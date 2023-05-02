package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.storage.UploadTask;
import com.sn.lib.NestedProgress;
import com.squareup.picasso.Picasso;

import java.net.URI;


public class EditProfileActivity extends AppCompatActivity {

    Button updateProfileButton;
    ImageView backSymbol,profilePicture,verifyEmailIcon;
    EditText editUserName, editUserMobileNo , editUserEmail;
    TextView changeProfilePicture;
    NestedProgress nestedProgress;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    Task<Void> dbRef2;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
    int SELECT_IMAGE = 200;
    DBHelper dbHelper;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mauth.getCurrentUser();
        dbRef = firebaseDatabase.getReference("User");
        dbHelper = new DBHelper(getApplicationContext());
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("UserProfile").child(firebaseUser.getUid());
//        firebaseStorage = storageReference.getStorage();

        backSymbol = findViewById(R.id.backSymbol);
        verifyEmailIcon = findViewById(R.id.verifyEmailIcon);
        profilePicture = findViewById(R.id.profilePicture);
        editUserName = findViewById(R.id.editUserName);
        editUserMobileNo = findViewById(R.id.editUserMobileNo);
        editUserEmail = findViewById(R.id.editUserEmail);
        changeProfilePicture = findViewById(R.id.changeProfilePicture);
        nestedProgress = findViewById(R.id.nestedProgress);


        changeProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });


        if (firebaseUser != null) {
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child(firebaseUser.getUid()).child("UserName").getValue(String.class);
                    String mobileNo = dataSnapshot.child(firebaseUser.getUid()).child("UserMobileNo").getValue(String.class);
                    String email = dataSnapshot.child(firebaseUser.getUid()).child("UserEmail").getValue(String.class);
                    String userProfilePicture = dataSnapshot.child(firebaseUser.getUid()).child("UserProfilePicture").getValue(String.class);

                    firebaseUser.sendEmailVerification().addOnCompleteListener(EditProfileActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                verifyEmailIcon.setVisibility(View.VISIBLE);
//                                Toast.makeText(EditProfileActivity.this, "Verification email sent to " + firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                            } else {
//                                Toast.makeText(EditProfileActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    if(name==null && mobileNo==null && email==null){
//                        Toast.makeText(getApplicationContext(), name+" "+mobileNo+" "+email, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editUserName.setText(name);
                        editUserMobileNo.setText(mobileNo);
                        editUserEmail.setText(DecodeEmailString(email));
//                        Picasso.get().load(userProfilePicture).into(profilePicture);
                        Glide.with(getApplicationContext())
                                .load(userProfilePicture)
                                .fitCenter()
                                .into(profilePicture);
                    }
                }


                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        else{
            editUserName.setText("rishuu");
            editUserMobileNo.setText("4569823562");
            editUserEmail.setText("rishuu@gmail.com");
        }


        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfileActivity.super.onBackPressed();
            }
        });

        updateProfileButton = findViewById(R.id.updateProfileButton);
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nestedProgress.setVisibility(View.VISIBLE);
                updateProfileButton.setVisibility(View.INVISIBLE);
                if(firebaseUser != null){
                    dbRef2 = dbRef.child(firebaseUser.getUid()).child("UserName").setValue(editUserName.getText().toString());
                    dbRef2 = dbRef.child(firebaseUser.getUid()).child("UserMobileNo").setValue(editUserMobileNo.getText().toString());
                    dbRef2 = dbRef.child(firebaseUser.getUid()).child("UserEmail").setValue(EncodeEmailString(editUserEmail.getText().toString()));

                    Toast.makeText(EditProfileActivity.this, "Profile Updated Succesfully!!", Toast.LENGTH_SHORT).show();
                    EditProfileActivity.super.onBackPressed();
                }
                else{
                    Toast.makeText(EditProfileActivity.this, "Profile Not Updated Successfully!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_IMAGE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_IMAGE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    profilePicture.setImageURI(selectedImageUri);
                    profilePicture.setDrawingCacheEnabled(true);
                    profilePicture.buildDrawingCache();
                    Bitmap bitmap = ((BitmapDrawable) profilePicture.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data2 = baos.toByteArray();

                    UploadTask uploadTask = storageReference.putBytes(data2);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();

                                    dbRef2 = dbRef.child(firebaseUser.getUid()).child("UserProfilePicture").setValue(url);
                                }
                            });



                        }
                    });
                }
            }
        }
    }

    public static String EncodeEmailString(String string) {
        return string.replace(".", "bnrv");
    }
    public static String DecodeEmailString(String string) {
        return string.replace("bnrv", ".");
    }
}