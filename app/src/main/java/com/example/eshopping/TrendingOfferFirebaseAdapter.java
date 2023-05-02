package com.example.eshopping;

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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TrendingOfferFirebaseAdapter extends FirebaseRecyclerAdapter<TrendingOfferModel, TrendingOfferFirebaseAdapter.TrendingOfferModelViewholder> {
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
        void onClickData(TrendingOfferModel data);
    }

    public TrendingOfferFirebaseAdapter(@NonNull FirebaseRecyclerOptions<TrendingOfferModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TrendingOfferFirebaseAdapter.TrendingOfferModelViewholder holder, int position, @NonNull TrendingOfferModel model) {
        holder.trendOfferName.setText(model.getTrendOfferName());
        holder.trendOfferDiscount.setText(model.getTrendOfferDiscount());
        holder.trendOfferDescription.setText(model.getTrendOfferDescription());
        Glide.with(holder.itemView)
                .load(model.getTrendOfferImage())
                .fitCenter()
                .into(holder.trendOfferImage);
        holder.trendOfferCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickData(model);
            }
        });
    }

    @NonNull
    @Override
    public TrendingOfferFirebaseAdapter.TrendingOfferModelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_offers_recycler_view, parent, false);
        return new TrendingOfferFirebaseAdapter.TrendingOfferModelViewholder(view);
    }

    public class TrendingOfferModelViewholder extends RecyclerView.ViewHolder {
        TextView trendOfferName,trendOfferDiscount,trendOfferDescription;
        ImageView trendOfferImage;
        CardView trendOfferCardView;

        public TrendingOfferModelViewholder(@NonNull View itemView) {
            super(itemView);
            this.trendOfferName = (TextView) itemView.findViewById(R.id.trend_offer_name);
            this.trendOfferDiscount = (TextView) itemView.findViewById(R.id.trend_offer_discount);
            this.trendOfferDescription = (TextView) itemView.findViewById(R.id.trend_offer_description);
            this.trendOfferImage = (ImageView) itemView.findViewById(R.id.trend_offer_image);
            this.trendOfferCardView = (CardView) itemView.findViewById(R.id.trend_offer_cardView);
        }
    }
}
