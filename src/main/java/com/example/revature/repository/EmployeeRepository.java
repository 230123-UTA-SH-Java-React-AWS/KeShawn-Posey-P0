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
import com.example.revature.model.Ticketing;

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
        String sql = "insert into employee (employeeId, email, pass, roles) values (?,?,?,?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);

            prstmt.setInt(1, employ.getId());
            prstmt.setString(2, employ.getEmail());
            prstmt.setString(3, employ.getPassword());
            prstmt.setString(4, employ.getRole());

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

                newEmployee.setId(rs.getInt(1));
                newEmployee.setEmail(rs.getString(2));
                newEmployee.setPassword(rs.getString(3));
                newEmployee.setRole(rs.getString(4));

                listOfEmployee.add(newEmployee);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return listOfEmployee;
    }

    public List<Ticketing> getTickets (String email)
    {
        String sql = "select * from ticketing where email = ?";
        List<Ticketing> allTickets = new ArrayList<Ticketing>();
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, email);
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()){
                Ticketing Ticket = new Ticketing();
                Ticket.setEmail(rs.getString("email"));
                Ticket.setAmount(rs.getDouble("amount"));
                Ticket.setDescription(rs.getString("description"));
                Ticket.setStatus(rs.getString("status"));
                allTickets.add(Ticket);
            }
            rs.close();
            prstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTickets;
    }

    public List<Ticketing> getFilterTickets (String email, String filter) {
        String sql = "select * from ticketing where email = ? and status = ?";
        List<Ticketing> allTickets = new ArrayList<Ticketing>();
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, email);
            prstmt.setString(2, filter);
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()){
                Ticketing Ticket = new Ticketing();
                Ticket.setEmail(rs.getString("email"));
                Ticket.setAmount(rs.getDouble("amount"));
                Ticket.setDescription(rs.getString("description"));
                Ticket.setStatus(rs.getString("status"));
                allTickets.add(Ticket);
            }
            rs.close();
            prstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTickets;
    }
    public Employee loginEmployee(Employee employee) {
        String sql = "select * from employee where email = ?";
        Employee Current = new Employee();
        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement prstmt = con.prepareStatement(sql);
            prstmt.setString(1, employee.getEmail());
            ResultSet rs = prstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Employee does not exist!");
                return null;
            }   // check if the password is correct
            else if (employee.getPassword().equals(rs.getString(2))) {
                Current.setId(rs.getInt("employeeId"));
                Current.setEmail(rs.getString("email"));
                Current.setPassword(rs.getString("pass"));
                Current.setRole(rs.getString("roles"));
                Current.setTickets(getTickets(Current.getEmail()));
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
