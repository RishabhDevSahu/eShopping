package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sn.lib.NestedProgress;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView signUp, forgotPass;
    EditText emailTxt, passwordTxt;
    NestedProgress nestedProgress;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("User");

        loginButton = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUp);
        forgotPass = findViewById(R.id.forgotPass);
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        nestedProgress = findViewById(R.id.nestedProgress);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassActivity.class);
                startActivity(i);
            }
        });


    }
    public static String EncodeEmailString(String string) {
        return string.replace(".", "bnrv");
    }

    public static String DecodeEmailString(String string) {
        return string.replace("bnrv", ".");
    }

    private void validateLogin() {
        loginButton.setEnabled(true);

        String email = emailTxt.getText().toString();
        String pass = passwordTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailTxt.setError("Can't be Empty");
            emailTxt.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            passwordTxt.setError("Can't be Empty");
            passwordTxt.requestFocus();
        } else {
            if (dbRef == null) {
                Toast.makeText(LoginActivity.this, "Invalid Email Id & Password", Toast.LENGTH_SHORT).show();
            } else {
                nestedProgress.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.INVISIBLE);
                mauth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            try{
                                Toast.makeText(LoginActivity.this, "Sign in Successfull!", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mauth.getCurrentUser();
                                mauth.updateCurrentUser(user);
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }catch (Exception e){
                                Toast.makeText(LoginActivity.this, "Invalid User ID & Password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Invalid User ID & Password", Toast.LENGTH_SHORT).show();
                            nestedProgress.setVisibility(View.INVISIBLE);
                            loginButton.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }

        }
    }
}

