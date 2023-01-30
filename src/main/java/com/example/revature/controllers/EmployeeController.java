package com.example.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.example.revature.service.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EmployeeController implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // TODO Auto-generated method stub

        String verb = exchange.getRequestMethod();

        switch (verb){
            case "POST":
                postRequest(exchange);
                break;
            default:
                break;
        }
        
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

}