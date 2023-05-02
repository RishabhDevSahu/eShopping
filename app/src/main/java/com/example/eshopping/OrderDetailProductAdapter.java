package com.example.eshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class OrderDetailProductAdapter extends FirebaseRecyclerAdapter<OrderDetailProductModel, OrderDetailProductAdapter.OrderDetailProductModelViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    onItemClick onItemClick;

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    interface onItemClick{
        void onClickData(OrderDetailProductModel data);
    }

    public OrderDetailProductAdapter(@NonNull FirebaseRecyclerOptions<OrderDetailProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderDetailProductModelViewholder holder, int position, @NonNull OrderDetailProductModel model) {
        holder.productName.setText(model.getProductName());
        holder.productTotalPrice.setText(model.getProductTotalPrice());
        holder.productQuantity.setText(model.getProductQuantity());
        holder.productPrice.setText(model.getProductPrice());
        Glide.with(holder.itemView)
                .load(model.getProductImage())
                .fitCenter()
                .into(holder.productImage);
    }

    @NonNull
    @Override
    public OrderDetailProductModelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_recyclerview, parent, false);
        return new OrderDetailProductAdapter.OrderDetailProductModelViewholder(view);
    }

    public class OrderDetailProductModelViewholder extends RecyclerView.ViewHolder {

        TextView productName,productTotalPrice,productQuantity,productPrice;
        ImageView productImage;

        public OrderDetailProductModelViewholder(@NonNull View itemView) {
            super(itemView);
            this.productName = (TextView) itemView.findViewById(R.id.orderDetailsName);
            this.productQuantity = (TextView) itemView.findViewById(R.id.orderDetailsQuantity);
            this.productTotalPrice = (TextView) itemView.findViewById(R.id.orderDetailsTotalPrice);
            this.productPrice = (TextView) itemView.findViewById(R.id.orderDetailsPrice);
            this.productImage = (ImageView) itemView.findViewById(R.id.orderDetailsImage);
        }
    }
}
