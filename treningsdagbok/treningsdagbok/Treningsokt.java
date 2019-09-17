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
import java.sql.Date;
import java.util.*;

public class Treningsokt extends ActiveDomainObject {
    private int oktId;
    private String dato;
    private int tidspunkt;
    private int varighet;
    private int form;
    private String notat;
    private int prestasjon;
 
    
    public Treningsokt(int oktid) {
    	this.oktId = oktid;
    }
    public int getForm() {
		return form;
	}
	public void setForm(int form) {
		this.form = form;
	}
	public String getNotat() {
		return notat;
	}
	public void setNotat(String notat) {
		this.notat = notat;
	}
	public int getPrestasjon() {
		return prestasjon;
	}
	public void setPrestasjon(int prestasjon) {
		this.prestasjon = prestasjon;
	}
	public int getOktId(){
        return oktId;
    }
    public String getDato () {
        return dato;
    }
    public void setDato (String string) {
        this.dato=string;
    }
    public int getTidspunkt () {
        return tidspunkt;
    }
    public void setTidspunkt (int tidspunkt) {
        this.tidspunkt=tidspunkt;
    }
    public int getVarighet (int varighet) {
        return varighet;
    }
    public void setVarighet (int varighet) {
        this.varighet=varighet;
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
            System.out.println("insert into treningsokt " 
                    + "values("+oktId+", " +tidspunkt+", " + varighet + ", '"+notat+"', " + prestasjon +", " + form+")");
            stmt.executeUpdate("insert into treningsokt " 
            + "values("+oktId+", " +tidspunkt+", " + varighet + ", '"+notat+"', " + prestasjon +", " + form+")");
           
        } catch (Exception e) {
            System.out.println("db error during update of notat="+e);
            return;
        }
    }
    
}