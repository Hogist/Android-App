package com.example.hogist;

public class BDataModel {

        String FoodItem;
        String VendorName;
        String EnterprisePrice;
        String ID;


        public BDataModel(String enterprisePrice,String id,String foodItem, String vendorName ) {
            FoodItem = foodItem;
            VendorName = vendorName;
            EnterprisePrice = enterprisePrice;
            ID=id;
        }

        public String getFoodItem() {
            return FoodItem;
        }

        public void setFoodItem(String foodItem) {
            FoodItem = foodItem;
        }

        public String getVendorName() {
            return VendorName;
        }

        public void setVendorName(String vendorName) {
            VendorName = vendorName;
        }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEnterprisePrice() {
            return EnterprisePrice;
        }

        public void setEnterprisePrice(String enterprisePrice) {
            EnterprisePrice = enterprisePrice;
        }
    }
