package com.example.revature.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    //Set them as char to receive symbols, numbers and alphabet within email and password
    private static String email; 
    private static String pass;
    
    public Employee(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
    public Employee(){
        
    }

    // private List<Register> registeration;
    // public Employee() {
    //     this.registeration = new ArrayList<>();
    // }

    /**
     * @param email receoves the email from the user
     * @param pass receives the password from the user
     */

    public static String getEmail() {
        return email;
    }
    public static String getPassword() {
        return pass;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String pass) {
        this.pass = pass;
    }
}
