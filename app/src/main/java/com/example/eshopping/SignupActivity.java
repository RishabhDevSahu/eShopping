package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sn.lib.NestedProgress;

public class SignupActivity extends AppCompatActivity {

    ImageView backSymbol;
    TextView nameTxt,mobileNoTxt,addressTxt,emailTxt,passwordTxt,confirmPasswordTxt,signIn;
    Button signUpButton;
    NestedProgress nestedProgress;
    FirebaseAuth mauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    DatabaseReference dbRef2;
    Task<Void> dbRef3;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        backSymbol = findViewById(R.id.backSymbol);
        nameTxt = findViewById(R.id.nameTxt);
        mobileNoTxt = findViewById(R.id.mobileNoTxt);
        addressTxt = findViewById(R.id.addressTxt);
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        confirmPasswordTxt = findViewById(R.id.confirmPasswordTxt);
        signIn = findViewById(R.id.signIn);
        signUpButton = findViewById(R.id.signUpButton);
        nestedProgress = findViewById(R.id.nestedProgress);

        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupActivity.super.onBackPressed();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                SignupActivity.super.onBackPressed();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUserName()){
                    if(validateUserMobileNumber()){
                        if(validateUserAddress()){
                            if(validateUserEmailPass()){
                                    if(validateUserConfirmPassword()){
                                        Toast.makeText(SignupActivity.this, "SignUp Successfull!", Toast.LENGTH_SHORT).show();
                                        Intent i2 = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(i2);
                                        SignupActivity.super.onBackPressed();
                                    }
                            }
                        }
                    }
                }
            }
        });
    }
    public static String EncodeEmailString(String string) {
        return string.replace(".", "bnrv");
    }

    public static String DecodeEmailString(String string) {
        return string.replace("bnrv", ".");
    }

    private boolean validateUserConfirmPassword() {
        signUpButton.setEnabled(true);
        String passconfirm = confirmPasswordTxt.getText().toString();
        String confrmpass = passwordTxt.getText().toString();
        if (passconfirm.isEmpty()) {
            confirmPasswordTxt.setError("Can't be Empty");
            confirmPasswordTxt.requestFocus();
            return false;

        } else {
            if (passconfirm.equals(confrmpass)) {
                return true;
            } else {
                confirmPasswordTxt.setError("Retype Password");
                return false;
            }

        }
    }

    private boolean validateUserEmailPass() {
        signUpButton.setEnabled(true);
        String email = emailTxt.getText().toString();
        String pass = passwordTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailTxt.setError("Can't be Empty");
            emailTxt.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            passwordTxt.setError("Can't be Empty");
            passwordTxt.requestFocus();
        } else {
            nestedProgress.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.INVISIBLE);
            mauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        firebaseUser = mauth.getCurrentUser();
                        dbRef = firebaseDatabase.getReference("User");
                        dbRef2 = dbRef.child(firebaseUser != null ? firebaseUser.getUid() : null);
                        dbRef3 = dbRef2.child("UserName").setValue(nameTxt.getText().toString());
                        dbRef3 = dbRef2.child("UserMobileNo").setValue(mobileNoTxt.getText().toString());
                        dbRef3 = dbRef2.child("UserAddress").setValue(addressTxt.getText().toString());
                        dbRef3 = dbRef2.child("UserEmail").setValue(EncodeEmailString(emailTxt.getText().toString()));
                        dbRef3 = dbRef2.child("UserPassword").setValue(passwordTxt.getText().toString());
                        dbRef3 = dbRef2.child("UserConfirmPassword").setValue(confirmPasswordTxt.getText().toString());
                        dbRef3 = dbRef2.child("UserProfilePicture").setValue("image");
                        mauth.updateCurrentUser(firebaseUser);

                    }
                    else{
//                        Toast.makeText(getApplicationContext(), "Signup Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        emailTxt.setError("Invalid Email Id & Password");
                        nestedProgress.setVisibility(View.INVISIBLE);
                        signUpButton.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        return true;
    }

    private boolean validateUserAddress() {
        signUpButton.setEnabled(true);
        String address = addressTxt.getText().toString();
        if(address.isEmpty())
        {
            addressTxt.setError("Can't be Empty");
            addressTxt.requestFocus();
            return false;
        }
        else{
            addressTxt.setError(null);
            return true;
        }
    }

    private boolean validateUserMobileNumber() {
        signUpButton.setEnabled(true);
        String mobileNo = mobileNoTxt.getText().toString();
        String MobilePattern = "[6-9][0-9]{9}";
        if(mobileNo.matches(MobilePattern)) {
            return true;

        } else{
            mobileNoTxt.setError("Please enter valid 10 digit mobile number");
            return false;
        }
    }

    private boolean validateUserName() {
        signUpButton.setEnabled(true);
        String inputName = nameTxt.getText().toString();
        if(inputName.isEmpty())
        {
            nameTxt.setError("Can't be Empty");
            nameTxt.requestFocus();
            return false;
        }
        else{
            nameTxt.setError(null);
            return true;
        }
    }
}