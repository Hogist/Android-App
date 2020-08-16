package com.example.hogist;

public class MenuChangeRequestDataModel {
    String RequestID;
    String EUserID;
    String Reason;
    String NewMenu;
    String EnterpriseName;
    public MenuChangeRequestDataModel(String requestID, String eUserID, String reason, String newMenu, String enterpriseName) {
        RequestID = requestID;
        EUserID = eUserID;
        Reason = reason;
        NewMenu = newMenu;
        EnterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getNewMenu() {
        return NewMenu;
    }

    public void setNewMenu(String newMenu) {
        NewMenu = newMenu;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getEUserID() {
        return EUserID;
    }

    public void setEUserID(String EUserID) {
        this.EUserID = EUserID;
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
    }
}
