package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    private static Connection con;

    private ConnectionUtil(){
        con = null; 
    }

    public static Connection getConnection() {
        //determines if we already connection if so give the current connection
        try {
            if (con != null && !con.isClosed()){
                return con;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url, user, pass;
        //it will use the value of the key from system variables
        url = System.getenv("url");
        user = System.getenv("user");
        pass = System.getenv("password");

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Probably wrong password or url");
        }

        return con;
    }
}
