package com.example.revature.model;

public class Employee {
    //Set them as char to receive symbols, numbers and alphabet within email and password
    private String email; 
    private String password;
    
    public Employee(String email, String password) {
        this.email = email;
        this.password = password;
    }
    /**
     * @param email receoves the email from the user
     * @param password receives the password from the user
     */

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}