package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fevziomurtekin.payview.Payview;
import com.fevziomurtekin.payview.data.PayModel;

import java.util.Random;

public class PaymentActivity extends AppCompatActivity {

    ImageView backSymbol;
    Payview payview;
    String cardNo;
    String mobileNo,message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        backSymbol = findViewById(R.id.backSymbol);
        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentActivity.super.onBackPressed();
            }
        });

        int pid = getIntent().getIntExtra("PersonID",0);
        mobileNo = getIntent().getStringExtra("PersonMobileNumber");


        payview = findViewById(R.id.payview);
        payview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        payview.setOnDataChangedListener(new Payview.OnChangelistener() {
            @Override
            public void onChangelistener(PayModel payModel, boolean b) {
                cardNo = payModel.getCardNo();
            }
        });

        Random random = new Random();
        @SuppressLint("DefaultLocale") String otp = String.format("%04d", random.nextInt(10000));
        message = otp + " is the OTP for accessing cards on eShopping. Do not share this anyone.";

        payview.setPayOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(getApplicationContext(),VerificationActivity.class);
                    i.putExtra("CardNumber",cardNo);
                    i.putExtra("PersonID",pid);
                    i.putExtra("otp",otp);
//                    sendSMSMessage();
                    try {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(mobileNo,"eShopping",message,null,null);
                        Toast.makeText(getApplicationContext(),"Otp Sent Successfully!",Toast.LENGTH_LONG).show();
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Otp Not Sent!",Toast.LENGTH_LONG).show();
                    }
                    startActivity(i);
                    PaymentActivity.super.onBackPressed();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Please Fill All Card Details", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

//    protected void sendSMSMessage() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[],  int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(mobileNo, null, message, null, null);
//                    Toast.makeText(getApplicationContext(), "Otp send successfully!",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "Otp Send faild, please try again.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        }
//    }
}