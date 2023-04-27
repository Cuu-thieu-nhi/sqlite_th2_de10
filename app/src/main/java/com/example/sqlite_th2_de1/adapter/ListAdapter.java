package com.example.sqlite_th2_de1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.model.Tour;
import com.example.sqlite_th2_de1.model.TourAndID;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<TourAndID> list;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(TourAndID tour);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idtv;
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public TextView tv4;
        public TextView tv5;

        public ViewHolder(View v) {
            super(v);
            idtv = v.findViewById(R.id.tv_id);
            tv1 = v.findViewById(R.id.tv1);
            tv2 = v.findViewById(R.id.tv2);
            tv3 = v.findViewById(R.id.tv3);
            tv4 = v.findViewById(R.id.tv4);
            tv5 = v.findViewById(R.id.tv5);
        }

        public void bind(final TourAndID tourAndID, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(tourAndID);
                }
            });
        }
    }

    public ListAdapter(Context context, List<TourAndID> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TourAndID tourAndID = list.get(position);
        holder.idtv.setText(Integer.toString(tourAndID.getId()));
        holder.tv1.setText(tourAndID.getTour().getField1());
        holder.tv2.setText(tourAndID.getTour().getField2());
        holder.tv3.setText(tourAndID.getTour().getField3());
        holder.tv4.setText(tourAndID.getTour().getField4());
        holder.tv5.setText(Long.toString(tourAndID.getTour().getField5()));
        holder.bind(tourAndID, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<TourAndID> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
