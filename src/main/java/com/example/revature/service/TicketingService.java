package com.example.revature.service;

import java.io.IOException;
import java.util.List;

import com.example.revature.repository.*;
import com.example.revature.model.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TicketingService {
    public void saveToTickBox(String TickJson) {
        
        TicketingRepository repo = new TicketingRepository();
        //Conversion from string to pokemon obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Ticketing newTicketing = mapper.readValue(TickJson, Ticketing.class);

            repo.Save(newTicketing);

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getAllTicketing(){
        TicketingRepository repo = new TicketingRepository();
        List<Ticketing> listOfTicketings = repo.getAllTicketing();

        ObjectMapper map = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = map.writeValueAsString(listOfTicketings);

        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonString;
 
    }
    public Ticketing updateTickets(String manEmail, String manPassword, String ticketID, String status){
        TicketingRepository repo = new TicketingRepository();
        Ticketing processedTicket = new Ticketing();
        boolean manValidation = repo.validateManager(manEmail,manPassword);
        if (manValidation == true) {    // Check if Manager exist and password is correct
            processedTicket = repo.processTickets(ticketID, status);
            System.out.println("Ticket Processed");
        } else {
            System.out.println("Unable to process ticket");
        }
        return processedTicket;
    }
}
