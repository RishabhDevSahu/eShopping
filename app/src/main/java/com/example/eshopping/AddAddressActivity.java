package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddAddressActivity extends AppCompatActivity {

    ImageView backSymbol;
    CardView addNewAddress;
    AddAddressModel[] addAddressModel;
    AddAddressAdapter addAddressAdapter;
    DBHelper dbHelper;
    RecyclerView addAddressRecyclerView;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        dbHelper = new DBHelper(getApplicationContext());
        mauth = FirebaseAuth.getInstance();
        firebaseUser = mauth.getCurrentUser();

        backSymbol = findViewById(R.id.backSymbol);
        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAddressActivity.super.onBackPressed();
            }
        });
        addNewAddress = findViewById(R.id.addNewAddress);
        addNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddNewAddressFormActivity.class);
                startActivity(i);
            }
        });

        addAddressRecyclerView = (RecyclerView) findViewById(R.id.addAddressRecyclerView);
        addAddressRecyclerView.setHasFixedSize(true);
        addAddressRecyclerView.setLayoutManager(new LinearLayoutManager(AddAddressActivity.this,LinearLayoutManager.VERTICAL,false));


        if (dbHelper.getPersonAddress(firebaseUser.getUid()).size() > 0) {
            addAddressRecyclerView.setVisibility(View.VISIBLE);
            addAddressAdapter = (AddAddressAdapter) new AddAddressAdapter(dbHelper.getPersonAddress(firebaseUser.getUid()),AddAddressActivity.this,AddAddressActivity.this);
            addAddressRecyclerView.setAdapter(addAddressAdapter);
            addAddressAdapter.notifyDataSetChanged();

        }
        else {
            addAddressRecyclerView.setVisibility(View.GONE);
        }
    }
    public void refreshData(){
        if (dbHelper.getPersonAddress(firebaseUser.getUid()).size() > 0) {
            addAddressRecyclerView.setVisibility(View.VISIBLE);
            addAddressAdapter = (AddAddressAdapter) new AddAddressAdapter(dbHelper.getPersonAddress(firebaseUser.getUid()),AddAddressActivity.this,AddAddressActivity.this);
            addAddressRecyclerView.setAdapter(addAddressAdapter);
            addAddressAdapter.notifyDataSetChanged();

        }
        else {
            addAddressRecyclerView.setVisibility(View.GONE);
        }
    }
}