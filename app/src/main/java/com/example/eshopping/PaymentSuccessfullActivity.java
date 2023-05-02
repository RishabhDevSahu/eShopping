package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sn.lib.NestedProgress;

import java.util.ArrayList;

public class PaymentSuccessfullActivity extends AppCompatActivity {

    DBHelper dbHelper;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    Button viewOrderDetail;
    NestedProgress nestedProgress;
    String RandomOrderID,OrderDate,OrderPersonName,CardNumber,TotalAmount,OrderStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successfull);

        dbHelper = new DBHelper(getApplicationContext());
        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mauth.getCurrentUser();

        viewOrderDetail = findViewById(R.id.viewOrderDetail);
        nestedProgress = findViewById(R.id.nestedProgress);

        String OrderID = getIntent().getStringExtra("OrderID");

        dbRef = FirebaseDatabase.getInstance().getReference("Orders").child(firebaseUser.getUid()).child(OrderID);
        Query query = dbRef;

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HistoryModel historyModel = snapshot.getValue(HistoryModel.class);
                RandomOrderID = historyModel.getOrderRandomID();
                OrderDate = historyModel.getOrderDate();
                OrderPersonName = historyModel.getPersonName();
                CardNumber = historyModel.getCardNumber();
                TotalAmount = historyModel.getOrderTotalAmount();
                OrderStatus = historyModel.getOrderStatus();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        viewOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),OrderDetailsNewActivity.class);
                i.putExtra("OrderID", OrderID);
                i.putExtra("RandomOrderID", RandomOrderID);
                i.putExtra("OrderDate", OrderDate);
                i.putExtra("OrderPersonName", OrderPersonName);
                i.putExtra("CardNumber", CardNumber);
                i.putExtra("TotalAmount", TotalAmount);
                i.putExtra("OrderStatus",OrderStatus);
                startActivity(i);
                dbHelper.deleteAddToCartData(firebaseUser.getUid());
                finish();
                }
        });


    }

    @Override
    public void onBackPressed() {
        super.finish();
    }
}