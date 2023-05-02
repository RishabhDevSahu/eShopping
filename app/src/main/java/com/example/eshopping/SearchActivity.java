//package com.example.eshopping;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.SearchView;
//import android.widget.Toast;
//
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//
//import java.util.ArrayList;
//
//public class SearchActivity extends AppCompatActivity {
//
//    RecyclerView searchRecyclerView;
//    ProductFirebaseAdapter adapter;
//    ArrayList<ProductModel> searchModalArrayList;
//    DatabaseReference dbRef;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        searchRecyclerView = findViewById(R.id.searchRecyclerView);
//        buildRecyclerView();
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.actionSearch);
//
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }
//
//    private void filter(String text) {
//
//        ArrayList<ProductModel> filteredlist = new ArrayList<>();
//        for (ProductModel item : searchModalArrayList) {
//
//            if (item.getProductName().toLowerCase().contains(text.toLowerCase())) {
//                filteredlist.add(item);
//            }
//        }
//        if (filteredlist.isEmpty()) {
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
//        } else {
//            adapter.filterList(filteredlist);
//        }
//    }
//
//    private void buildRecyclerView() {
//
//        searchModalArrayList = new ArrayList<>();
//
//        dbRef = FirebaseDatabase.getInstance().getReference("Product");
//        Query query = dbRef;
//
//        FirebaseRecyclerOptions<ProductModel> productOption = new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(query, ProductModel.class).build();
//        adapter = new ProductFirebaseAdapter(productOption);
//
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        searchRecyclerView.setHasFixedSize(true);
//        searchRecyclerView.setLayoutManager(manager);
//        searchRecyclerView.setAdapter(adapter);
//    }
//}