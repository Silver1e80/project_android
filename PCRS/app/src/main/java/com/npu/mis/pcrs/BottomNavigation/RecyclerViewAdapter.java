package com.npu.mis.pcrs.BottomNavigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.npu.mis.pcrs.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    interface OnItemClickHandler {
        void OnItemClick(String text);

        void OnItemRemove(int position, String text);
    }

    //private Context context;
    private List<String> listData;
    private OnItemClickHandler onItemClickHandler;

    RecyclerViewAdapter(List<String> listdata, OnItemClickHandler clickHandler) {
        //this.context = context;
        listData = listdata;
        onItemClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.textViewItem.setText(listData.get(position));
        if (onItemClickHandler != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = listData.get(holder.getAdapterPosition());
                    onItemClickHandler.OnItemClick(msg);
                }
            });
            holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = listData.get(holder.getAdapterPosition());
                    onItemClickHandler.OnItemRemove(holder.getAdapterPosition(), msg);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewItem;
        private Button buttonRemove;

        public ViewHolder(View itemView) {
        super(itemView);
        textViewItem = (TextView) itemView.findViewById(R.id.txtItem);
        buttonRemove = (Button) itemView.findViewById(R.id.btnRemove);
        }
    }

    public void addItem(String text) {
        listData.add(1, text);
        notifyItemInserted(1);
    }

    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }
}
