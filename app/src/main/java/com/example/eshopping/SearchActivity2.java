package com.example.eshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.paulrybitskyi.persistentsearchview.PersistentSearchView;
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchConfirmedListener;
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchQueryChangeListener;
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity2 extends AppCompatActivity{

    PersistentSearchView persistentSearchView;
//    SearchView searchView;
    DatabaseReference dbRef,dbRef2;
    RecyclerView searchRecyclerViewNew,confirmSearchRecyclerViewNew;
    ProductFirebaseAdapter productFirebaseAdapter;
    ArrayList<ProductModel> productModels = new ArrayList<ProductModel>();
    SearchAdapter adapter;
    Query query,query1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

//        searchView = findViewById(R.id.searchView);


        dbRef = FirebaseDatabase.getInstance().getReference("Product");
        searchRecyclerViewNew = (RecyclerView) findViewById(R.id.searchRecyclerViewNew);
        FirebaseRecyclerOptions<ProductModel> productOption = new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(dbRef, ProductModel.class).build();
        searchRecyclerViewNew.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        productFirebaseAdapter = new ProductFirebaseAdapter(productOption);
        searchRecyclerViewNew.setAdapter(productFirebaseAdapter);

        productFirebaseAdapter.setOnItemClick(new ProductFirebaseAdapter.onItemClick() {
            @Override
            public void onClickData(ProductModel data) {
                Intent intent = new Intent(getApplicationContext(), ItemDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ProductID", data.getProductID());
                intent.putExtra("ProductImage", data.getProductImage());
                intent.putExtra("ProductName", data.getProductName());
                intent.putExtra("ProductDiscountPrice", data.getProductDiscountPrice());
                intent.putExtra("ProductOriginalPrice", data.getProductOriginalPrice());
                intent.putExtra("ProductDiscountPercent", data.getProductDiscountPercent());
                intent.putExtra("ProductStockStatus", data.getProductStockStatus());
                intent.putExtra("ProductShortDescription", data.getProductShortDescription());
                getApplicationContext().startActivity(intent);
            }
        });

        persistentSearchView = findViewById(R.id.persistentSearchView);


        persistentSearchView.setOnLeftBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        persistentSearchView.setOnClearInputBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query1 = FirebaseDatabase.getInstance().getReference("Product");
                FirebaseRecyclerOptions<ProductModel> productOption = new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(query1, ProductModel.class).build();
                productFirebaseAdapter = new ProductFirebaseAdapter(productOption);
                productFirebaseAdapter.startListening();
                searchRecyclerViewNew.setAdapter(productFirebaseAdapter);

                productFirebaseAdapter.setOnItemClick(new ProductFirebaseAdapter.onItemClick() {
                    @Override
                    public void onClickData(ProductModel data) {
                        Intent intent = new Intent(getApplicationContext(), ItemDetailsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("ProductID", data.getProductID());
                        intent.putExtra("ProductImage", data.getProductImage());
                        intent.putExtra("ProductName", data.getProductName());
                        intent.putExtra("ProductDiscountPrice", data.getProductDiscountPrice());
                        intent.putExtra("ProductOriginalPrice", data.getProductOriginalPrice());
                        intent.putExtra("ProductDiscountPercent", data.getProductDiscountPercent());
                        intent.putExtra("ProductStockStatus", data.getProductStockStatus());
                        intent.putExtra("ProductShortDescription", data.getProductShortDescription());
                        getApplicationContext().startActivity(intent);
                    }
                });
            }
        });

        persistentSearchView.setOnSearchConfirmedListener(new OnSearchConfirmedListener() {
            @Override
            public void onSearchConfirmed(PersistentSearchView searchView, String query) {
                searchView.collapse();
                prouductFilter(query);
            }
        });

        persistentSearchView.setOnSearchQueryChangeListener(new OnSearchQueryChangeListener() {
            @Override
            public void onSearchQueryChanged(PersistentSearchView searchView, String oldQuery, String newQuery) {

                prouductFilter(newQuery);
            }
        });

        persistentSearchView.setVoiceRecognitionDelegate(new VoiceRecognitionDelegate(this));


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                prouductFilter(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                prouductFilter(s);
//                return false;
//            }
//        });

//        });
    }

    private void prouductFilter(String s) {


        if(s!=null && s.length()>0){
            StringBuilder sb = new StringBuilder(s);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            String s2 =  sb.toString();
            query = FirebaseDatabase.getInstance().getReference("Product").orderByChild("ProductName").startAt(s2).endAt(s2+"\uf8ff");
            FirebaseRecyclerOptions<ProductModel> productOption = new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(query, ProductModel.class).build();
            productFirebaseAdapter = new ProductFirebaseAdapter(productOption);
            productFirebaseAdapter.startListening();
            searchRecyclerViewNew.setAdapter(productFirebaseAdapter);

            productFirebaseAdapter.setOnItemClick(new ProductFirebaseAdapter.onItemClick() {
                @Override
                public void onClickData(ProductModel data) {
                    Intent intent = new Intent(getApplicationContext(), ItemDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("ProductID", data.getProductID());
                    intent.putExtra("ProductImage", data.getProductImage());
                    intent.putExtra("ProductName", data.getProductName());
                    intent.putExtra("ProductDiscountPrice", data.getProductDiscountPrice());
                    intent.putExtra("ProductOriginalPrice", data.getProductOriginalPrice());
                    intent.putExtra("ProductDiscountPercent", data.getProductDiscountPercent());
                    intent.putExtra("ProductStockStatus", data.getProductStockStatus());
                    intent.putExtra("ProductShortDescription", data.getProductShortDescription());
                    getApplicationContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        VoiceRecognitionDelegate.handleResult(persistentSearchView, requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        productFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        productFirebaseAdapter.stopListening();
    }

}