package com.example.hogist;

public class CancelOrderRequestDataModel {

    String RequestId;
    String EUserID;
    String Reason;
    String NewMenu;
    String Status;


    public CancelOrderRequestDataModel(String requestId, String EUserID, String reason, String newMenu, String status) {
        RequestId = requestId;
        this.EUserID = EUserID;
        Reason = reason;
        NewMenu = newMenu;
        Status = status;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getEUserID() {
        return EUserID;
    }

    public void setEUserID(String EUserID) {
        this.EUserID = EUserID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getNewMenu() {
        return NewMenu;
    }

    public void setNewMenu(String newMenu) {
        NewMenu = newMenu;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
