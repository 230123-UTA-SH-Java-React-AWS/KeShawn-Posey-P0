package com.example.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import com.example.revature.model.Manager;
import com.example.revature.service.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ManagerController implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub

        String verb = exchange.getRequestMethod();

        switch (verb){
            case "GET":
                //login for manager and sees employees
                getRequest(exchange);
                break;
            case "POST":
                postRequest(exchange);
                break;
            case "UPDATE":
                //updateRequest(exchange);
                break;
            case "PUT":
                getEmployeeRequest(exchange);
                break;
            default:
                break;
        }
        
    }

    private void getRequest(HttpExchange exchange) throws IOException {
        ManagerService serv = new ManagerService();
        ObjectMapper mapper = new ObjectMapper();
        String response = "";
        InputStream is = exchange.getRequestBody();
        StringBuilder textBuilder = new StringBuilder();
        OutputStream os = exchange.getResponseBody();
        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char)c);
            }
        } 
        Manager userInfo = mapper.readValue(textBuilder.toString(), Manager.class);
        Manager currentUser = serv.getCurrentManager(userInfo);
        if (currentUser == null) {
            response = "Incorrect Email or Password";
            exchange.sendResponseHeaders(404, response.getBytes().length);
            os.write(response.getBytes());
        }
        else if (currentUser != null) {
            response = mapper.writeValueAsString(currentUser);
            exchange.sendResponseHeaders(200, response.getBytes().length);
            os.write(response.getBytes());
        }
        os.close();
    }
    //signup
    private void postRequest(HttpExchange exchange) throws IOException{

        InputStream is = exchange.getRequestBody();

        StringBuilder textBuilder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){
            int k = 0;

            while ((k = reader.read()) != -1){
                textBuilder.append((char)k);
            }
        }
        exchange.sendResponseHeaders(200, textBuilder.toString().getBytes().length);
    
        ManagerService manService = new ManagerService();
        manService.saveToManBox(textBuilder.toString());
    
        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();
    }

    //gets employee request
    private void getEmployeeRequest(HttpExchange exchange) throws IOException {
        ManagerService serv = new ManagerService();
        String jsonCurrentList = serv.getAllForManager();
        exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(jsonCurrentList.getBytes());
        os.close();
    }

}
