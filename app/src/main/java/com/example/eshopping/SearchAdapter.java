package com.example.eshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    ArrayList<ProductModel> listData = new ArrayList<ProductModel>();
    ArrayList<ProductModel> fullListData = new ArrayList<ProductModel>();
    Context context;


    public SearchAdapter(ArrayList<ProductModel> productModels, Context context) {
        this.listData = productModels;
        this.context = context;
    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_selected_item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        final ProductModel productModel = listData.get(position);
        holder.searchItemName.setText(productModel.getProductName());
        holder.searchItemDiscountPrice.setText(productModel.getProductDiscountPrice());
        holder.searchItemOriginalPrice.setText(productModel.getProductOriginalPrice());
        holder.searchItemDiscountPercent.setText(productModel.getProductDiscountPercent());
        Glide.with(holder.itemView)
                .load(productModel.getProductImage())
                .fitCenter()
                .into(holder.searchItemImage);

        holder.searchItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(context , ItemDetailsActivity.class);
//                i.putExtra("CategorySelectedItemID",searchModel.getSearchItemID());
//                i.putExtra("CategorySelectedItemImage",searchModel.getSearchItemImage());
//                i.putExtra("CategorySelectedItemName",searchModel.getSearchItemName());
//                i.putExtra("CategorySelectedItemDiscountPrice",searchModel.getSearchItemDiscountPrice());
//                i.putExtra("CategorySelectedItemOriginalPrice",searchModel.getSearchItemOriginalPrice());
//                i.putExtra("CategorySelectedItemDiscountPercent",searchModel.getSearchItemDiscountPercent());
//                i.putExtra("CategorySelectedItemStockStatus",searchModel.getSearchItemStockStatus());
//                i.putExtra("CategorySelectedItemShortDescription",searchModel.getSearchItemShortDescription());
//                context.startActivity(i);
            }

        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public Filter getFilter() {
       return productFilter;
    }

    private Filter productFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ProductModel> filterlist=new ArrayList<>();

            if(constraint==null|| constraint.length()==0){
                filterlist.addAll(fullListData);
            }
            else{
                String pattrn=constraint.toString().toLowerCase().trim();
                for(ProductModel item :fullListData){
                    if(item.getProductName().toLowerCase().contains(pattrn)){
                        filterlist.add(item);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterlist;
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listData.clear();
            listData.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView searchItemName,searchItemDiscountPrice,searchItemOriginalPrice,searchItemDiscountPercent;
        ImageView searchItemImage;
        CardView searchItemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.searchItemName = (TextView) itemView.findViewById(R.id.selected_category_item_name);
            this.searchItemDiscountPrice = (TextView) itemView.findViewById(R.id.selected_category_item_discountPrice);
            this.searchItemOriginalPrice = (TextView) itemView.findViewById(R.id.selected_category_item_originalPrice);
            this.searchItemDiscountPercent = (TextView) itemView.findViewById(R.id.selected_category_item_discountPercent);
            this.searchItemImage = (ImageView) itemView.findViewById(R.id.selected_category_item_image);
            this.searchItemCardView = (CardView) itemView.findViewById(R.id.selected_category_item_cardview);
        }
    }
}
