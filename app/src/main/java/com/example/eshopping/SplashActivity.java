package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mauth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = mauth.getCurrentUser();
                if(user == null) {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    Toast.makeText(SplashActivity.this, "Created By Rishabh Dev Sahu", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    SplashActivity.this.finish();
                }
                else{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    Toast.makeText(SplashActivity.this, "Created By Rishabh Dev Sahu", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    SplashActivity.this.finish();
                }

            }

        },1000);

    }
}