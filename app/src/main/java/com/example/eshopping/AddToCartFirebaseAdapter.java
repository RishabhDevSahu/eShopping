//package com.example.eshopping;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.Objects;
//
//public class AddToCartFirebaseAdapter extends FirebaseRecyclerAdapter<AddToCartModel, AddToCartFirebaseAdapter.AddToCartModelViewholder> {
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//
//    DatabaseReference dbRef;
//    Task<Void> dbRef2;
//    FirebaseAuth mauth;
//    FirebaseDatabase firebaseDatabase;
//    FirebaseUser firebaseUser;
//    AddToCartFirebaseAdapter adapter;
//    Query query,query2,removeQuery;
//
//
//    onItemClick onItemClick;
//
//    public void setOnItemClick(onItemClick onItemClick) {
//        this.onItemClick = onItemClick;
//    }
//
//    interface onItemClick {
//        void onClickData(AddToCartModel data);
//    }
//
//    public AddToCartFirebaseAdapter(@NonNull FirebaseRecyclerOptions<AddToCartModel> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull AddToCartFirebaseAdapter.AddToCartModelViewholder holder, int position, @NonNull AddToCartModel model) {
//        Glide.with(holder.itemView)
//                .load(model.getCartImage())
//                .fitCenter()
//                .into(holder.cartListImage);
//        holder.cartListName.setText(model.getCartName());
//        holder.cartListPrice.setText(model.getCartPrice());
//        holder.itemQty.setText(model.getCartQuantity());
//        holder.cartListTotalPrice.setText(model.getCartTotalPrice());
//        holder.cartListTotalPrice.setText(model.getCartTotalPrice());
//
//        mauth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseUser = mauth.getCurrentUser();
//        dbRef = firebaseDatabase.getReference("AddToCart").child(firebaseUser.getUid());
//        query = dbRef;
//        query2 = query.orderByChild("CartQuantity");
//
//
//
//
//        holder.setOnClickListener(new AddToCartModelViewholder.ClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
////                Toast.makeText(view.getContext(), ""+model.getProductID(), Toast.LENGTH_SHORT).show();
//
//                removeQuery = dbRef.orderByChild("ProductID").equalTo(model.getProductID());
//                removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        for (DataSnapshot child : snapshot.getChildren()) {
//                            String key = child.getKey();
//                            Toast.makeText(view.getContext(), ""+key, Toast.LENGTH_SHORT).show();
//                            if(key!=null)
//                            removeQuery.getRef().child(key).setValue(null);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onItemClick2(View view, int position) {
//                query2.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(Integer.parseInt(String.valueOf(query.orderByChild("CartQuantity")))<10){
//                            double price = Double.parseDouble(model.getCartPrice());
//                            int qty = Integer.parseInt(model.getCartQuantity());
//                            Double total = price*qty;
//                            int qty2 = qty+1;
//                            holder.itemQty.setText(String.valueOf(qty2));
//                            holder.cartListTotalPrice.setText(String.valueOf(total));
//                            dbRef2 = dbRef.orderByChild("CartTotalPrice").getRef().setValue(String.valueOf(total));
//                            dbRef2 = dbRef.orderByChild("CartQuantity").getRef().setValue(String.valueOf(qty2));
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onItemClick3(View view, int position) {
//
//            }
//        });
//    }
//
//    @NonNull
//    @Override
//    public AddToCartFirebaseAdapter.AddToCartModelViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_to_cart_recyclerview, parent, false);
//        return new AddToCartFirebaseAdapter.AddToCartModelViewholder(view);
//    }
//
//    public static class AddToCartModelViewholder extends RecyclerView.ViewHolder {
//
//        ImageView cartListImage, cartClose;
//        TextView cartListName, cartListPrice, cartListTotalPrice, itemQty, incItemQty, decItemQty;
//
//
//        public AddToCartModelViewholder(@NonNull View itemView) {
//            super(itemView);
//            this.cartListImage = (ImageView) itemView.findViewById(R.id.cartImage);
//            this.cartListName = (TextView) itemView.findViewById(R.id.cartName);
//            this.cartListPrice = (TextView) itemView.findViewById(R.id.cartPrice);
//            this.cartListTotalPrice = (TextView) itemView.findViewById(R.id.cartTotalPrice);
//            this.itemQty = (TextView) itemView.findViewById(R.id.item_Qty);
//            this.incItemQty = (TextView) itemView.findViewById(R.id.inc_Item_Qty);
//            this.decItemQty = (TextView) itemView.findViewById(R.id.dec_Item_Qty);
//            this.cartClose = (ImageView) itemView.findViewById(R.id.cartClose);
//
//            itemView.findViewById(R.id.cartClose).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mClickListener.onItemClick(v,getAdapterPosition());
//
//                }
//            });
//            itemView.findViewById(R.id.inc_Item_Qty).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mClickListener.onItemClick2(view,getAdapterPosition());
//                }
//            });
//
//            itemView.findViewById(R.id.dec_Item_Qty).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mClickListener.onItemClick3(view,getAdapterPosition());
//                }
//            });
//
//        }
//
//        private AddToCartModelViewholder.ClickListener mClickListener;
//
//        public interface ClickListener {
//            public void onItemClick(View view, int position);
//            public void onItemClick2(View view, int position);
//            public void onItemClick3(View view, int position);
//        }
//
//        public void setOnClickListener(AddToCartModelViewholder.ClickListener clickListener) {
//            mClickListener = clickListener;
//        }
//    }
//}