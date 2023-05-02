package com.example.eshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CategorySelectedItemActivity extends AppCompatActivity {

    ImageView backSymbol;
    TextView selectedCategoryName;
    CategorySelectedItemModel[] categorySelectedItemModels;
    CategorySelectedItemAdapter categorySelectedItemAdapter;
    ProductModel productModel;
    ProductFirebaseAdapter productFirebaseAdapter;
    RecyclerView productRecyclerView;
    DatabaseReference dbRef,dbRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selected_item);

        selectedCategoryName = findViewById(R.id.selectedCategoryName);
        backSymbol = findViewById(R.id.backSymbol);
        backSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategorySelectedItemActivity.super.onBackPressed();
            }
        });

        String selCategoryName = getIntent().getStringExtra("CategoryName").toString();
        selectedCategoryName.setText(selCategoryName);

        String selCategoryID = getIntent().getStringExtra("CategoryID");

        dbRef = FirebaseDatabase.getInstance().getReference("Product");
        productRecyclerView = findViewById(R.id.selectedCategoryListRecyclerView);

        Query query=dbRef.orderByChild("CategoryID").equalTo(selCategoryID);

        FirebaseRecyclerOptions<ProductModel> productOption = new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(query, ProductModel.class).build();
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        productFirebaseAdapter = new ProductFirebaseAdapter(productOption);
        productRecyclerView.setAdapter(productFirebaseAdapter);

        String msg = "Currently Unavailable";


        productFirebaseAdapter.setOnItemClick(new ProductFirebaseAdapter.onItemClick(){
            @Override
            public void onClickData(ProductModel data) {
                Intent intent = new Intent(getApplicationContext(), ItemDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ProductID",data.getProductID());
                intent.putExtra("ProductImage",data.getProductImage());
                intent.putExtra("ProductName",data.getProductName());
                intent.putExtra("ProductDiscountPrice",data.getProductDiscountPrice());
                intent.putExtra("ProductOriginalPrice",data.getProductOriginalPrice());
                intent.putExtra("ProductDiscountPercent",data.getProductDiscountPercent());
                if(Integer.parseInt(data.getProductStock())>0){
                    intent.putExtra("ProductStockStatus","In Stock");
                }else{
                    intent.putExtra("ProductStockStatus",msg);
                }
                intent.putExtra("ProductShortDescription",data.getProductShortDescription());
                getApplicationContext().startActivity(intent);
            }
        });

        
    }


    @Override
    public void onStart()
    {
        super.onStart();
        productFirebaseAdapter.startListening();
    }


    @Override
    public void onStop()
    {
        super.onStop();
        productFirebaseAdapter.stopListening();
    }
}