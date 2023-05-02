package com.example.eshopping;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import java.math.BigInteger;
import java.util.ArrayList;

public class AddToCartListAdapter extends RecyclerView.Adapter<AddToCartListAdapter.ViewHolder> {

    private ArrayList<AddToCartModel> listData;
    Context context;
    DBHelper dbHelper;
    AddToCartActivity addToCartActivity;
    Double price,itemQty;

    public AddToCartListAdapter(ArrayList<AddToCartModel> listData,AddToCartActivity addToCartActivity,Context context) {
        this.listData = listData;
        this.context = context;
        this.addToCartActivity = addToCartActivity;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public AddToCartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.add_to_cart_recyclerview,parent,false);
        AddToCartListAdapter.ViewHolder viewHolder = new AddToCartListAdapter.ViewHolder(view);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AddToCartListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        final AddToCartModel addToCartModel = listData.get(position);
        Glide.with(holder.itemView)
                .load(addToCartModel.getCartImage())
                .fitCenter()
                .into(holder.cartListImage);
        holder.cartListName.setText(addToCartModel.getCartName());
        holder.cartListPrice.setText(addToCartModel.getCartPrice());
        holder.itemQty.setText(addToCartModel.getQty());
        price = Double.parseDouble(addToCartModel.getCartPrice());
        itemQty =Double.parseDouble(addToCartModel.getQty());
        Double total = price*itemQty;
        addToCartModel.setCartTotalPrice(String.valueOf(total));
        dbHelper.updateCartTotalPrice(addToCartModel.getCartId(),String.valueOf(total));
        holder.cartListTotalPrice.setText(addToCartModel.getCartTotalPrice());

        System.out.println("Debug "+addToCartModel.getCartName());
        System.out.println("Debug2 "+ total);


        holder.incItemQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Integer.parseInt((String) addToCartModel.getQty()) < 10){
                    dbHelper.increseCartData(addToCartModel.getCartId(),
                            Integer.parseInt(String.valueOf(holder.itemQty.getText()))+1,addToCartModel.getCartTotalPrice());


                    addToCartActivity.refreshData();
                }else{
                    Toast.makeText(context, "You can't buy more than 10 items at a time", Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.decItemQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt((String) addToCartModel.getQty()) > 1){
                    dbHelper.decrementCartData(addToCartModel.getCartId(),Integer.parseInt(String.valueOf(holder.itemQty.getText()))-1,addToCartModel.getCartTotalPrice());


                    addToCartActivity.refreshData();

                }else{
                    Toast.makeText(context, "You can't buy item less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.cartClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteCartData(addToCartModel.getCartId());
                addToCartActivity.refreshData();
                notifyDataSetChanged();
                Toast.makeText(context, "Item " + holder.cartListName.getText() + " has been removed from list",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartListImage,cartClose;
        TextView cartListName,cartListPrice,cartListTotalPrice,itemQty,incItemQty,decItemQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cartListImage = (ImageView) itemView.findViewById(R.id.cartImage);
            this.cartListName = (TextView) itemView.findViewById(R.id.cartName);
            this.cartListPrice = (TextView) itemView.findViewById(R.id.cartPrice);
            this.cartListTotalPrice = (TextView) itemView.findViewById(R.id.cartTotalPrice);
            this.itemQty = (TextView) itemView.findViewById(R.id.item_Qty);
            this.incItemQty = (TextView) itemView.findViewById(R.id.inc_Item_Qty);
            this.decItemQty = (TextView) itemView.findViewById(R.id.dec_Item_Qty);
            this.cartClose = (ImageView) itemView.findViewById(R.id.cartClose);

        }
    }
}

