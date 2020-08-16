package com.example.hogist;

public class DataModel2 {
    String FoodItem;
    String VendorName;
    String EnterprisePrice;


    public DataModel2(String foodItem, String vendorName, String enterprisePrice) {
        FoodItem = foodItem;
        VendorName = vendorName;
        EnterprisePrice = enterprisePrice;
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

    public String getEnterprisePrice() {
        return EnterprisePrice;
    }

    public void setEnterprisePrice(String enterprisePrice) {
        EnterprisePrice = enterprisePrice;
    }
}
