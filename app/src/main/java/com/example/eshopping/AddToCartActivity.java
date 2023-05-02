package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AddToCartActivity extends AppCompatActivity {

    ImageView backSymbol;
    DBHelper dbHelper;
    RecyclerView cartRecyclerView;
    AddToCartListAdapter cartAdapter;
    ArrayList<AddToCartModel> addToCartModels =  new ArrayList<AddToCartModel>();;
    TextView cartSubTotalPrice;
    AddToCartModel listData;
    LinearLayout bottomCartLayout,emptyCartLayout;
    Button proceedToCheckOutButton;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        bottomCartLayout = findViewById(R.id.bottomCartLayout);
        emptyCartLayout = findViewById(R.id.emptyCartLayout);

        proceedToCheckOutButton = findViewById(R.id.proceedToCheckoutButton);

        dbHelper = new DBHelper(AddToCartActivity.this);
        mauth = FirebaseAuth.getInstance();
        firebaseUser = mauth.getCurrentUser();

        try {
            cartSubTotalPrice = findViewById(R.id.cartSubTotalPrice);

            addToCartModels = new ArrayList<AddToCartModel>();
            addToCartModels = dbHelper.getCartData();
            double sum = dbHelper.sumOfTotalPrice();
            cartSubTotalPrice.setText(String.valueOf(sum));
            cartSubTotalPrice.notify();
            dbHelper.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        backSymbol = findViewById(R.id.backSymbol);
        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToCartActivity.super.onBackPressed();
            }
        });

        proceedToCheckOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddAddressActivity.class);
                startActivity(i);
                AddToCartActivity.super.onBackPressed();
            }
        });

        cartRecyclerView = (RecyclerView) findViewById(R.id.addToCartRecyclerView);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(AddToCartActivity.this,LinearLayoutManager.VERTICAL,false));

        if (dbHelper.getCartData().size() > 0) {
            cartRecyclerView.setVisibility(View.VISIBLE);
            cartAdapter = (AddToCartListAdapter) new AddToCartListAdapter(dbHelper.getCartData(),AddToCartActivity.this,AddToCartActivity.this);
            cartRecyclerView.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
            bottomCartLayout.setVisibility(View.VISIBLE);

        }
        else {
            cartRecyclerView.setVisibility(View.GONE);
            emptyCartLayout.setVisibility(View.VISIBLE);
            bottomCartLayout.setVisibility(View.INVISIBLE);
        }
    }
    public void refreshData(){
        if (dbHelper.getCartData().size() > 0) {
            cartRecyclerView.setVisibility(View.VISIBLE);
            cartAdapter = (AddToCartListAdapter) new AddToCartListAdapter(dbHelper.getCartData(),AddToCartActivity.this,AddToCartActivity.this);
            cartRecyclerView.setAdapter(cartAdapter);
            cartAdapter.notifyDataSetChanged();
            bottomCartLayout.setVisibility(View.VISIBLE);
        }
        else {
            cartRecyclerView.setVisibility(View.GONE);
            emptyCartLayout.setVisibility(View.VISIBLE);
            bottomCartLayout.setVisibility(View.INVISIBLE);
        }
        updateSubtotal();
    }
    private void updateSubtotal()
    {
        double sum = dbHelper.sumOfTotalPrice();
        cartSubTotalPrice.setText(String.valueOf(sum));
    }
}