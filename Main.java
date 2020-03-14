package rubrica.persone;
import java.util.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.*;  
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.jdi.event.EventQueue;   

public class Main {

	
	//MAIN
	public static void main(String[] args) {
		
  		//Lancia il primo script con la pagina principale della rubrica
		Vector <Persona> vect = DatiFile.carica();
		creaPaginaPrincipale(vect);
  		
		
		System.out.println("Apertura");
		System.out.close();
	 }
	

	
		
	/*METODO CREA PAGINA PRINCIPALE*/
	public static void creaPaginaPrincipale(Vector vect) {
		
		//costruttore pagina principale 
		JFrame paginaPrincipale = new JFrame("Rubrica");
		paginaPrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JTable tabella = new JTable();
		MyTableModel model = new MyTableModel(vect); 

		
		tabella.setModel(model);
		tabella.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JScrollPane sp = new JScrollPane(tabella); 
		//frame.add(tabella);
		sp.setBounds(100,100,800,400);
		paginaPrincipale.add(sp);
	
		
		/*costruttori bottoni*/
		JButton nuovo = new JButton("Nuovo");  
		JButton modifica = new JButton("Modifica");  
		JButton elimina = new JButton("Elimina");  
		
		
		/*set dimensioni bottoni*/
		nuovo.setBounds(200,600,75,25);
		modifica.setBounds(400,600,85,25);
		elimina.setBounds(600,600,75,25);
		
		
		/*Aggiungo bottoni alle rispettive pagine */	
		paginaPrincipale.add(nuovo);
	    paginaPrincipale.add(elimina);
	    paginaPrincipale.add(modifica);
	    
	    
	    /*setup pagina principale */
	    paginaPrincipale.setSize(1000,800);  
	    paginaPrincipale.setLayout(null);  
	    paginaPrincipale.setVisible(true); 	
	    	    
	
	    /*JBUTTON NUOVO*/
	    nuovo.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    		
	    		MyTableModel modello = (MyTableModel) tabella.getModel();
	    		MyDialog personaDaAggiungere = new MyDialog(modello,paginaPrincipale);
	    
	   

	    		personaDaAggiungere.pack();
	    		personaDaAggiungere.setVisible(true);
	   
	    	}
	    }); 
	    
	    
    
	    /*JBUTTON ELIMINA*/
	    elimina.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    		/*DEVE ELIMINARE*/
	    		
	    		
	    		JOptionPane messaggio = new JOptionPane(JOptionPane.CANCEL_OPTION);
	    		
	    		int currentSelectionIndex = tabella.getSelectionModel().getLeadSelectionIndex();
	    		if(currentSelectionIndex == -1) {
	    			messaggio.showMessageDialog(null, "Selezionare una persona da eliminare", "Errore Selezione", 0, null);
	    			return;
	    		}
	    		MyTableModel modello = (MyTableModel) tabella.getModel();
	    		int result = messaggio.showConfirmDialog(paginaPrincipale, "Vuoi davvero eliminare:\n" + modello.getPersona(currentSelectionIndex).getNome() + modello.getPersona(currentSelectionIndex).getCognome());
	    		if(result == 1 || result == 2 || result == -1) {
	    			return;
	    		}
	    		
	    		modello.deleteRow(currentSelectionIndex);
	    	
	    		DatiFile.salva(modello.getPersoneList());
	    		
	    		
	    		
	        }  
	    }); 
	    
	   
	    
	    /*JBUTTON MODIFICA*/
	    modifica.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	
	    		int currentSelectionIndex = tabella.getSelectionModel().getLeadSelectionIndex();
	    		if(currentSelectionIndex == -1) {
	    			JOptionPane.showMessageDialog(null, "Selezionare una persona da modificare", "Errore Selezione", 0, null);
	    			return;
	    		}
	    		MyTableModel modello = (MyTableModel) tabella.getModel();
	    		MyDialog personaDaModificare = new MyDialog(modello,paginaPrincipale, currentSelectionIndex);

	    		
	    		DatiFile.salva(modello.getPersoneList());
	    		
	    		personaDaModificare.pack();
	    		personaDaModificare.setVisible(true);

	    		
	        }  
	    }); 
	    

	}

	
}


		