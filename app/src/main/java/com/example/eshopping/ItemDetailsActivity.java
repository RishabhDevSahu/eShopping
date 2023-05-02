package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ItemDetailsActivity extends AppCompatActivity {

    ImageView backSymbol,imageD,addToCart,wishListImg;
    TextView nameD,priceD,origPriceD,discountD,qtyText,stockAvalibility,prDescription;
    Button addToCartButton;
    DBHelper dbHelper;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        imageD = findViewById(R.id.imageD);
        nameD = findViewById(R.id.nameD);
        priceD = findViewById(R.id.priceD);
        origPriceD = findViewById(R.id.origPriceD);
        discountD = findViewById(R.id.discountD);
        backSymbol = findViewById(R.id.backSymbol2);
        addToCart = findViewById(R.id.addToCartImg);
        wishListImg = findViewById(R.id.favoriteImg2);
        addToCartButton = findViewById(R.id.addToCartButton);
        qtyText = findViewById(R.id.qtyText);
        stockAvalibility = findViewById(R.id.stockAvailibility);
        prDescription = findViewById(R.id.prDescription);

        mauth = FirebaseAuth.getInstance();
        firebaseUser = mauth.getCurrentUser();

        dbHelper = new DBHelper(getApplicationContext());

        if(dbHelper.getQuantity()){
            try{
                long qty = dbHelper.getQuantityValue();
                Log.d("Rishabh", String.valueOf(qty));
                qtyText.setVisibility(View.VISIBLE);
                qtyText.setText(String.valueOf(qty));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            qtyText.setVisibility(View.INVISIBLE);
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddToCartActivity.class);
                startActivity(i);
            }
        });

        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDetailsActivity.super.onBackPressed();
            }
        });

        String id = getIntent().getStringExtra("ProductID");
        String img = getIntent().getStringExtra("ProductImage");
        Glide.with(getApplicationContext())
                .load(img)
                .centerCrop()
                .error(R.drawable.eshoppinglogo)
                .into(imageD);

        String name = getIntent().getStringExtra("ProductName");
        nameD.setText(name);
        String discountPrice = getIntent().getStringExtra("ProductDiscountPrice");
        priceD.setText(discountPrice);
        String originalPrice = getIntent().getStringExtra("ProductOriginalPrice");
        origPriceD.setText(originalPrice);
        String discountPercent = getIntent().getStringExtra("ProductDiscountPercent");
        discountD.setText(discountPercent);
        String stockStatus = getIntent().getStringExtra("ProductStockStatus");
        stockAvalibility.setText(stockStatus);
        String shortDescription = getIntent().getStringExtra("ProductShortDescription");
        prDescription.setText(shortDescription);


        wishListImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dbHelper.checkWishListID(id)){
                    Toast.makeText(ItemDetailsActivity.this, "Item Already Added In Favorite", Toast.LENGTH_SHORT).show();
                }else{
                    if(dbHelper.insertWishListData(id,firebaseUser.getUid(),img,name,discountPrice,originalPrice,discountPercent,stockStatus,shortDescription)){

                        Toast.makeText(ItemDetailsActivity.this, "Item Added To Favorite", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper.checkAddToCartID(id)) {
                    dbHelper.increaseCart(Integer.parseInt(id));
                    Toast.makeText(ItemDetailsActivity.this, "Item Added To Cart", Toast.LENGTH_SHORT).show();
                    refreshQty();
                } else {
                    if (dbHelper.insertCartData(id,firebaseUser.getUid(), img, name, discountPrice,originalPrice,discountPercent, String.valueOf('1'), String.valueOf('1'))) {
                        Toast.makeText(ItemDetailsActivity.this, "Item Added To Cart", Toast.LENGTH_SHORT).show();
                        refreshQty();
                    }
                }
            }
        });

    }
    public void refreshQty(){
        if(dbHelper.getQuantity()){
            try{
                long qty = dbHelper.getQuantityValue();
                Log.d("Rishabh", String.valueOf(qty));
                qtyText.setVisibility(View.VISIBLE);
                qtyText.setText(String.valueOf(qty));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            qtyText.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshQty();
    }
}