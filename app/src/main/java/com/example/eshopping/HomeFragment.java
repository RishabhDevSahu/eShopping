package com.example.eshopping;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    CardView searchCard;
    ImageView addToCartImg;
    TextView qtyText,moveRightCategory,moveRightTrendingOffer;
    Context context;
    SliderView sliderView;
    SliderAdapter sliderAdapter;
    List<SliderItem> sliderItemList;
    MySQConnection mySQConnection;
    RecyclerView categoryRecyclerView,trendingOfferRecyclerView;
    DBHelper dbHelper;
    CategoryFirebaseAdapter categoryAdapter;
    TrendingOfferFirebaseAdapter trendingOfferAdapter;
    DatabaseReference dbRef,dbRef2;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;
//    CategoryAdapter adapter;
//    ArrayList<CategoryModel> categoryModels;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);
        context = root.getContext();
        dbHelper = new DBHelper(root.getContext());
        mauth = FirebaseAuth.getInstance();
        firebaseUser = mauth.getCurrentUser();

        qtyText = root.findViewById(R.id.qtyText);
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

        addToCartImg =root.findViewById(R.id.addToCartImg);
        addToCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(),AddToCartActivity.class);
                startActivity(i);
            }
        });

        searchCard = root.findViewById(R.id.searchCard);
        searchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(root.getContext(),SearchActivity2.class);
                startActivity(i2);
            }
        });


        sliderAdapter = new SliderAdapter(root.getContext());

        searchCard = root.findViewById(R.id.searchCard);
        sliderView = root.findViewById(R.id.imgSlider);

        sliderAdapter.addItem(new SliderItem("","https://i.pinimg.com/originals/ef/81/00/ef8100cfcf584de09f75a1c3944e22b3.jpg"));
        sliderAdapter.addItem(new SliderItem("","https://www.bigcmobiles.com/media/slidebanner/m/a/main_16.jpg"));
        sliderAdapter.addItem(new SliderItem("","https://previews.123rf.com/images/elenabsl/elenabsl2001/elenabsl200100005/137269692-grocery-shopping-promotional-sale-banner-fast-shopping-cart-full-of-fresh-colorful-food.jpg"));
        sliderAdapter.addItem(new SliderItem("","https://www.bigcmobiles.com/media/slidebanner/r/e/reno8pro.jpg"));
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();



        dbRef = FirebaseDatabase.getInstance().getReference("Category");
        categoryRecyclerView = root.findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<CategoryModel> categoryOptions = new FirebaseRecyclerOptions.Builder<CategoryModel>().setQuery(dbRef, CategoryModel.class).build();
        categoryAdapter = new CategoryFirebaseAdapter(categoryOptions);
        categoryRecyclerView.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClick(new CategoryFirebaseAdapter.onItemClick(){
            @Override
            public void onClickData(CategoryModel data) {
                Log.e("ID",String.valueOf(data.getCategoryID()));
                Intent intent = new Intent(root.getContext(), CategorySelectedItemActivity.class);
                intent.putExtra("CategoryName", data.getCategoryName());
                intent.putExtra("CategoryID",data.getCategoryID());
                context.startActivity(intent);

            }
        });


        moveRightCategory = root.findViewById(R.id.moveRightCategory);
        moveRightCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryRecyclerView.smoothScrollBy(220,20);
            }
        });


        dbRef2 = FirebaseDatabase.getInstance().getReference("TrendingOffer");
        trendingOfferRecyclerView = root.findViewById(R.id.trendingOffersRecyclerView);
        trendingOfferRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<TrendingOfferModel> trendingOfferOptions = new FirebaseRecyclerOptions.Builder<TrendingOfferModel>().setQuery(dbRef2, TrendingOfferModel.class).build();
        trendingOfferAdapter = new TrendingOfferFirebaseAdapter(trendingOfferOptions);
        trendingOfferRecyclerView.setAdapter(trendingOfferAdapter);

        trendingOfferAdapter.setOnItemClick(new TrendingOfferFirebaseAdapter.onItemClick(){
            @Override
            public void onClickData(TrendingOfferModel data) {
                Intent intent = new Intent(root.getContext(), CategorySelectedItemActivity.class);
                intent.putExtra("CategoryName", data.getTrendOfferName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



        moveRightTrendingOffer = root.findViewById(R.id.moveRightTrendingOffer);
        moveRightTrendingOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trendingOfferRecyclerView.smoothScrollBy(220,20);
            }
        });


        return root;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        categoryAdapter.startListening();
        trendingOfferAdapter.startListening();
    }


    @Override
    public void onStop()
    {
        super.onStop();
        categoryAdapter.stopListening();
        trendingOfferAdapter.stopListening();
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
    public void onResume() {
        super.onResume();
        refreshQty();
    }


}