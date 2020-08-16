package com.example.hogist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataModels;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView vendorId, vendorFullName, companyName, enterpriseId, enterpriseName, menuId;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.vendorId = (TextView)itemView.findViewById(R.id.vendor_details_vendorId);
            this.vendorFullName = (TextView)itemView.findViewById(R.id.vendor_details_venforfullname);
            this.companyName = (TextView)itemView.findViewById(R.id.vendor_details_companyname);
            this.enterpriseId = (TextView)itemView.findViewById(R.id.vendor_details_enterpriseid);
            this.enterpriseName = (TextView)itemView.findViewById(R.id.vendor_details_enterprisename);
            this.menuId = (TextView)itemView.findViewById(R.id.vendor_details_menuid);

        }
    }

    public ViewAdapter(ArrayList<DataModel> data){
        this.dataModels = data;
    }


    @NonNull
    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendordetails_cardview,parent,false);
        view.setOnClickListener(ViewEnterpriseActivity.myOnclickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.MyViewHolder holder, int position) {
        TextView VendorID = holder.vendorId;
        TextView VendorFullName = holder.vendorFullName;
        TextView CompanyName = holder.companyName;
        TextView EnterpriseID = holder.enterpriseId;
        TextView EnterpriseName = holder.enterpriseName;
        TextView MenuID = holder.menuId;

        VendorID.setText("Vendor ID:"+dataModels.get(position).getVendorID());
        VendorFullName.setText("VendorFullName:"+dataModels.get(position).getVendorFullName());
        CompanyName.setText("CompanyName:"+dataModels.get(position).getCompanyName());
        EnterpriseName.setText("EnterpriseName:"+dataModels.get(position).getEnterpriseName());





    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}
