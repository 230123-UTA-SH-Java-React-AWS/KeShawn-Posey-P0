package com.example.revature.service;

import java.io.IOException;
import java.util.List;

import com.example.revature.repository.*;
import com.example.revature.model.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ManagerService {
    //saves the manager
    public void saveToManBox(String ManJson) {
        
        ManagerRepository repo = new ManagerRepository();
        //Conversion from string to pokemon obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Manager newManager = mapper.readValue(ManJson, Manager.class);

            repo.Save(newManager);

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

    public String getAllForManager(){
        ManagerRepository repo = new ManagerRepository();
        List<Manager> listOfManagers = repo.getAllForManager();

        ObjectMapper map = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = map.writeValueAsString(listOfManagers);

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
    //follows manager
    public Manager getCurrentManager(Manager current) {
        ManagerRepository repo = new ManagerRepository();
        Manager currentManager = repo.loginManager(current);
        return currentManager;
    }
}
