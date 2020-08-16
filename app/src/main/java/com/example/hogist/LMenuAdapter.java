package com.example.hogist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LMenuAdapter extends RecyclerView.Adapter<LMenuAdapter.MyViewHolder> {

    private ArrayList<BDataModel> dataModels;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView BreakfastItem, VendorName, EnterprisePrice,id;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.BreakfastItem= (TextView)itemView.findViewById(R.id.menu_cardview_breakfastitem);
            this.VendorName = (TextView)itemView.findViewById(R.id.menu_cardview_vendorname);
            this.EnterprisePrice = (TextView)itemView.findViewById(R.id.menu_cardview_enterpriseprice);
            this.id=(TextView)itemView.findViewById(R.id.menu_cardview_id);

        }
    }

    public LMenuAdapter(ArrayList<BDataModel> data){
        this.dataModels = data;
    }

    @NonNull
    @Override
    public LMenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_details_cardview,parent,false);
        view.setOnClickListener(Menudetails.myOnclickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LMenuAdapter.MyViewHolder holder, int position) {

        TextView FoodItem = holder.BreakfastItem;
        TextView VendorName= holder.VendorName;
        TextView EnterprisePrice = holder.EnterprisePrice;
        TextView id = holder.id;


        FoodItem.setText("Lunch id: "+dataModels.get(position).getFoodItem());
        VendorName.setText("Menu: "+dataModels.get(position).getVendorName());
        EnterprisePrice.setText(dataModels.get(position).getEnterprisePrice());
        id.setText("Price: "+ dataModels.get(position).getID());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}
