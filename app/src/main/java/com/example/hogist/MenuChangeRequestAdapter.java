package com.example.hogist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuChangeRequestAdapter extends RecyclerView.Adapter<MenuChangeRequestAdapter.MyViewHolder> {

    private ArrayList<MenuChangeRequestDataModel> dataModels;


    @NonNull
    @Override
    public MenuChangeRequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_request_cardview,parent,false);
        view.setOnClickListener(MenuRequestDetails.myOnclickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuChangeRequestAdapter.MyViewHolder holder, int position) {

        TextView RequestID = holder.Requestid;
        TextView VendorName = holder.Vendorname;
        TextView Reason  = holder.Reason;
        TextView NewMenu = holder.Newmenu;
        TextView Status = holder.Status;


        RequestID.setText("Request ID:  "+dataModels.get(position).getRequestID());
        VendorName.setText("EUserID: "+dataModels.get(position).getEUserID());
        Reason.setText("Reason: "+dataModels.get(position).getReason());
        NewMenu.setText("New Menu:"+dataModels.get(position).getNewMenu());
        Status.setText("Enterprise name: "+dataModels.get(position).getEnterpriseName());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Requestid, Vendorname, Reason, Newmenu, Status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.Requestid = (TextView)itemView.findViewById(R.id.menu_change_cardview_Requestid);
            this.Vendorname = (TextView)itemView.findViewById(R.id.menu_change_cardview_vendorname);
            this.Reason = (TextView)itemView.findViewById(R.id.menu_change_cardview_reason);
            this.Newmenu = (TextView)itemView.findViewById(R.id.menu_change_cardview_newmenu);
            this.Status = (TextView)itemView.findViewById(R.id.menu_change_cardview_status);

        }
    }

    public MenuChangeRequestAdapter(ArrayList<MenuChangeRequestDataModel> dataModels) {
        this.dataModels = dataModels;
    }
}
