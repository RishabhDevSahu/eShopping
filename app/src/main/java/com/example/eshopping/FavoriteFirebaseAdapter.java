package com.example.eshopping;

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

public class FavoriteFirebaseAdapter extends FirebaseRecyclerAdapter<FavoriteModel, FavoriteFirebaseAdapter.FavoriteModelViewholder> {
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
        void onClickData(FavoriteModel data);
    }

    public FavoriteFirebaseAdapter(@NonNull FirebaseRecyclerOptions<FavoriteModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FavoriteFirebaseAdapter.FavoriteModelViewholder holder, int position, @NonNull FavoriteModel model) {
        holder.FavoriteName.setText(model.getFavoriteName());
        holder.FavoriteDiscountPrice.setText(String.valueOf(model.getFavoriteDiscountPrice()));
        holder.FavoriteOriginalPrice.setText(String.valueOf(model.getFavoriteOriginalPrice()));
        holder.FavoriteDiscountPercent.setText(String.valueOf(model.getFavoriteDiscountPercent()));
        Glide.with(holder.itemView)
                .load(model.getFavoriteImage())
                .fitCenter()
                .into(holder.FavoriteImage);
        holder.delItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickData(model);
            }
        });
    }

    @NonNull
    @Override
    public FavoriteFirebaseAdapter.FavoriteModelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_recyclerview, parent, false);
        return new FavoriteFirebaseAdapter.FavoriteModelViewholder(view);
    }

    public class FavoriteModelViewholder extends RecyclerView.ViewHolder {
        TextView FavoriteName,FavoriteDiscountPrice,FavoriteOriginalPrice,FavoriteDiscountPercent;;
        ImageView FavoriteImage,delItem;

        public FavoriteModelViewholder(@NonNull View itemView) {
            super(itemView);
            this.FavoriteName = (TextView) itemView.findViewById(R.id.wishlist_item_name);
            this.FavoriteDiscountPrice = (TextView) itemView.findViewById(R.id.wishlist_item_discountPrice);
            this.FavoriteOriginalPrice = (TextView) itemView.findViewById(R.id.wishlist_item_originalPrice);
            this.FavoriteDiscountPercent = (TextView) itemView.findViewById(R.id.wishlist_item_discountPercent);
            this.FavoriteImage = (ImageView) itemView.findViewById(R.id.wishlist_image);
            this.delItem = (ImageView) itemView.findViewById(R.id.wishlist_del);
        }
    }
}
