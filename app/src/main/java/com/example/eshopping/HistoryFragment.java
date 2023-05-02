package com.example.eshopping;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HistoryFragment extends Fragment {

    DBHelper dbHelper;
    RecyclerView orderHistoryRecyclerView;
    HistoryAdapter historyAdapter;
    RelativeLayout noOrderHistoryImg;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    Query query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_history, null);

        dbHelper = new DBHelper(root.getContext());

        noOrderHistoryImg = root.findViewById(R.id.noOrderHistoryImg);

        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mauth.getCurrentUser();

        dbRef = FirebaseDatabase.getInstance().getReference("Orders").child(firebaseUser.getUid());
        orderHistoryRecyclerView = root.findViewById(R.id.orderHistoryRecyclerView);
        query = dbRef;
        FirebaseRecyclerOptions<HistoryModel> historyData = new FirebaseRecyclerOptions.Builder<HistoryModel>().setQuery(query, HistoryModel.class).build();
        historyAdapter = new HistoryAdapter(historyData);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {

                    orderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
                    orderHistoryRecyclerView.setAdapter(historyAdapter);

                    historyAdapter.setOnItemClick(new HistoryAdapter.onItemClick() {
                        @Override
                        public void onClickData(HistoryModel data) {
                            Intent intent = new Intent(root.getContext(), OrderDetailsNewActivity.class);
                            intent.putExtra("OrderID", data.getOrderID());
                            intent.putExtra("RandomOrderID", data.getOrderRandomID());
                            intent.putExtra("OrderDate", data.getOrderDate());
                            intent.putExtra("OrderPersonName", data.getPersonName());
                            intent.putExtra("CardNumber", data.getCardNumber());
                            intent.putExtra("TotalAmount", data.getOrderTotalAmount());
                            intent.putExtra("OrderStatus", data.getOrderStatus());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            root.getContext().startActivity(intent);
                        }
                    });
                }else {
                    noOrderHistoryImg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        historyAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        historyAdapter.stopListening();
    }
}