package com.example.revature.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.revature.repository.*;
import com.example.revature.model.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeService {
    //saves the employee
    public void saveToEmployBox(String employJson) {
        
        EmployeeRepository repo = new EmployeeRepository();
        //Conversion from string to pokemon obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Employee newEmployee = mapper.readValue(employJson, Employee.class);

            repo.Save(newEmployee);

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

    public String getAllEmployee(){
        EmployeeRepository repo = new EmployeeRepository();
        List<Employee> listOfEmployees = repo.getAllEmployee();

        ObjectMapper map = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = map.writeValueAsString(listOfEmployees);

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
    //follows employee
    public Employee getCurrentEmployee (Employee current) {
        EmployeeRepository repo = new EmployeeRepository();
        Employee currentEmployee = repo.loginEmployee(current);
        return currentEmployee;
    }
    //filters the tickets by status

    public List<Ticketing> filterTickets (String userEmail, String filter){
        EmployeeRepository repo = new EmployeeRepository();
        List<Ticketing> allTickets = new ArrayList<Ticketing>();
        allTickets = repo.getFilterTickets(userEmail, filter);
        return allTickets;
    }
}
