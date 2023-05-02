package com.example.eshopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class ProductFirebaseAdapter extends FirebaseRecyclerAdapter<ProductModel, ProductFirebaseAdapter.ProductModelViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    ArrayList<ProductModel> productModel = new ArrayList<ProductModel>();


    onItemClick onItemClick;

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    interface onItemClick{
        void onClickData(ProductModel data);
    }

    public void filterList(ArrayList<ProductModel> filterllist) {

        productModel = filterllist;
        notifyDataSetChanged();
    }

    public ProductFirebaseAdapter(@NonNull FirebaseRecyclerOptions<ProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductFirebaseAdapter.ProductModelViewholder holder, int position, @NonNull ProductModel model) {
        holder.ProductName.setText(model.getProductName());
        holder.ProductDiscountPrice.setText(String.valueOf(model.getProductDiscountPrice()));
        holder.ProductOriginalPrice.setText(String.valueOf(model.getProductOriginalPrice()));
        holder.ProductDiscountPercent.setText(String.valueOf(model.getProductDiscountPercent()));
        Glide.with(holder.itemView)
                .load(model.getProductImage())
                .fitCenter()
                .into(holder.ProductImage);
        holder.ProductCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickData(model);
            }
        });
    }

    @NonNull
    @Override
    public ProductFirebaseAdapter.ProductModelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_selected_item_recycler_view, parent, false);
        return new ProductFirebaseAdapter.ProductModelViewholder(view);
    }

    public static class ProductModelViewholder extends RecyclerView.ViewHolder {
        TextView ProductName,ProductDiscountPrice,ProductOriginalPrice,ProductDiscountPercent;
        ImageView ProductImage;
        CardView ProductCardView;

        public ProductModelViewholder(@NonNull View itemView) {
            super(itemView);
            this.ProductName = (TextView) itemView.findViewById(R.id.selected_category_item_name);
            this.ProductDiscountPrice = (TextView) itemView.findViewById(R.id.selected_category_item_discountPrice);
            this.ProductOriginalPrice = (TextView) itemView.findViewById(R.id.selected_category_item_originalPrice);
            this.ProductDiscountPercent = (TextView) itemView.findViewById(R.id.selected_category_item_discountPercent);
            this.ProductImage = (ImageView) itemView.findViewById(R.id.selected_category_item_image);
            this.ProductCardView = (CardView) itemView.findViewById(R.id.selected_category_item_cardview);
        }
    }
}
