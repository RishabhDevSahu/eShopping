package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sn.lib.NestedProgress;

public class ForgotPassActivity extends AppCompatActivity {

    ImageView backSymbol;
    Button sendButton;
    EditText emailTxt;
    NestedProgress nestedProgress;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);


        auth = FirebaseAuth.getInstance();

        backSymbol = findViewById(R.id.backSymbol);
        emailTxt = findViewById(R.id.emailTxt);
        sendButton = findViewById(R.id.sendButton);
        nestedProgress = findViewById(R.id.nestedProgress);


        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPassActivity.super.onBackPressed();
            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                nestedProgress.setVisibility(View.INVISIBLE);
                sendButton.setVisibility(View.VISIBLE);
                ForgotPassActivity.super.onBackPressed();
            }
        });

    }

    private void validateEmail() {
        sendButton.setEnabled(true);
        String email = emailTxt.getText().toString();


        if (TextUtils.isEmpty(email)) {
            emailTxt.setError("Can't be Empty");
            emailTxt.requestFocus();
        } else {
            nestedProgress.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.INVISIBLE);
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPassActivity.this, "Email Sent Successfully!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(ForgotPassActivity.this, "Email Not Sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}