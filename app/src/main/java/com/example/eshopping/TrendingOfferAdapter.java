package com.example.eshopping;

import android.content.Context;
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

public class TrendingOfferAdapter extends RecyclerView.Adapter<TrendingOfferAdapter.ViewHolder>{

    private final TrendingOfferModel[] listData;
    Context context;

    public TrendingOfferAdapter(TrendingOfferModel[] listData,Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public TrendingOfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.trending_offers_recycler_view,parent,false);
        TrendingOfferAdapter.ViewHolder viewHolder = new TrendingOfferAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingOfferAdapter.ViewHolder holder, int position) {
        final TrendingOfferModel trendingOfferModel = listData[position];
        holder.trendOfferName.setText(trendingOfferModel.getTrendOfferName());
        holder.trendOfferDiscount.setText(trendingOfferModel.getTrendOfferDiscount());
        holder.trendOfferDescription.setText(trendingOfferModel.getTrendOfferDescription());
        Glide.with(holder.itemView)
                .load(trendingOfferModel.getTrendOfferImage())
                .fitCenter()
                .into(holder.trendOfferImage);
        holder.trendOfferCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, trendingOfferModel.getTrendOfferName()+" is opened", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trendOfferName,trendOfferDiscount,trendOfferDescription;
        ImageView trendOfferImage;
        CardView trendOfferCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.trendOfferName = (TextView) itemView.findViewById(R.id.trend_offer_name);
            this.trendOfferDiscount = (TextView) itemView.findViewById(R.id.trend_offer_discount);
            this.trendOfferDescription = (TextView) itemView.findViewById(R.id.trend_offer_description);
            this.trendOfferImage = (ImageView) itemView.findViewById(R.id.trend_offer_image);
            this.trendOfferCardView = (CardView) itemView.findViewById(R.id.trend_offer_cardView);
        }
    }
}
