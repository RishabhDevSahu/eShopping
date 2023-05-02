package com.example.eshopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder>{

//    private final AddAddressModel[] listData;
    private ArrayList<AddAddressModel> listData;
    AddAddressActivity addAddressActivity;
    Context context;
    DBHelper dbHelper;


    public AddAddressAdapter(ArrayList<AddAddressModel> listData,AddAddressActivity addAddressActivity,Context context) {
        this.listData = listData;
        this.context = context;
        this.addAddressActivity = addAddressActivity;
        dbHelper = new DBHelper(context);
    }



    @NonNull
    @Override
    public AddAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.add_address_recyclerview,parent,false);
        AddAddressAdapter.ViewHolder viewHolder = new AddAddressAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddAddressAdapter.ViewHolder holder, int position) {
        final AddAddressModel addAddressModel = listData.get(position);

        holder.personName.setText(addAddressModel.getPersonName());
        holder.personAddress.setText(addAddressModel.getPersonAddress());
        holder.personLandMark.setText(addAddressModel.getPersonLandMark());
        holder.personCity.setText(addAddressModel.getPersonCity());
        holder.personState.setText(addAddressModel.getPersonState());
        holder.personPinCode.setText(addAddressModel.getPersonPinCode());
        holder.personCountry.setText(addAddressModel.getPersonCountry());
        holder.personMobileNumber.setText(addAddressModel.getPersonMobileNumber());
        holder.deliverAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,PaymentActivity.class);
                i.putExtra("PersonID",addAddressModel.getPersonID());
                i.putExtra("PersonMobileNumber",addAddressModel.getPersonMobileNumber());
                context.startActivity(i);
                ((Activity) context).finish();
            }
        });
        holder.editAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,EditAddressFormActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("PersonID",addAddressModel.getPersonID());
                i.putExtra("PersonName",addAddressModel.getPersonName());
                i.putExtra("PersonMobileNumber",addAddressModel.getPersonMobileNumber());
                i.putExtra("PersonPinCode",addAddressModel.getPersonPinCode());
                i.putExtra("PersonAddress",addAddressModel.getPersonAddress());
                i.putExtra("PersonLandmark",addAddressModel.getPersonLandMark());
                i.putExtra("PersonCity",addAddressModel.getPersonCity());
                i.putExtra("PersonState",addAddressModel.getPersonState());
                i.putExtra("PersonCountry",addAddressModel.getPersonCountry());
                context.startActivity(i);
                ((Activity) context).finish();
            }
        });
        holder.closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteAddressData(addAddressModel.getPersonID());
                addAddressActivity.refreshData();
                notifyDataSetChanged();
                Toast.makeText(context, "" + holder.personName.getText() + " has been removed from list",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView personName,personAddress,personLandMark,personCity,personState,personPinCode,personCountry,personMobileNumber;
        Button deliverAddressButton,editAddressButton;
        ImageView closeIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.personName = (TextView) itemView.findViewById(R.id.personName);
            this.personAddress = (TextView) itemView.findViewById(R.id.personAddress);
            this.personLandMark = (TextView) itemView.findViewById(R.id.personLandMark);
            this.personCity = (TextView) itemView.findViewById(R.id.personCity);
            this.personState = (TextView) itemView.findViewById(R.id.personState);
            this.personPinCode = (TextView) itemView.findViewById(R.id.personPinCode);
            this.personCountry = (TextView) itemView.findViewById(R.id.personCountry);
            this.personMobileNumber = (TextView) itemView.findViewById(R.id.personMobileNo);
            this.deliverAddressButton = (Button) itemView.findViewById(R.id.deliverAddressButton);
            this.editAddressButton = (Button) itemView.findViewById(R.id.editAddressButton);
            this.closeIcon = (ImageView) itemView.findViewById(R.id.closeIcon);
        }
    }
}
