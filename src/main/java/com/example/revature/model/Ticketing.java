package com.example.revature.model;

public class Ticketing {

    //enum to have a special class of constants to give the status of the tickets
    private enum TicStatus {
        APPROVED, DENIED, PENDING
    };

    private String amount;
    private String description;
    private TicStatus status = TicStatus.PENDING;



    public Ticketing(String amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TicStatus status) {
        if (this.status != TicStatus.PENDING) {
            return;
        } else{ 
            this.status = status;
        }
    }
}
