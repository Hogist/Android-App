package com.example.hogist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private ArrayList<BDataModel> dataModels;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView BreakfastItem, VendorName, EnterprisePrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.BreakfastItem= (TextView)itemView.findViewById(R.id.menu_cardview_breakfastitem);
            this.VendorName = (TextView)itemView.findViewById(R.id.menu_cardview_vendorname);
            this.EnterprisePrice = (TextView)itemView.findViewById(R.id.menu_cardview_enterpriseprice);

        }
    }

    public MenuAdapter(ArrayList<BDataModel> data){
        this.dataModels = data;
    }

    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_details_cardview,parent,false);
        view.setOnClickListener(Menudetails.myOnclickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {

        TextView FoodItem = holder.BreakfastItem;
        TextView VendorName= holder.VendorName;
        TextView EnterprisePrice = holder.EnterprisePrice;

        VendorName.setText("VendorName: "+dataModels.get(position).getVendorName());
        FoodItem.setText("FoodItems: "+dataModels.get(position).getFoodItem());
        EnterprisePrice.setText("Status: "+dataModels.get(position).getEnterprisePrice());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}
