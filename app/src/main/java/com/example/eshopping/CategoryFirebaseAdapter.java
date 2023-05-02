package com.example.eshopping;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class CategoryFirebaseAdapter extends FirebaseRecyclerAdapter<CategoryModel, CategoryFirebaseAdapter.CategoryModelViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    onItemClick onItemClick;

    public void setOnItemClick(CategoryFirebaseAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    interface onItemClick{
        void onClickData(CategoryModel data);
    }

    public CategoryFirebaseAdapter(@NonNull FirebaseRecyclerOptions<CategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryModelViewholder holder, int position, @NonNull CategoryModel model) {
        holder.categoryName.setText(model.getCategoryName());
        Glide.with(holder.itemView)
                .load(model.getCategoryImage())
                .fitCenter()
                .into(holder.categoryImage);
        holder.categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickData(model);
            }
        });
    }


    @NonNull
    @Override
    public CategoryModelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_view, parent, false);
        return new CategoryFirebaseAdapter.CategoryModelViewholder(view);
    }

    public class CategoryModelViewholder extends RecyclerView.ViewHolder {
        TextView categoryName,categoryID;
        ImageView categoryImage;
        CardView categoryCard;
        public CategoryModelViewholder(@NonNull View itemView) {
            super(itemView);
            this.categoryName = (TextView) itemView.findViewById(R.id.category_Name);
            this.categoryImage = (ImageView) itemView.findViewById(R.id.category_Image);
            this.categoryCard = (CardView) itemView.findViewById(R.id.category_CardView);
        }
    }
}
