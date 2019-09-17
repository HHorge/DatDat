package treningsdagbok;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Apparat extends ActiveDomainObject{
	private String apparatNavn;
    private String beskrivelse;

    public void setApparatNavn (String apparatNavn) {
        this.apparatNavn = apparatNavn;
    }
    public String getApparatNavn () {
        return apparatNavn;
    }
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select navn where apparatNavn=" + apparatNavn);
            while (rs.next()) {
            	apparatNavn = rs.getString("apparatnavn");
                beskrivelse = rs.getString("beskrivelse");
            }

        } catch (Exception e) {
            System.out.println("db error during select of bruker= "+e);
            return;
        }

    }
    
    public void refresh (Connection conn) {
        initialize (conn);
    }
    
    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("insert into apparat " +"values('"+apparatNavn+"', '"+beskrivelse+"')");
        } catch (Exception e) {
            System.out.println("db error during update of bruker="+e);
            return;
        }
    }
	public void setBeskrivelse(String b) {
		
		this.beskrivelse = b;
	}
}
