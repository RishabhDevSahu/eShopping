package com.example.eshopping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

//    private final CategoryModel[] listData;
    private ArrayList<CategoryModel> listData;

    Context context;

    public CategoryAdapter(ArrayList<CategoryModel> listData,Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.category_recycler_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        final CategoryModel categoryModel = listData.get(position);
        holder.categoryName.setText(categoryModel.getCategoryName());
        Glide.with(holder.itemView)
                .load(categoryModel.getCategoryImage())
                .fitCenter()
                .into(holder.categoryImage);
        holder.categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, categoryModel.getCategoryName()+" is opened", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context , CategorySelectedItemActivity.class);
                i.putExtra("CategoryName",categoryModel.getCategoryName());
                context.startActivity(i);
            }

    });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;
        CardView categoryCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryName = (TextView) itemView.findViewById(R.id.category_Name);
            this.categoryImage = (ImageView) itemView.findViewById(R.id.category_Image);
            this.categoryCard = (CardView) itemView.findViewById(R.id.category_CardView);
        }
    }
}
