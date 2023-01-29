package com.example.revature;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.example.revature.model.Employee;


/**
 * Keshawn Posey
 * 02-08-23
 * Allow associates to develop features in a self guided evironment
 */
public final class App {
    public static void main(String[] args) {
        
        Employee Bob = new Employee("bobisclutch@gmail.com", "bobisnumber1!");
        Employee Amy = new Employee("bobisclutch@gmail.com", "bobisnumber1!");
        Amy.setEmail("bobisclutch@gmail.com");
        Amy.setPassword("amyisnumber1!");
        
        System.out.println(Bob.getEmail());
        System.out.println(Amy.getPassword());

        List<Employee> employs = new ArrayList<Employee>();
        employs.add(Bob);
        
        System.out.println("Hello World!");
    }
}
