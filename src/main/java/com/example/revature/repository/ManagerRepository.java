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

import com.example.revature.model.Employee;
import com.example.revature.model.Manager;

import utils.ConnectionUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ManagerRepository {
    
    public void Save(Manager man) {

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
        String sql = "insert into manager (managerId, email, pass, roles) values (?,?,?,?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setInt(1, man.getId());
            prstmt.setString(2, man.getEmail());
            prstmt.setString(3, man.getPassword());
            prstmt.setString(4, man.getRole());

            // excute is updating'
            // excutequery expect something to result after excuting the statement

            prstmt.execute();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public List<Manager> getAllManager() {
        String sql = "select * from Manager";
        List<Manager> listOfManager = new ArrayList<Manager>();

        try (Connection con = ConnectionUtil.getConnection()) {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            // Mapping information from a table to a DS instead
            while (rs.next()) {
                Manager newManager = new Manager();

                newManager.setId(rs.getInt(1));
                newManager.setEmail(rs.getString(2));
                newManager.setPassword(rs.getString(3));
                newManager.setRole(rs.getString(4));

                listOfManager.add(newManager);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return listOfManager;
    }
    public Manager loginManager(Manager man) {
        String sql = "select * from manager where email = ?";
        Manager Current = new Manager();
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, man.getEmail());
            ResultSet rs = prstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Employee does not exist!");
                return null;
            }   // check if the password is correct
            else if (man.getPassword().equals(rs.getString(2))) {
                Current.setId(rs.getInt("Id"));
                Current.setEmail(rs.getString("email"));
                Current.setPassword(rs.getString("Password"));
                Current.setRole(rs.getString("Role"));
            } else {
                System.out.println("Wrong Password!");
                return null;
            }
            rs.close();
            prstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Current;
    }
    
}

