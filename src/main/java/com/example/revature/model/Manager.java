package com.example.revature.model;

public class Manager {
    // Checks if email is registered

        //Set them as char to receive symbols, numbers and alphabet within email and password
        private String email; 
        private String password;
        
        public Manager(String email, String password) {
            this.email = email;
            this.password = password;
        }
    
        // private List<Register> registeration;
        // public Employee() {
        //     this.registeration = new ArrayList<>();
        // }
    
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
    // public static boolean findExistingEmail(String email){
    //     for (int i; i > employ.length; i++;){
    //         if (i.email)
    //     }
    // }

}
