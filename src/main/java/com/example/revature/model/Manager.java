package com.example.revature.model;

import java.util.ArrayList;


public class Manager extends Employee {
    // Checks if email is registered
    
    private enum process{APPROVED, DENIED, PENDING}

    public Manager(String email, String password,  String tickets, boolean manager) {
        super(email, password, tickets);
    }
    
    
    public Manager(){
        
    }

        public void ProcessTick(){
            
            process change = process.PENDING;
        
        
            switch (change){
                case APPROVED:
                    System.out.println(process.APPROVED);    
                    break;
                case DENIED:
                    System.out.println(process.DENIED);
                    break;
                default:
                    break;
            }
        }

        // private String changeApproved(String approve) {
        //     if (approve == "APPROVED"){
        //         this.approve = process.APPROVED;
        //     }
        //     return ;
        // }
        // private List<Register> registeration;
        // public Employee() {
        //     this.registeration = new ArrayList<>();
        // }
    
    // public static boolean findExistingEmail(String email){
    //     for (int i; i > employ.length; i++;){
    //         if (i.email)
    //     }
    // }

}
