package rubrica.persone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


/*
 * Classe dedicata alla lettura e alla scrittura su File
 //*/



public class DatiFile {

	//COSTRUTTORE
	private DatiFile() {}
	
	
	
	//METODO PER LA LETTURA
	public static Vector<Persona> carica() {
		
		Vector<Persona> vect = new Vector<Persona>();
		
		try {
			
			File myRubrica = new File(".\\rubrica.txt");
			
			if (!myRubrica.exists()) {
			
				System.out.println("File Creato: " + myRubrica.createNewFile());
	
			}else { 

		    	  System.out.println("Il file esiste gia.\nInizio Lettura");
		    	  
		    	  Scanner reader = new Scanner(myRubrica).useDelimiter("\\s*;\\s*");
		    	  
		          while (reader.hasNext()) {

		        	     Persona pers = new Persona();
		        	     pers.setNome(reader.next());
		        	     pers.setCognome(reader.next());
		        	     pers.setIndirizzo(reader.next());
		        	     pers.setTelefono(reader.next());
		        	     pers.setEta(reader.nextInt());

		        	     vect.add(pers);
		        	     
		          }
		          
		          reader.close();
			}      
			
			
			System.out.println("\nlettura terminata");  
		        
	          

		}catch (IOException e) {
			System.out.println("Errore.");
			e.printStackTrace();
        }
			
		return vect;
	}
	
	
	
	
	
	//METODO PER LA SCRITTURA
	public static void salva(Vector<Persona> personeLista){

		
		try {
			
    		FileWriter w = new FileWriter(".\\rubrica.txt");

    	    BufferedWriter b = new BufferedWriter (w);


    		for (Persona p : personeLista) {
	      		String nome = p.getNome();
	      		String cognome = p.getCognome();
	      		String indirizzo = p.getIndirizzo();
	      		String telefono = p.getTelefono();
	      		int eta = p.getEta();
	      		String format = nome + ";" + cognome +";" + indirizzo + ";" + telefono + ";" + eta + ";\n";
    		
	      		b.write(format);
    		}
    		
    		b.close();
      		
		}catch(IOException error) {
			System.out.println("Errore.");
			error.printStackTrace();
    	}
    			

		
	}

	
	
}
