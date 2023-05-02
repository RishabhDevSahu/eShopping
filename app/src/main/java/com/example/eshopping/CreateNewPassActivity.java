package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateNewPassActivity extends AppCompatActivity {

    ImageView backSymbol;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_pass);

        backSymbol = findViewById(R.id.backSymbol);
        submitButton = findViewById(R.id.submitButton);

        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewPassActivity.super.onBackPressed();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreateNewPassActivity.this, "Forgot Password Successfull!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                CreateNewPassActivity.super.onBackPressed();
            }
        });
    }
}