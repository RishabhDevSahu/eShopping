package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewAddressFormActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    ImageView backSymbol;

    String[] country = { "India", "USA", "China", "Japan", "Germany"};
    Button useThisAddressBuutton;
    EditText fullNameText,mobileNumberText,pinCodeText,addressLineText,landMarkText,cityText,stateText;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address_form);

        mauth = FirebaseAuth.getInstance();
        firebaseUser = mauth.getCurrentUser();

        dbHelper = new DBHelper(getApplicationContext());

        fullNameText = findViewById(R.id.fullNameText);
        mobileNumberText = findViewById(R.id.mobileNumberText);
        pinCodeText= findViewById(R.id.pinCodeText);
        addressLineText= findViewById(R.id.addressLineText);
        landMarkText= findViewById(R.id.landMarkText);
        cityText= findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        useThisAddressBuutton= findViewById(R.id.useThisAddressButton);

        backSymbol = findViewById(R.id.backSymbol);
        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewAddressFormActivity.super.onBackPressed();
            }
        });

        Spinner countrySpin = (Spinner) findViewById(R.id.countrySpinner);
        countrySpin.setOnItemSelectedListener(this);

        ArrayAdapter countryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpin.setAdapter(countryAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),country[i] + " is selected", Toast.LENGTH_LONG).show();

        useThisAddressBuutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = fullNameText.getText().toString();
                String personMobileNo = mobileNumberText.getText().toString();
                String personPinCode = pinCodeText.getText().toString();
                String personAddress = addressLineText.getText().toString();
                String personLandmark = landMarkText.getText().toString();
                String personCity = cityText.getText().toString();
                String personState = stateText.getText().toString();
                String personCountry = adapterView.getItemAtPosition(i).toString();
                if(dbHelper.insertAddressData(firebaseUser.getUid(),personName,personMobileNo,personPinCode,personAddress,personLandmark,personCity,personState,personCountry)){
                    Toast.makeText(AddNewAddressFormActivity.this, "Add New Address Successfull!!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),AddAddressActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AddNewAddressFormActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}