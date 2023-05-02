package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class VerificationActivity extends AppCompatActivity {

    ImageView backSymbol;
    Button verfiyButton;
    EditText et1,et2,et3,et4;
    DBHelper dbHelper;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef,productDBRef;
    DatabaseReference dbRef2,dbRef4,dbRef5;
    Task<Void> dbRef3;
    String productStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        backSymbol = findViewById(R.id.backSymbol);
        verfiyButton = findViewById(R.id.verifyButton);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);

        dbHelper = new DBHelper(getApplicationContext());
        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mauth.getCurrentUser();

        String cardNo = getIntent().getStringExtra("CardNumber");
        int pid = getIntent().getIntExtra("PersonID",0);
        String otp = getIntent().getStringExtra("otp");

        int card = Integer.parseInt(cardNo.substring(cardNo.length() - 4, cardNo.length()));

        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationActivity.super.onBackPressed();
            }
        });

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et1.getText().toString().length()==1)
                {
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et2.getText().toString().length()==1)
                {
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et3.getText().toString().length()==1)
                {
                    et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et4.getText().toString().length()==1)
                {
                    verfiyButton.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et2.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et1.getText().toString().length()==1)
                {
                    verfiyButton.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                et1.requestFocus();
            }
        });

        et3.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et2.getText().toString().length()==1)
                {
                    verfiyButton.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                et2.requestFocus();
            }
        });

        et4.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et3.getText().toString().length()==1)
                {
                    verfiyButton.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                et3.requestFocus();
            }
        });

        verfiyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateET1()) {
                    if (validateET2()) {
                        if (validateET3()) {
                            if (validateET4()) {

                                String ET1 = et1.getText().toString();
                                String ET2 = et2.getText().toString();
                                String ET3 = et3.getText().toString();
                                String ET4 = et4.getText().toString();

                                String otp2 = ET1 + ET2 + ET3 + ET4;

                                if(otp2.equals(otp)){

                                    try{

                                        int randomID = new Random().nextInt(9000) + 1000;

                                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z");
                                        String currentDateandTime = sdf.format(new Date());
                                        String total = String.valueOf(dbHelper.sumOfTotalPrice());

                                        ArrayList<AddAddressModel> getAddress = dbHelper.getPersonAdd(pid);

                                        String address = getAddress.get(0).getPersonLandMark() + " " + getAddress.get(0).getPersonAddress();

                                        dbRef = firebaseDatabase.getReference("Orders");
                                        dbRef2 = dbRef.child(firebaseUser != null ? firebaseUser.getUid() : null).push();
                                        dbRef3 = dbRef2.child("OrderRandomID").setValue(String.valueOf(randomID));
                                        dbRef3 = dbRef2.child("OrderID").setValue(dbRef2.getKey());
                                        dbRef3 = dbRef2.child("PersonName").setValue(getAddress.get(0).getPersonName());
                                        dbRef3 = dbRef2.child("PersonMobileNumber").setValue(getAddress.get(0).getPersonMobileNumber());
                                        dbRef3 = dbRef2.child("PersonAddress").setValue(address);
                                        dbRef3 = dbRef2.child("PersonCity").setValue(getAddress.get(0).getPersonCity());
                                        dbRef3 = dbRef2.child("PersonState").setValue(getAddress.get(0).getPersonState());
                                        dbRef3 = dbRef2.child("PersonPinCode").setValue(getAddress.get(0).getPersonPinCode());
                                        dbRef3 = dbRef2.child("OrderTotalAmount").setValue(total);
                                        dbRef3 = dbRef2.child("CardNumber").setValue(String.valueOf(card));
                                        dbRef3 = dbRef2.child("OrderDate").setValue(currentDateandTime);
                                        dbRef3 = dbRef2.child("OrderStatus").setValue("Completed");
                                        dbRef4 = dbRef2.child("OrderDetails");

                                        productDBRef = firebaseDatabase.getReference("Product");

                                        ArrayList<OrderDetailsModel> getOrderDetails = dbHelper.getProductOrderDetails();


                                        for (int i = 0; i < getOrderDetails.toArray().length; i++) {
                                            dbRef5 = dbRef4.push();
                                            dbRef3 = dbRef5.child("OrderID").setValue(dbRef2.getKey());
                                            productDBRef.child(getOrderDetails.get(i).getProductID()).child("ProductStock").addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    productStock = snapshot.getValue(String.class);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                            int stock = Integer.parseInt(productStock)-Integer.parseInt(getOrderDetails.get(i).getProductQuantity());
                                            productDBRef.child(getOrderDetails.get(i).getProductID()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    snapshot.getRef().child("ProductStock").setValue(String.valueOf(stock));
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });

                                            Log.d("Hello",String.valueOf(stock));
                                            dbRef3 = dbRef5.child("ProductID").setValue(getOrderDetails.get(i).getProductID());
                                            dbRef3 = dbRef5.child("ProductName").setValue(getOrderDetails.get(i).getProductName());
                                            dbRef3 = dbRef5.child("ProductPrice").setValue(getOrderDetails.get(i).getProductPrice());
                                            dbRef3 = dbRef5.child("ProductQuantity").setValue(getOrderDetails.get(i).getProductQuantity());
                                            dbRef3 = dbRef5.child("ProductImage").setValue(getOrderDetails.get(i).getProductImage());
                                            dbRef3 = dbRef5.child("ProductTotalPrice").setValue(getOrderDetails.get(i).getProductTotalPrice());
                                        }

                                        Intent i = new Intent(getApplicationContext(),PaymentSuccessfullActivity.class);
                                        i.putExtra("OrderID",dbRef2.getKey());
                                        startActivity(i);
                                        VerificationActivity.super.onBackPressed();

                                    }catch (Exception e){
//                                    Toast.makeText(VerificationActivity.this, "Some Error Occurred.", Toast.LENGTH_SHORT).show();
                                    }

                                }else{
                                    Toast.makeText(VerificationActivity.this, "Invalid OTP! You have entered wrong OTP", Toast.LENGTH_SHORT).show();
                                }




                            }
                        }
                    }
                }
            }
        });


    }

    private boolean validateET4() {
        verfiyButton.setEnabled(true);
        String eT4 = et4.getText().toString();
        if(eT4.isEmpty())
        {
            et4.setError("Can't be Empty");
            et4.requestFocus();
            return false;
        }
        else{
            et4.setError(null);
            return true;
        }
    }

    private boolean validateET3() {
        verfiyButton.setEnabled(true);
        String eT3 = et3.getText().toString();
        if(eT3.isEmpty())
        {
            et3.setError("Can't be Empty");
            et3.requestFocus();
            return false;
        }
        else{
            et3.setError(null);
            return true;
        }
    }

    private boolean validateET2() {
        verfiyButton.setEnabled(true);
        String eT2 = et2.getText().toString();
        if(eT2.isEmpty())
        {
            et2.setError("Can't be Empty");
            et2.requestFocus();
            return false;
        }
        else{
            et2.setError(null);
            return true;
        }
    }

    private boolean validateET1() {
        verfiyButton.setEnabled(true);
        String eT1 = et1.getText().toString();
        if(eT1.isEmpty())
        {
            et1.setError("Can't be Empty");
            et1.requestFocus();
            return false;
        }
        else{
            et1.setError(null);
            return true;
        }
    }
}