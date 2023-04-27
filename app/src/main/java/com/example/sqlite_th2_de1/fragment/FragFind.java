package com.example.sqlite_th2_de1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.activity.DetailActivity;
import com.example.sqlite_th2_de1.adapter.ListAdapter;
import com.example.sqlite_th2_de1.database.DAO;
import com.example.sqlite_th2_de1.model.TourAndID;

import java.util.List;

public class FragFind extends Fragment {

    RecyclerView findList;
    ListAdapter listAdapter;

    List<TourAndID> list;

    List<Pair<String, Long>> yearlyTotal;
    TextView rank, size;
    Button findBtn;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private DAO mDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_find, container, false);

        mDAO = new DAO(getContext());
        list = mDAO.getAll();

        findBtn = view.findViewById(R.id.findBtn);

        findList = view.findViewById(R.id.find_list);
        findList.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new ListAdapter(getContext(), list);
        findList.setAdapter(listAdapter);

        checkBox1 = view.findViewById(R.id.checkbox1);
        checkBox2 = view.findViewById(R.id.checkbox2);
        checkBox3 = view.findViewById(R.id.checkbox3);
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);

        size = view.findViewById(R.id.sizeRecord);
        rank = view.findViewById(R.id.rank);

//        yearlyTotal = mDAO.getYearlyTotal();
//
//        for (Pair<String, Long> p: yearlyTotal) {
//            rank.setText(p.first + ": " + p.second + "\n");
//        }

        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TourAndID tour) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Log.d("Tuan", "onItemClick: " + tour.toString());
                intent.putExtra("tour", tour);
                startActivity(intent);
            }
        });


        findBtn = view.findViewById(R.id.findBtn);
        rank = view.findViewById(R.id.rank);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked()) {
                    list = mDAO.getAll();
                    listAdapter.setList(list);
                } else {
                    String f3 = "";
                    Long l = Long.valueOf(0);
                    if (checkBox1.isChecked()) f3 += checkBox1.getText() + "  ";
                    if (checkBox2.isChecked()) f3 += checkBox2.getText() + "  ";
                    if (checkBox3.isChecked()) f3 += checkBox3.getText();
                    list = mDAO.getAll(f3);
                    for (TourAndID t : list) {
                        l += t.getTour().getField5();
                    }
                    size.setText(String.valueOf(l));
                    listAdapter.setList(list);
                }

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list = mDAO.getAll();
        listAdapter.setList(list);
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
    }
}
