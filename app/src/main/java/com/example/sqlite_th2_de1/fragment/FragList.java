package com.example.sqlite_th2_de1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.activity.DetailActivity;
import com.example.sqlite_th2_de1.adapter.ListAdapter;
import com.example.sqlite_th2_de1.database.DAO;
import com.example.sqlite_th2_de1.model.Tour;
import com.example.sqlite_th2_de1.model.TourAndID;

import java.util.List;

public class FragList extends Fragment {
    RecyclerView workListRecycleView;
    ListAdapter listAdapter;
    List<TourAndID> list;
    private DAO mDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_list, container, false);

        mDAO = new DAO(getContext());
        list = mDAO.getAll();

        workListRecycleView = view.findViewById(R.id.work_list);
        workListRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new ListAdapter(getContext(), list);
        workListRecycleView.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TourAndID tour) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Log.d("Tuan", "onItemClick: " + tour.toString());
                intent.putExtra("tour", tour);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDAO.open();
        list = mDAO.getAll();
        mDAO.close();
        listAdapter.setList(list);
    }
}
