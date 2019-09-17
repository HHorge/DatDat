package treningsdagbok;


import java.sql.*;
import java.util.*;

public class Ovelse extends ActiveDomainObject {
    private String ovelseNavn;
    private int sett;
 
    /*
    public Ovelse (String ovelseNavn) {
        this.ovelseNavn = ovelseNavn;
    }*/
    public String getOvelseNavn () {
        return ovelseNavn;
    }
    public int getSett () {
        return sett;
    }
    public void setSett (int sett) {
        this.sett = sett;
    }

    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("insert into ovelse " + "values('"+ovelseNavn+"', "+sett+")");
        } catch (Exception e) {
            System.out.println("db error during update of notat="+e);
            return;
        }
    }
	public void setNavn(String next) {
		this.ovelseNavn = next;
		
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