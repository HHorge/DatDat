/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package treningsdagbok;

/**
 *
 * @author sveinbra
 */

import java.sql.*;
import java.util.*;

public class Ovelsesgruppe extends ActiveDomainObject {
    private String gruppeNavn;
    private String beskrivelse;
 

    public void setGruppenavn (String gruppeNavn) {
        this.gruppeNavn = gruppeNavn;
    }

    public String getGruppeNavn () {
        return gruppeNavn;
    }
    public String getBeskrivelse () {
        return beskrivelse;
    }
    public void setBeskrivelse (String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
   
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select beskrivelse, prestasjon, form where bid=");
      

        } catch (Exception e) {
            System.out.println("db error during select of notat= "+e);
            return;
        }

    }
    
    public void refresh (Connection conn) {
        initialize (conn);
    }
    
    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("insert into ovelsesgruppe " + "values('"+gruppeNavn+"', '"+beskrivelse+"')");
        } catch (Exception e) {
            System.out.println("db error during update of notat="+e);
            return;
        }
    }
    
}