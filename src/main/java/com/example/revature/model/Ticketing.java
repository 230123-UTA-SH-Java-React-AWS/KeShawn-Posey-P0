package com.example.revature.model;

public class Ticketing {

    private String email;
    private String password;
    private int ticketId;
    private double amount;
    private String description;
    private String status;
    protected final String[] validation = {"PENDING", "APPROVED", "DENIED"};
    
    public Ticketing(String email, String password, int ticketId, Double amount, String description, String status) {
        this.email = email;
        this.password = password;
        this.ticketId = ticketId;
        this.amount = amount;
        this.description = description;
        this.status = validation[0];
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
    
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    

        public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Ticketing [ticketId=" + ticketId + ",email=" + email + ", password=" + password + ", amount=" + amount
                + ", description=" + description + ", status=" + status + "]";
    }

       
}
