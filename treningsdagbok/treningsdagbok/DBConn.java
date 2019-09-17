/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package treningsdagbok;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConn {
	protected Connection conn;
    public DBConn () {
   
    }
    
 
    public void connect() {
        try {
        	//Class.forName("com.mysql.jdbc.Driver").newInstance();
            /*// Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            p.put("user", "mariuscs_DatDat");
            p.put("password", "mmkkoo");*/
            conn = DriverManager.getConnection("jdbc:mysql://localhost/treningsdagbok", "root","123456789");
            System.out.println(conn);
            System.out.println("Vi har naa connection");
        } catch (Exception e)
        {
            throw new RuntimeException("Unable to connect", e);
        }
        
        
    }
}