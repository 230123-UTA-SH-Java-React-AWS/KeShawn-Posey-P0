package com.example.revature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.example.revature.model.Employee;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeRepository {

    public void Save(Employee employ){

        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = "";

        try {

            jsonObject = mapper.writeValueAsString(employ);

            File employFile = new File("./src/main/java/com/example/revature/repository/employee.json");
            employFile.createNewFile();

            FileWriter writer = new FileWriter("./src/main/java/com/example/revature/repository/employee.json");
            writer.write(jsonObject); //Writes the string into the file
            writer.close(); 
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
    }
    
}
