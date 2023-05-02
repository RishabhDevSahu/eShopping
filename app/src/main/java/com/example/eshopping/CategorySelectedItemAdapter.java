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

public class CategorySelectedItemAdapter extends RecyclerView.Adapter<CategorySelectedItemAdapter.ViewHolder>{

//    private final CategorySelectedItemModel[] listData;
//    ArrayList<CategorySelectedItemModel> listData;
    ArrayList<ProductModel> listData;
    Context context;

    public CategorySelectedItemAdapter(ArrayList<ProductModel> listData,Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CategorySelectedItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.category_selected_item_recycler_view,parent,false);
        CategorySelectedItemAdapter.ViewHolder viewHolder = new CategorySelectedItemAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySelectedItemAdapter.ViewHolder holder, int position) {
//        final CategorySelectedItemModel categorySelectedItemModel = listData.get(position);
//        holder.categorySelectedItemName.setText(categorySelectedItemModel.getCategorySelectedItemName());
//        holder.categorySelectedItemDiscountPrice.setText(categorySelectedItemModel.getCategorySelectedItemDiscountPrice());
//        holder.categorySelectedItemOriginalPrice.setText(categorySelectedItemModel.getCategorySelectedItemOriginalPrice());
//        holder.categorySelectedItemDiscountPercent.setText(categorySelectedItemModel.getCategorySelectedItemDiscountPercent());
//        Glide.with(holder.itemView)
//                .load(categorySelectedItemModel.getCategorySelectedItemImage())
//                .fitCenter()
//                .into(holder.categorySelectedItemImage);
//
//        holder.categorySelectedItemCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, categorySelectedItemModel.getCategorySelectedItemName()+" is opened", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(context , ItemDetailsActivity.class);
//                i.putExtra("CategorySelectedItemID",categorySelectedItemModel.getCategorySelectedItemID());
//                i.putExtra("CategorySelectedItemImage",categorySelectedItemModel.getCategorySelectedItemImage());
//                i.putExtra("CategorySelectedItemName",categorySelectedItemModel.getCategorySelectedItemName());
//                i.putExtra("CategorySelectedItemDiscountPrice",categorySelectedItemModel.getCategorySelectedItemDiscountPrice());
//                i.putExtra("CategorySelectedItemOriginalPrice",categorySelectedItemModel.getCategorySelectedItemOriginalPrice());
//                i.putExtra("CategorySelectedItemDiscountPercent",categorySelectedItemModel.getCategorySelectedItemDiscountPercent());
//                i.putExtra("CategorySelectedItemStockStatus",categorySelectedItemModel.getCategorySelectedItemStockStatus());
//                i.putExtra("CategorySelectedItemShortDescription",categorySelectedItemModel.getCategorySelectedItemShortDescription());
//                context.startActivity(i);
//            }

        final ProductModel categorySelectedItemModel = listData.get(position);
        holder.categorySelectedItemName.setText(categorySelectedItemModel.getProductName());
        holder.categorySelectedItemDiscountPrice.setText(String.valueOf(categorySelectedItemModel.getProductDiscountPrice()));
        holder.categorySelectedItemOriginalPrice.setText(String.valueOf(categorySelectedItemModel.getProductOriginalPrice()));
        holder.categorySelectedItemDiscountPercent.setText(String.valueOf(categorySelectedItemModel.getProductDiscountPercent()));
        Glide.with(holder.itemView)
                .load(categorySelectedItemModel.getProductImage())
                .fitCenter()
                .into(holder.categorySelectedItemImage);

        holder.categorySelectedItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context , ItemDetailsActivity.class);
                i.putExtra("CategorySelectedItemID",categorySelectedItemModel.getCategoryID());
                i.putExtra("CategorySelectedItemImage",categorySelectedItemModel.getProductImage());
                i.putExtra("CategorySelectedItemName",categorySelectedItemModel.getProductName());
                i.putExtra("CategorySelectedItemDiscountPrice",categorySelectedItemModel.getProductDiscountPrice());
                i.putExtra("CategorySelectedItemOriginalPrice",categorySelectedItemModel.getProductOriginalPrice());
                i.putExtra("CategorySelectedItemDiscountPercent",categorySelectedItemModel.getProductDiscountPercent());
                i.putExtra("CategorySelectedItemStockStatus",categorySelectedItemModel.getProductStockStatus());
                i.putExtra("CategorySelectedItemShortDescription",categorySelectedItemModel.getProductShortDescription());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categorySelectedItemName,categorySelectedItemDiscountPrice,categorySelectedItemOriginalPrice,categorySelectedItemDiscountPercent;
        ImageView categorySelectedItemImage;
        CardView categorySelectedItemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categorySelectedItemName = (TextView) itemView.findViewById(R.id.selected_category_item_name);
            this.categorySelectedItemDiscountPrice = (TextView) itemView.findViewById(R.id.selected_category_item_discountPrice);
            this.categorySelectedItemOriginalPrice = (TextView) itemView.findViewById(R.id.selected_category_item_originalPrice);
            this.categorySelectedItemDiscountPercent = (TextView) itemView.findViewById(R.id.selected_category_item_discountPercent);
            this.categorySelectedItemImage = (ImageView) itemView.findViewById(R.id.selected_category_item_image);
            this.categorySelectedItemCardView = (CardView) itemView.findViewById(R.id.selected_category_item_cardview);
        }
    }
}
