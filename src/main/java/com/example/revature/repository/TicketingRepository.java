package com.example.revature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import com.example.revature.model.Ticketing;

import utils.ConnectionUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TicketingRepository {
    //create ticket by employee inside of database

    public void Save(Ticketing tick) {

        // -------------- Save to json file ----------------

        // ObjectMapper mapper = new ObjectMapper();
        // String jsonObject = "";

        // try {

        // jsonObject = mapper.writeValueAsString(employ);

        // File employFile = new
        // File("./src/main/java/com/example/revature/repository/employee.json");
        // employFile.createNewFile();

        // FileWriter writer = new
        // FileWriter("./src/main/java/com/example/revature/repository/employee.json");
        // writer.write(jsonObject); //Writes the string into the file
        // writer.close();
        // } catch (JsonGenerationException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (JsonMappingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }

        // -------------- Save to database ------------
        String sql = "insert into ticketing (ticketID, email, pass, amount, description, status) values (?,?,?,?,?,'PENDING')";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setInt(1, tick.getTicketId());
            prstmt.setString(2, tick.getEmail());
            prstmt.setString(3, tick.getPassword());
            prstmt.setDouble(4, tick.getAmount());
            prstmt.setString(5, tick.getDescription());
            //prstmt.setString(6, tick.getStatus());

            // excute is updating'
            // excutequery expect something to result after excuting the statement

            prstmt.execute();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    //manager can check to see pending tickets
    public List<Ticketing> getAllTicketing() {
        String sql = "select * from ticketing where status = 'PENDING'";
        List<Ticketing> listOfTicketing = new ArrayList<Ticketing>();

        try (Connection con = ConnectionUtil.getConnection()) {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Ticketing newTicketing = new Ticketing();

                newTicketing.setTicketId(rs.getInt(1));
                newTicketing.setEmail(rs.getString(2));
                newTicketing.setAmount(rs.getDouble(3));
                newTicketing.setDescription(rs.getString(4));
                newTicketing.setStatus(rs.getString(5));

                listOfTicketing.add(newTicketing);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return listOfTicketing;
    }

    //manager can process ticket
    public Ticketing processTickets(int ticketID, String status){
        Ticketing processedTicket = new Ticketing();
        String sql = "update ticketing set status = ? where ticketID = ?";
            try (Connection con = ConnectionUtil.getConnection()) {
                PreparedStatement prstmt = con.prepareStatement(sql);
                prstmt.setString(1, status);
                prstmt.setInt(2, ticketID);
                ResultSet rs = prstmt.executeQuery();
                rs.close();
                prstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        processedTicket = findTicket(ticketID);
        return processedTicket;
    }

    //valid that the manager can update the status
    public boolean validateManager(String manEmail, String manPassword) {
        boolean isManager = false;
        String sql = "select * from manager where email = ?";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, manEmail);
            ResultSet rs = prstmt.executeQuery();
            if (!rs.next()) {
                isManager = false;
            }
            else if (manPassword.equals(rs.getString(3))) {
                isManager = true;
            }
            rs.close();
            prstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isManager;
    }
    //finding tickets by id
    private Ticketing findTicket(int ticketID) {
        Ticketing Ticket = new Ticketing();
        String sql = "select * from ticketing where ticketid = ?";
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setInt(1, ticketID);
            ResultSet rs = prstmt.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                Ticket.setTicketId(rs.getInt(1));
                Ticket.setEmail(rs.getString(2));
                Ticket.setAmount(rs.getDouble(3));
                Ticket.setDescription(rs.getString(4));
                Ticket.setStatus(rs.getString(5));
            }
            rs.close();
            prstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ticket;
    }
}
