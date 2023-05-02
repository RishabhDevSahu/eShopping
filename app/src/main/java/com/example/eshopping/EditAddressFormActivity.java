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

public class EditAddressFormActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    ImageView backSymbol;

    String[] country = { "India", "USA", "China", "Japan", "Germany"};
    Button useThisAddressBuutton;
    EditText fullNameText,mobileNumberText,pinCodeText,addressLineText,landMarkText,cityText,stateText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address_form);

        dbHelper = new DBHelper(getApplicationContext());

        fullNameText = findViewById(R.id.fullNameText);
        mobileNumberText = findViewById(R.id.mobileNumberText);
        pinCodeText= findViewById(R.id.pinCodeText2);
        addressLineText= findViewById(R.id.addressLineText);
        landMarkText= findViewById(R.id.landMarkText);
        cityText= findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        useThisAddressBuutton= findViewById(R.id.useThisAddressBuutton2);



        backSymbol = findViewById(R.id.backSymbol);
        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditAddressFormActivity.super.onBackPressed();
            }
        });

        Spinner countrySpin = (Spinner) findViewById(R.id.countrySpinner);
        countrySpin.setOnItemSelectedListener(this);

        ArrayAdapter countryAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpin.setAdapter(countryAdapter);


        String personName = getIntent().getStringExtra("PersonName");
        fullNameText.setText(personName);
        String personMobileNo = getIntent().getStringExtra("PersonMobileNumber");
        mobileNumberText.setText(personMobileNo);
        String personPinCode = getIntent().getStringExtra("PersonPinCode");
        pinCodeText.setText(personPinCode);
        String personAddress = getIntent().getStringExtra("PersonAddress");
        addressLineText.setText(personAddress);
        String personLandmark = getIntent().getStringExtra("PersonLandmark");
        landMarkText.setText(personLandmark);
        String personCity = getIntent().getStringExtra("PersonCity");
        cityText.setText(personCity);
        String personState = getIntent().getStringExtra("PersonState");
        stateText.setText(personState);





    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),country[i] + " is selected", Toast.LENGTH_LONG).show();
        useThisAddressBuutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int personID = getIntent().getIntExtra("PersonID",0);
                String personNameText = fullNameText.getText().toString();
                String personMobileNoText = mobileNumberText.getText().toString();
                String personPinCodeText = pinCodeText.getText().toString();
                String personAddressText = addressLineText.getText().toString();
                String personLandmarkText = landMarkText.getText().toString();
                String personCityText = cityText.getText().toString();
                String personStateText = stateText.getText().toString();
                String personCountryText = adapterView.getItemAtPosition(i).toString();
                if(dbHelper.updateAddressData(personID,personNameText,personMobileNoText,personPinCodeText,personAddressText,personLandmarkText,personCityText,personStateText,personCountryText)){
                    Toast.makeText(EditAddressFormActivity.this, "Edit Address Successfull!!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),AddAddressActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(EditAddressFormActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}