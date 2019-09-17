

package treningsdagbok;

import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;
import treningsdagbok.Treningsokt;

public class TreningsDagbok {
	
	public static void startProgram() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hei, og velkommen til din treningsdagbok!");
				
		TreningsDagbokCtrl t = new TreningsDagbokCtrl();
		System.out.println("Vil du logge inn (0) eller registrere deg? (1) ");
		int st = scanner.nextInt();
		String bnavn = "";
		if(st == 0){
				System.out.println("Skriv inn brukernavn: ");
				bnavn = scanner.next();
				t.lagBruker(bnavn);
		}
		else if(st == 1){
				System.out.println("Skriv inn brukernavn: ");
				bnavn = scanner.next();
				t.lagBruker(bnavn);
				t.saveBruker();
		}
    		
		while (true) {
	    	System.out.println( "Velg din funksjonalitet (skriv inn tallet): \n"
	    			+ "1) \t Sett registrere nye apparater, ovelser og treningsoekter \n"
	    			+ "2) \t Her finner du informasjon om de n siste treningsoktene \n"
	    			+ "3) \t Her kan du for hver enkelt ovelse se en resultatlogg i et gitt tidsintervall \n"
	    			+ "4) \t Her kan du lage nye ovelsesgrupper og finne ut hvilke ovelser som er i hvilken gruppe \n"
	    			+ "5) \t Her kan du finne ut hvilke ovelser du kan gjore paa et apparat \n"
	    			+ "0) \t Skriv 0 om du onsker aa avslutte programmet");
						
				int fu = scanner.nextInt();
	    	
	    	if (fu == 0) {
	    		break;
	    	}
	    	
	    	if (fu == 1) {
	    		
                
	    		System.out.println("onsker du aa registrere en ny treningsokt (1), apparat (2) eller ovelse(3)");
	    		int funksjon = scanner.nextInt();
	    		if (funksjon == 1) {

					System.out.println("Skriv inn dato (format: YYYY-MM-DD)");
	    			String dato = scanner.next();
	    			
	    			System.out.println("Skriv inn tidspunkt (format: HHMMSS)");
	    			int tidspunkt = scanner.nextInt();
	    			
	    			System.out.println("Skriv inn varighet");
	    			int varighet = scanner.nextInt();
	    		
	    			System.out.println("Skriv inn form (tall fra 1-10");
	    			int form = scanner.nextInt();
	    			
	    			System.out.println("Skriv inn prestasjon (tall fra 1-10)");
	    			int prestasjon = scanner.nextInt();
	    			
	    			System.out.println("Skriv inn notat");
	    			String notat = scanner.next();
					
						
            t.lagTreningsokt(dato, tidspunkt, varighet, form, prestasjon, notat);
            
	    		}
	    		if (funksjon == 2) {
	    			System.out.println("Skriv inn apparatnavn");
	    			String apparatNavn = scanner.next();
	    			
	    			System.out.println("Skriv inn beskrivelse");
	    			String beskrivelse = scanner.next();
	    			
	    			t.lagApparat(apparatNavn, beskrivelse);

	    		}
	    		if (funksjon == 3) {
	    			System.out.println("Skriv inn ovelsesnavn");
	    			String ovelsenavn = scanner.next();
	    			System.out.println("Skriv inn antall sett");
	    			int sett = (scanner.nextInt());
	    			t.lagOvelse(ovelsenavn, sett);
	    			
	    			
	    		}
		    		
		    }
		    	
	    	if (fu == 2) {
	    		System.out.println("Skriv inn hvor mange okter du har lyst til aa se:");
	    		int n = scanner.nextInt();
		 			t.brukerOkt(bnavn,n);
	    	}
	 //   	Her kan du for hver enkelt ovelse se en resultatlogg i et gitt tidsintervall
	    	if (fu == 3) {
	    		System.out.println("Hvilken ovelse onsker du aa se resultatlogg for? ");
					String ovelse = scanner.next();
					
					System.out.println("Velg startdato paa format YYYY-MM-DD");
					String start = scanner.next();
					System.out.println("Velg sluttdato paa format YYYY-MM-DD");
					String slutt = scanner.next();
					
	    		t.resultatlogg(ovelse, start, slutt);
	    	}		    	

			
		    	
	    	if (fu == 4){
					System.out.println("Oensker du aa opprette en ny muskelgruppe, svar y dersom ja og n dersom nei");
	    		String svar = scanner.next();
	    		if(svar.equals("y")){
	    			System.out.println("Hvilke muskelgruppe oensker du aa lage en gruppe for? "); 
						String gnavn = scanner.next();
						System.out.println("Skriv en beskrivelse for gruppen: "); 
						String beskrivelse = scanner.next();
						t.lagOvelsesgruppe(gnavn, beskrivelse);
		    		System.out.println("Hvilke oevelser vil du at skal ligge i gruppen, skriv inn navn på oevelser");
		    		System.out.println("Velg en oevelse, og trykk enter. Naar du er ferdig kan du skrive 0");
		    		while (true) {
		    			String nyovelse = scanner.next();
		    			if (nyovelse.equals("0")) {
		    				break;
		    			}
		    			else {
		    				t.lagreOvelserIGruppe(nyovelse, gnavn);
		    			}
		    		}
	    		}
	    		System.out.println("Hvilken gruppe oensker du aa se oevelser fra?");
	    		String gruppeNavn = scanner.next();
	    		t.ovelsesgruppe(gruppeNavn);
	    	}
	   
	    	if (fu == 5) { 
			    System.out.println("Skriv navnet paa appartet for aa se hvilke oevelser som kan utfoeres med det: ");
			    String navn = scanner.next();
	    	  t.ovelsePaaApparat(navn);
	    	}	   
		}
	
    System.out.println("Ha det bra!");
 		scanner.close();
    }
	
	
	
    public static void main(String[] args) {
    	
    	
		startProgram();

	
    }
}