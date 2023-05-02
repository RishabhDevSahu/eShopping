package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailsNewActivity extends AppCompatActivity {

    TextView orderIDText,dateTime,personName,paymentMethod,itemTotal,paid,status;
    OrderDetailProductAdapter orderDetailProductAdapter;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef,dbRef2;
    Query query;
    ImageView backSymbol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_new);

        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mauth.getCurrentUser();

        backSymbol = findViewById(R.id.backSymbol);
        orderIDText = findViewById(R.id.orderID);
        dateTime = findViewById(R.id.dateTime);
        personName = findViewById(R.id.personName);
        paymentMethod = findViewById(R.id.paymentMethod);
        itemTotal = findViewById(R.id.itemTotal);
        paid = findViewById(R.id.paid);
        status = findViewById(R.id.status);


        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailsNewActivity.super.onBackPressed();
            }
        });

        String orderID = getIntent().getStringExtra("OrderID");
        String randomOrderID = getIntent().getStringExtra("RandomOrderID");
        orderIDText.setText(randomOrderID);

        String orderDate = getIntent().getStringExtra("OrderDate");
        dateTime.setText(orderDate);

        String personName2 = getIntent().getStringExtra("OrderPersonName");
        personName.setText(personName2);

        String cardNumber = getIntent().getStringExtra("CardNumber");
        paymentMethod.setText(cardNumber);

        String totalAmount = getIntent().getStringExtra("TotalAmount");
        itemTotal.setText(totalAmount);
        paid.setText(totalAmount);

        String status2 = getIntent().getStringExtra("OrderStatus");
        status.setText(status2);


        dbRef = FirebaseDatabase.getInstance().getReference("Orders").child(firebaseUser.getUid());
        query =  dbRef.getRef().orderByChild("OrderDetails").getRef().equalTo(orderID).getRef().child(orderID).getRef().child("OrderDetails");
        Query query12 = query;
        RecyclerView orderDetailsRecyclerView = findViewById(R.id.odRecyclerView);
        FirebaseRecyclerOptions<OrderDetailProductModel> orderOption = new FirebaseRecyclerOptions.Builder<OrderDetailProductModel>().setQuery(query12, OrderDetailProductModel.class).build();
        orderDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailsNewActivity.this, LinearLayoutManager.VERTICAL, false));

        orderDetailProductAdapter = new OrderDetailProductAdapter(orderOption);
        orderDetailsRecyclerView.setAdapter(orderDetailProductAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        orderDetailProductAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orderDetailProductAdapter.stopListening();
    }
}