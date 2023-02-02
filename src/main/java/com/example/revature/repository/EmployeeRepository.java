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

import utils.ConnectionUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeRepository {

    public void Save(Employee employ) {

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
        String sql = "insert into employee (email, pass) values (?,?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setString(1, employ.getEmail());
            prstmt.setString(2, employ.getPassword());

            // excute is updating'
            // excutequery expect something to result after excuting the statement

            prstmt.execute();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployee() {
        String sql = "select * from employee";
        List<Employee> listOfEmployee = new ArrayList<Employee>();

        try (Connection con = ConnectionUtil.getConnection()) {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            // Mapping information from a table to a DS instead
            while (rs.next()) {
                Employee newEmployee = new Employee();

                newEmployee.setEmail(rs.getString(1));
                newEmployee.setPassword(rs.getString(2));

                listOfEmployee.add(newEmployee);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return listOfEmployee;
    }
}
