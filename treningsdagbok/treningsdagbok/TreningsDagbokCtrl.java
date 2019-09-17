package treningsdagbok;

import java.sql.*;

public class TreningsDagbokCtrl extends DBConn{
	
	private Treningsokt okt;
	private Apparat apparat;
	private Ovelsesgruppe og;
	private Bruker bruker;
	private Ovelse ovelse;
	
	private int saveSelector;

	public void lagBruker(String n) {
		bruker = new Bruker();
		bruker.setBrukerNavn(n);
		
	}
	
	public void saveBruker() {
		saveSelector = 6;
		fullfor(saveSelector);
	}
	
	public int findOktID() {
		try {
			int result = 0;
			Statement stmt = conn.createStatement(); 
			
			String query = "select * from treningsokt order by oktid desc limit 1";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getInt("oktid");
			}
			return result+1;
		
		} catch (Exception e) {
	        System.out.println("db error during update of notat="+e);
	        return 29;
    }
	}

	public void lagTreningsokt(String dato, int tidspunkt, int varighet, int form, int prestasjon, String notat) {
		saveSelector = 1;
		okt = new Treningsokt(findOktID());
		
		okt.setDato(dato);
		okt.setTidspunkt(tidspunkt);
		okt.setVarighet(varighet);
		okt.setForm(form);
		okt.setPrestasjon(prestasjon);
		okt.setNotat(notat);
		fullfor(saveSelector);
		this.lagBrukerokt();
	}
	
	public void lagOvelse(String ovelsenavn, int sett){
		saveSelector = 2;
		ovelse = new Ovelse();
		ovelse.setNavn(ovelsenavn);
		ovelse.setSett(sett);
		fullfor(saveSelector);
	}

    public void lagApparat(String apparatNavn, String beskrivelse) {
		saveSelector = 3;
		apparat = new Apparat();
		apparat.setApparatNavn(apparatNavn);
		apparat.setBeskrivelse(beskrivelse);
		fullfor(saveSelector);
		
	} 
	
	
	public void lagOvelsesgruppe(String navn, String beskrivelse){
		saveSelector = 4;
		og = new Ovelsesgruppe();
		og.setGruppenavn(navn);
		og.setBeskrivelse(beskrivelse);
		fullfor(saveSelector);
	}
	
	public void lagBrukerokt () {
        try {
            
        	Statement stmt = conn.createStatement(); 
            
            String query = "select brukerId from bruker where navn = " + formatting(bruker.getNavn());
            int bid = 0;
            
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				 
				bid = rs.getInt("brukerid");
			}
			stmt.executeUpdate("insert into brukerokt " + "values("+bid+","+okt.getOktId()+")" );
            conn.commit();
			
            
			
        } catch (Exception e) {
            System.out.println("db error during update of lagbrukerokt="+e);
            return;
        }
    }
	
	//Relasjoner
	public void lagreOvelserIGruppe(String nyovelse, String gruppeNavn){
		try {
			Statement stmt = conn.createStatement(); 
			String query = "insert into ovelseigruppe " + "values("+formatting(nyovelse)+", "+formatting(gruppeNavn)+")";
			System.out.println(query);
			stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			System.out.println("db error during update of ovelserigruppe="+e);
			return;
		}
	}
		
    //Relasjonsspoerringer
    
    //Returnerer de n siste treningsoktene til en bruker
    public void brukerOkt(String bnavn, int n) {
    	Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select treningsokt.* "
					+ "from (brukerokt natural join treningsokt) natural join bruker "
					+ "where bruker.navn = "+ formatting(bnavn)
					+ " order by treningsokt.oktID desc "
					+ "limit " + n;
			
			String result = "";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();    
			for (int i = 1; i <= columnCount; i++) {
					result += metadata.getColumnName(i) + ", ";  	
					 
			}
			System.out.println("result stringen er: " + result); 
		
			while (rs.next()) {
					String row = "";
					for (int i = 1; i <= columnCount; i++) {
							row += rs.getString(i) + ", ";          
					}
					System.out.println("row stringen er: " + row);
					

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //Retrunerer de ulike ovelsene som horer til i en gitt ovelsesgruppe
    public void ovelsesgruppe(String navn) {
    	Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select ovelse.ovelsenavn "
					+ "from (ovelseigruppe natural join ovelse) natural join ovelsesgruppe "
					+ "where ovelseigruppe.gruppenavn = "+ formatting(navn);

			
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Ovelsene som hoerer til "+ navn +" er:");
			while(rs.next()){
				String ovelse = rs.getString("ovelseNavn");
				System.out.println(ovelse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //Retrunerer prestasjon paa en gitt ovelse utfort ila et gitt tidsintervall
    public void resultatlogg(String navn, String tid1, String tid2) {
    	Statement stmt;
			try {
				stmt = conn.createStatement();
				String query = "select treningsokt.prestasjon "
						+ "from (ovelseiokt natural join ovelse) natural join treningsokt "
						+ "where ovelse.ovelsenavn = "+formatting(navn)+ "and (treningsokt.tidspunkt > " +formatting(tid1)+" and treningsokt.tidspunkt < " + formatting(tid2)+")";

				
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("Dine tidligere prestasjon(er) paa"+ navn +" er:");
				while(rs.next()){
					String prestasjon = rs.getString("prestasjon");
					System.out.println(prestasjon);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void ovelsePaaApparat(String apparat){
			Statement stmt;
			try {
				stmt = conn.createStatement();
				String query = "select ovelse.ovelseNavn " +
						"from (apparatrelasjon natural join ovelse) natural join apparat " +
						"where apparatNavn = " + formatting(apparat);

				ResultSet rs = stmt.executeQuery(query);
				System.out.println("Oevelsene du kan utfoere paa "+ apparat +" er:");
				while(rs.next()){
					String ovelse = rs.getString("ovelseNavn");
					System.out.println(ovelse);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		public void ovelseiokt(Connection conn) {
			Statement stmt;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
	 
public TreningsDagbokCtrl () {
		connect();
		// La laging av avtale vaere en transaksjon
		try {
				conn.setAutoCommit(false);
		} catch (SQLException e) {
				System.out.println("db error during setAuoCommit of TreningsDagbokCtrl="+e);
				return;
		}
	}
		
	public String formatting(String s){
		return "'"+s+"'";
	}
	
	public void fullfor (int selector) {
		if(selector == 1){
			okt.save(conn);
			try {
				conn.commit();
			} catch (SQLException e) {
				System.out.println("db error during commit of Oekt="+e);
				return;
			}
		
		} else if(selector == 2) {
			ovelse.save(conn);
			try {
				conn.commit();
			} catch (SQLException e) {
				System.out.println("db error during commit of Oevelse="+e);
				return;
			}
		} else if(selector == 3) {	
			apparat.save(conn);
			try {
				conn.commit();
			} catch (SQLException e) {
				System.out.println("db error during commit of Apparat="+e);
				return;
			}
		} else if(selector == 4) {
			og.save(conn);
			try {
				conn.commit();
			} catch (SQLException e) {
				System.out.println("db error during commit of LagAvtaleCtrl="+e);
				return;
			}
		} else if(selector == 5) {
			okt.save(conn);
			try {
				conn.commit();
			} catch (SQLException e) {
				System.out.println("db error during commit of LagAvtaleCtrl="+e);
				return;
			}
		}else if(selector == 6) {
			bruker.save(conn);
			try {
				conn.commit();
			} catch (SQLException e) {
				System.out.println("db error during commit of LagAvtaleCtrl="+e);
				return;
			}
		}

    }
    
    
	

}
