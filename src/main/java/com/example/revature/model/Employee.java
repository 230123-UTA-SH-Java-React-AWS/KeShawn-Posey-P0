package com.example.revature.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    //Set them as char to receive symbols, numbers and alphabet within email and password
    private String email; 
    private String pass;
    private ArrayList<Ticketing> tickets = new ArrayList<Ticketing>();

    public Employee(String email, String pass, ArrayList<Ticketing> tickets) {
        this.email = email;
        this.pass = pass;
        this.tickets = tickets;
    }
    
    public Employee(){
        
    }

    
    @Override
    public String toString() {
        return "Employee [email=" + email + ", password=" + pass + "]";
    }

    public void addTicket(Ticketing tickets){
        this.tickets.add(tickets);
    }

    public void printTickets(){
        double total = 0;

        for (Ticketing i: tickets){
            total += i.getAmount();
            System.out.println(i);
        }
        System.out.println("Your total on your ticket is $" + total);
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
}
