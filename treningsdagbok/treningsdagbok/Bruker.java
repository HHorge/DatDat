

package treningsdagbok;


import java.sql.*;
import java.util.*;

public class Bruker extends ActiveDomainObject {
    private String brukerId = "NULL";
    private String navn;
    
    public String formatting(String s){
		return "'"+s+"'";
	}
    public void setBrukerNavn (String n) {
        this.navn = n;
        
    }
    public String getNavn() {
    	return this.navn;
    }
    

    
    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement(); 

            stmt.executeUpdate("insert into bruker " + "values("+brukerId+", '"+navn+"')");
        } catch (Exception e) {
            System.out.println("db error during update of bruker="+e);
            return;
        }
    }
	public void setNavn(String next) {
		this.navn = next;
	}
	
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub
		
	}
    
}