package com.example.eshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class HistoryAdapter extends FirebaseRecyclerAdapter<HistoryModel, HistoryAdapter.HistoryViewholder> {


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
        void onClickData(HistoryModel data);
    }


    public HistoryAdapter(@NonNull FirebaseRecyclerOptions<HistoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewholder holder, int position, @NonNull HistoryModel model) {
        holder.orderRandomID.setText(model.getOrderRandomID());
        holder.orderDate.setText(model.getOrderDate());
        holder.orderPersonName.setText(model.getPersonName());
        holder.orderTotalAmount.setText(model.getOrderTotalAmount());
        holder.orderStatus.setText(model.getOrderStatus());

        holder.viewOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClickData(model);
            }
        });
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recyclerview, parent, false);
        return new HistoryAdapter.HistoryViewholder(view);
    }

    public class HistoryViewholder extends RecyclerView.ViewHolder {

        TextView orderRandomID,orderDate,orderPersonName,orderTotalAmount,orderStatus;
        Button viewOrderDetails;

        public HistoryViewholder(@NonNull View itemView) {
            super(itemView);
            this.orderRandomID = (TextView) itemView.findViewById(R.id.order_id);
            this.orderDate = (TextView) itemView.findViewById(R.id.order_date);
            this.orderPersonName = (TextView) itemView.findViewById(R.id.order_person_Name);
            this.orderTotalAmount = (TextView) itemView.findViewById(R.id.total_amount);
            this.orderStatus = (TextView) itemView.findViewById(R.id.order_status);
            this.viewOrderDetails = (Button) itemView.findViewById(R.id.viewOrderDetails);
        }
    }
}
