package com.example.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.example.revature.model.Ticketing;
import com.example.revature.service.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EmployeeController implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub

        String verb = exchange.getRequestMethod();

        switch (verb){
            case "GET":
                getRequest(exchange);
                break;
            case "POST":
                postRequest(exchange);
                break;
            case "PUT":
                putRequest(exchange);
                break;
            default:
                break;
        }
        
    }

    private void getRequest(HttpExchange exchange) throws IOException {
        EmployeeService serv = new EmployeeService();
        String jsonCurrentList = serv.getAllEmployee();

        exchange.sendResponseHeaders(200, jsonCurrentList.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(jsonCurrentList.getBytes());
        os.close();
    }

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
    
        EmployeeService employService = new EmployeeService();
        employService.saveToEmployBox(textBuilder.toString());
    
        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();
    }

    private void putRequest(HttpExchange exchange) throws IOException {

        EmployeeService serv = new EmployeeService();
        String userEmail;
        String filter;
        String response = "";
        JsonNode jsonDoc;
        List<Ticketing> allTickets = new ArrayList<Ticketing>();
        InputStream is = exchange.getRequestBody();
        StringBuilder textBuilder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        OutputStream os = exchange.getResponseBody();
        try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char)c);
            }
        }
        jsonDoc = mapper.readTree(textBuilder.toString()); 
        userEmail = (jsonDoc.get("email").toString());
        userEmail = userEmail.replace("\"", "");
        filter = (jsonDoc.get("filter").toString());
        filter = filter.replace("\"", "");
        allTickets = serv.filterTickets(userEmail, filter);
        response = mapper.writeValueAsString(allTickets);
        exchange.sendResponseHeaders(200, response.getBytes().length);
        os.write(response.getBytes());
        os.close();
    }

}