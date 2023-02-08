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
        String sql = "insert into ticketing (amount, description, status) values (?,?,?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setDouble(1, tick.getAmount());
            prstmt.setString(2, tick.getDescription());
            prstmt.setString(3, tick.getStatus());

            // excute is updating'
            // excutequery expect something to result after excuting the statement

            prstmt.execute();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public List<Ticketing> getAllTicketing() {
        String sql = "select * from ticketing";
        List<Ticketing> listOfTicketing = new ArrayList<Ticketing>();

        try (Connection con = ConnectionUtil.getConnection()) {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            // Mapping information from a table to a DS instead
            while (rs.next()) {
                Ticketing newTicketing = new Ticketing();

                newTicketing.setAmount(rs.getDouble(1));
                newTicketing.setDescription(rs.getString(2));
                newTicketing.setStatus(rs.getString(3));

                listOfTicketing.add(newTicketing);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return listOfTicketing;
    }
}
