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

public class TicketingController implements HttpHandler {
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
                notHTTP(exchange);
                break;
        }
    }

    private void getRequest(HttpExchange exchange) throws IOException {
        TicketingService serv = new TicketingService();
        String jsonCurrentList = serv.getAllTicketing();

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
    
        TicketingService tickService = new TicketingService();
        tickService.saveToTickBox(textBuilder.toString());
    
        OutputStream os = exchange.getResponseBody();
        os.write(textBuilder.toString().getBytes());
        os.close();
    }


    private void putRequest(HttpExchange exchange) throws IOException{
        TicketingService serv = new TicketingService();
        String manEmail;
        String manPassword;
        String ticketID;
        String status;
        String response = "";
        JsonNode jsonDoc;
        Ticketing processTickets = new Ticketing();
        //Ticketing processTickets = new Ticketing();
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
        manEmail = (jsonDoc.get("email").toString());
        manEmail = manEmail.replace("\"", "");
        manPassword = (jsonDoc.get("password").toString());
        manPassword = manPassword.replace("\"", "");
        ticketID = (jsonDoc.get("ticketID").toString());
        ticketID = ticketID.replace("\"", "");
        status = (jsonDoc.get("status").toString());
        status = status.replace("\"", "");
        processTickets = serv.updateTickets(manEmail, manPassword, ticketID, status);
        response = mapper.writeValueAsString(processTickets);
        exchange.sendResponseHeaders(200, response.getBytes().length);
        os.write(response.getBytes());
        os.close();
    }

    private void notHTTP(HttpExchange exchange) throws IOException {
        String noResponse = "HTTP Not Supported";
        exchange.sendResponseHeaders(404, noResponse.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(noResponse.getBytes());
        os.close();
    }

}
