package com.example.revature;


import com.example.revature.controllers.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


/**
 * Keshawn Posey
 * 02-08-23
 * Allow associates to develop features in a self guided evironment
 */
public final class App {
    public static void main(String[] args) throws Exception {
        // ArrayList<Ticketing> k = new ArrayList<Ticketing>();
        // Employee John = new Employee("john@gmail.com", "john234", k);
        // Manager Michael = new Manager("michael@gmail.com", "michael234", k);
        // Ticketing newTicket = new Ticketing(500.00, "medical bill");
        // John.addTicket(newTicket);
        // String str = "APPROVED";
        // Michael.printTickets();
        // System.out.println(John);
        // John.printTickets()
       
        
        System.out.println("Starting backend server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/employee13", new EmployeeController());
        server.createContext("/manager13", new ManagerController());
        server.createContext("/ticketing13", new TicketingController());

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
