package com.example.eshopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FavoriteFragment extends Fragment {

    DBHelper dbHelper;
    RecyclerView wishListRecyclerView;
    WishListAdapter adapter;
    RelativeLayout noFavoriteImg;
    FirebaseAuth mauth;
    FirebaseUser firebaseUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_favorite, null);

        dbHelper = new DBHelper(root.getContext());
        mauth = FirebaseAuth.getInstance();
        firebaseUser = mauth.getCurrentUser();

        noFavoriteImg = root.findViewById(R.id.noFavoriteImg);
        wishListRecyclerView = (RecyclerView) root.findViewById(R.id.wishListRecyclerView);
        Log.d("Adapter","hi"+adapter);
        wishListRecyclerView.setHasFixedSize(true);
        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));

        if (dbHelper.getWishListModel().size() > 0) {
            wishListRecyclerView.setVisibility(View.VISIBLE);
            adapter = new WishListAdapter(dbHelper.getWishListModel(),this,root.getContext());
            wishListRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else {
            wishListRecyclerView.setVisibility(View.GONE);
            noFavoriteImg.setVisibility(View.VISIBLE);
        }

        return root;
    }
    public void refreshData() {
        if (dbHelper.getWishListModel().size() > 0) {
            wishListRecyclerView.setVisibility(View.VISIBLE);
            adapter = new WishListAdapter(dbHelper.getWishListModel(),this,getContext());
            wishListRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else {
            wishListRecyclerView.setVisibility(View.GONE);
            noFavoriteImg.setVisibility(View.VISIBLE);
        }
    }
}