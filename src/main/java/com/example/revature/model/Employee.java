package com.example.revature.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    //Set them as char to receive symbols, numbers and alphabet within email and password
    private int id;
    private String email; 
    private String pass;
    protected String role;
    private List<Ticketing> tickets = new ArrayList<Ticketing>();

    public Employee(int id, String email, String pass, String role) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }
    
    public Employee(){  
        role = "Employee"; 
    }

    public boolean isNotManager() {
        return false;
    }
    
    
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", email=" + email + ", pass=" + pass + ", role=" + role + ", tickets=" + tickets + "]";
    }

    /**
     * @param email receoves the email from the user
     * @param pass receives the password from the user
     */
    
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return pass;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String pass) {
        this.pass = pass;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Ticketing> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticketing> tickets) {
        this.tickets = tickets;
    }
}
