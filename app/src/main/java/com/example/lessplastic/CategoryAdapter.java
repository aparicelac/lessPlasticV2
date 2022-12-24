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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> mData;

    public CategoryAdapter(List<Category> itemList){
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, null, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Category> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView title, status, weight, quantity;

        public ViewHolder(View itemView) {
            super(itemView);

            iconImage = itemView.findViewById(R.id.iconImageView);
            title = itemView.findViewById(R.id.categoryName);
            status = itemView.findViewById(R.id.categoryStatus);
            weight = itemView.findViewById(R.id.numberWeight);
            quantity = itemView.findViewById(R.id.quantityNumber);

        }

        public void bindData(final Category item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.DST_IN);

            title.setText(item.getTitle());

            switch (title.getText().toString()){
                case "Bolsas":
                    iconImage.setImageResource(R.drawable.ic_bags);
                    break;
                case "Botellas":
                    iconImage.setImageResource(R.drawable.ic_bottles);
                    break;
                case "Tecnopor":
                    iconImage.setImageResource(R.drawable.ic_techno);
                    break;
                case "Empaques":
                    iconImage.setImageResource(R.drawable.ic_wrap);
                    break;
                case "PVC":
                    iconImage.setImageResource(R.drawable.ic_pvc);
                    break;
                case "Envases":
                    iconImage.setImageResource(R.drawable.ic_containers);
                    break;
            }
            System.out.println(title.getText());

            status.setText(item.getStatus());
            weight.setText(item.getWeight());
            quantity.setText(item.getQuantity());
        }
    }

}