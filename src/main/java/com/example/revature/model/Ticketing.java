package com.example.revature.model;

public class Ticketing {

    //enum to have a special class of constants to give the status of the tickets
    // private enum TicStatus {
    //     APPROVED, DENIED, PENDING
    // };
    
    private double amount;
    private String description;
    private String status;
    
    public Ticketing(Double amount, String description, String status) {
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Ticketing(){
        
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    // public void setStatus(TicStatus status) {
    //     if (this.status != TicStatus.PENDING) {
    //         return;
    //     } else{ 
    //         this.status = status;
    //     }
    // }
    
        @Override
        public String toString() {
            return "Ticketing [amount=$" + amount + ", description=" + description + ", status=" + status + "]";
        }
}
