package com.example.revature;

import java.util.ArrayList;
import java.util.List;
import com.example.revature.model.Employee;
import com.example.revature.repository.EmployeeRepository;
import com.example.revature.controllers.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

import utils.ConnectionUtil;

/**
 * Keshawn Posey
 * 02-08-23
 * Allow associates to develop features in a self guided evironment
 */
public final class App {
    public static void main(String[] args) throws Exception {
        
        // Employee Bob = new Employee("bobisclutch@gmail.com", "bobisnumber1!");
        // Employee Amy = new Employee("bobisclutch@gmail.com", "bobisnumber1!");
        // Amy.setEmail("bobisclutch@gmail.com");
        // Amy.setPassword("amyisnumber1!");
        
        // System.out.println(Bob.getEmail());
        // System.out.println(Amy.getPassword());

        // List<Employee> employs = new ArrayList<Employee>();
        // employs.add(Bob);
        
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/employee13", new EmployeeController());

        server.setExecutor(null);
        server.start();

        // System.out.println(System.getenv("user"));
        // System.out.println(System.getenv("password"));
        // System.out.println(System.getenv("url"));

        // ConnectionUtil.getConnection();

    //     EmployeeRepository repo = new EmployeeRepository();
    //     List<Employee> currentList = repo.getAllEmployee();

    //     System.out.println(currentList.toString());
    }
}
