package com.example.eshopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.nio.charset.StandardCharsets;


public class ProfileFragment extends Fragment {


    TextView userName,userMobileNo,userEmail;
    ImageView editProfileIcon,logoutIcon,profileImage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);


        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mauth.getCurrentUser();
        dbRef = firebaseDatabase.getReference("User");
        firebaseStorage = FirebaseStorage.getInstance();

        
        userName = root.findViewById(R.id.userName);
        userMobileNo = root.findViewById(R.id.userMobileNo);
        userEmail = root.findViewById(R.id.userEmail);
        logoutIcon = root.findViewById(R.id.logoutIcon);
        editProfileIcon = root.findViewById(R.id.editProfileIcon);
        profileImage = root.findViewById(R.id.profileImage);



        storageReference = firebaseStorage.getReference("UserProfile").child(firebaseUser.getUid());


        if (firebaseUser != null) {
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child(firebaseUser.getUid()).child("UserName").getValue(String.class);
                    String mobileNo = dataSnapshot.child(firebaseUser.getUid()).child("UserMobileNo").getValue(String.class);
                    String email = dataSnapshot.child(firebaseUser.getUid()).child("UserEmail").getValue(String.class);
                    String userProfilePicture = dataSnapshot.child(firebaseUser.getUid()).child("UserProfilePicture").getValue(String.class);

                    if(name==null && mobileNo==null && email==null){
                        Toast.makeText(root.getContext(), name+" "+mobileNo+" "+email, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userName.setText(name);
                        userMobileNo.setText(mobileNo);
                        userEmail.setText(DecodeEmailString(email));
//                        Picasso.get().load(userProfilePicture).into(profileImage);
                        Glide.with(root.getContext())
                                .load(userProfilePicture)
                                .fitCenter()
                                .into(profileImage);
                    }
                }


                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        else{
            userName.setText("rishuu");
            userMobileNo.setText("4569823562");
            userEmail.setText("rishuu@gmail.com");
            profileImage.setImageResource(R.drawable.avtaricon);
        }


        editProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(),EditProfileActivity.class);
                startActivity(i);
            }
        });

        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth.signOut();
                Toast.makeText(root.getContext(), "Logout Successfull!!", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(root.getContext(),LoginActivity.class);
                startActivity(i2);
            }
        });

        return root;
    }
    public static String DecodeEmailString(String string) {
        return string.replace("bnrv", ".");
    }
}