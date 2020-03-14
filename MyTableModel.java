package rubrica.persone;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;




/*
 * MyTableModel estende AbstractTableModel e permette di creare il modello che gestisce la tabella inserita nella paginaPrincipale
 //*/
public class MyTableModel extends AbstractTableModel {
	
	 //vettore che contiene i dati delle persone
	Vector personeList;
	//header della tabella
	String headerColonne[] = new String[]{"Nome","Cognome","Telefono"};
	 
	//COSTRUTTORE 1
	public MyTableModel(Vector<Persona> vect) {
		personeList = vect;
	}
	 
	
	//metodo restituisce il numero di colonne
	@Override
	public int getColumnCount(){
		return 3;
	}
	 
	
	//metodo restituisce numer odi righe 
	@Override
	public int getRowCount(){
		return personeList.size();
	}
	 
	//metodo per cancellazione di una riga aggiornando il modello 
	public void deleteRow(int index) {
		personeList.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	//metodo per ottenere una persona all'interno del vettore tramite indice
	public Persona getPersona(int index){
		return (Persona) personeList.get(index);
	}
	
	//mmetodo per aggiunta di una persona al vettore
	public void addPersona(Persona personaDaAggiungere) {
		personeList.add(personaDaAggiungere);
    	fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}
	//metodo per la modifica di una persona 
	public void modificaPersona(int index, Persona daModificare) {
		personeList.set(index, daModificare);
		fireTableRowsUpdated(index, index);
	}
	
	//metodo per la restituizione del vettore che continene i dati delle persone all'interno della rubrica
	public Vector<Persona> getPersoneList(){
		return personeList;
	}
	
	
	
	//metodo assegnazione dei valori alle varie colonne 
	@Override
	public Object getValueAt(int row, int column) {
		Persona entity = null;
		entity= (Persona) personeList.get(row);
	 
		switch (column) {
		 
			case 0:
			return entity.getNome();
			case 1:
			return entity.getCognome();
			case 2:
			return entity.getTelefono();
			 
			default :
			}
		return "";
	}
	 
	 
	 //metodo per mostrare gli header delle colonne
	public String getColumnName(int col) {
		return headerColonne[col];
	}
	
}
