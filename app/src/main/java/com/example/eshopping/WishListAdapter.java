package com.example.eshopping;

import android.annotation.SuppressLint;
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

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private ArrayList<WishListModel> listData;
    Context context;
    DBHelper dbHelper;
    FavoriteFragment favoriteFragment;

    public WishListAdapter(ArrayList<WishListModel> listData, FavoriteFragment favoriteFragment, Context context) {
        this.listData = listData;
        this.favoriteFragment = favoriteFragment;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.wishlist_recyclerview,parent,false);
        WishListAdapter.ViewHolder viewHolder = new WishListAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.ViewHolder holder, int position) {

        final WishListModel wishListModel = listData.get(position);
        Glide.with(holder.itemView)
                .load(wishListModel.getWishListImage())
                .fitCenter()
                .into(holder.wishListImage);
        holder.wishListName.setText(wishListModel.getWishListName());
        holder.wishListDiscountPrice.setText(wishListModel.getWishListDiscountPrice());
        holder.wishListOriginalPrice.setText(wishListModel.getWishListOriginalPrice());
        holder.wishListDiscountPercent.setText(wishListModel.getWishListDiscountPercent());
        holder.delItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                dbHelper.deleteWishListData(wishListModel.getWishListID());
                favoriteFragment.refreshData();
                notifyDataSetChanged();
                Toast.makeText(context, "Item " + holder.wishListName.getText() + " has been removed from list",
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.wishListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , ItemDetailsActivity.class);
                i.putExtra("ProductID",wishListModel.getWishListID());
                i.putExtra("ProductImage",wishListModel.getWishListImage());
                i.putExtra("ProductName",wishListModel.getWishListName());
                i.putExtra("ProductDiscountPrice",wishListModel.getWishListDiscountPrice());
                i.putExtra("ProductOriginalPrice",wishListModel.getWishListOriginalPrice());
                i.putExtra("ProductDiscountPercent",wishListModel.getWishListDiscountPercent());
                i.putExtra("ProductStockStatus",wishListModel.getWishListStockStatus());
                i.putExtra("ProductShortDescription",wishListModel.getWishListShortDescription());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView wishListImage,delItem;
        TextView wishListName,wishListDiscountPrice,wishListOriginalPrice,wishListDiscountPercent;
        CardView wishListCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wishListImage = (ImageView) itemView.findViewById(R.id.wishlist_image);
            this.wishListName = (TextView) itemView.findViewById(R.id.wishlist_item_name);
            this.wishListDiscountPrice = (TextView) itemView.findViewById(R.id.wishlist_item_discountPrice);
            this.wishListOriginalPrice = (TextView) itemView.findViewById(R.id.wishlist_item_originalPrice);
            this.wishListDiscountPercent = (TextView) itemView.findViewById(R.id.wishlist_item_discountPercent);
            this.delItem = (ImageView) itemView.findViewById(R.id.wishlist_del);
            this.wishListCardView = (CardView) itemView.findViewById(R.id.wishlist_cardview);
        }
    }
}
