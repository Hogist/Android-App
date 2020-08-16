package com.example.hogist;

public class DataModel {

    String VendorID;
    String VendorFullName;
    String CompanyName;
    String EnterpriseID;
    String EnterpriseName;
    String MenuId;

    public DataModel(String enterpriseID, String vendorFullName, String companyName,String vendorID , String enterpriseName, String menuId) {
        VendorID = vendorID;
        VendorFullName = vendorFullName;
        CompanyName = companyName;
        EnterpriseID = enterpriseID;
        EnterpriseName = enterpriseName;
        MenuId = menuId;
    }

    public String getVendorID() {
        return VendorID;
    }

    public void setVendorID(String vendorID) {
        VendorID = vendorID;
    }

    public String getVendorFullName() {
        return VendorFullName;
    }

    public void setVendorFullName(String vendorFullName) {
        VendorFullName = vendorFullName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(String enterpriseID) {
        EnterpriseID = enterpriseID;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

}
