package com.example.lessplastic;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LogrosAdapter extends RecyclerView.Adapter<LogrosAdapter.ViewHolder> {

    private List<Logros> mData;

    public LogrosAdapter(List<Logros> itemList){
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public LogrosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.logros_list, null, false);
        return new LogrosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LogrosAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Logros> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name, description, status;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.tvNombreLogro);
            description = itemView.findViewById(R.id.tvDescripcionLogro);
            status = itemView.findViewById(R.id.tvEstadoLogro);

        }

        public void bindData(final Logros item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            description.setText(item.getDescription());
            status.setText(item.getStatus());
        }
    }

}

