package rubrica.persone;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 *Classe MyDialog che estende JDialog utilizzata per la creazione dei JDialog quando viene richiesto 
 *di aggiungere una nuova persona (JButton NUOVO del JFrame paginaPrincipale) e per la modifica
 *di una persona precedente (JButton MODIFICA del JFrame pagina Principale) distinti in due costruttori differenti
 //*/






public class MyDialog extends JDialog{
	
	
	//ATTRIBUTI ISTANZA
	private JTextField nome,cognome,indirizzo,telefono,eta;
	private JButton salva,esci;
	
	
	
	
	
	
	
	private MyDialog(JFrame paginaPrincipale) {

		//richiama classe padre JDialog gia definita ed estesa da MyDialog
		super(paginaPrincipale,"EDITOR PERSONA",true);
		
		
		//Chiude al click sulla X
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// creazione JPanel 
	    JPanel p1 = new JPanel(); 
	 
	    
	    // Layout della tabella
	    p1.setLayout(new GridLayout(7,2,10,10)); 

	    
	    // creazione JLable 
	    JLabel nomeLabel, cognomeLabel, indirizzoLabel, telefonoLabel, etaLabel; 
	   
	  
	    //INIZIALIZZAZIONE JLABLE nomeLabel
	    nomeLabel = new JLabel("Nome"); 
	  
	    // INIZIALIZZAZIONE JTEXTFIELD nome
	    nome = new JTextField(20); 
	  
	    //INIZIALIZZAZIONE JLABLE cognomeLabel
	    cognomeLabel = new JLabel("cognome"); 
	  
	    // INIZIALIZZAZIONE JTEXTFIELD cognome
	    cognome = new JTextField(20); 
	  
	    //INIZIALIZZAZIONE JLABLE 	indirizzoLabel
	    indirizzoLabel = new JLabel("indirizzo"); 
	  
	    // INIZIALIZZAZIONE JTEXTFIELD indirizzo
	    indirizzo = new JTextField(20); 
	  
	    //INIZIALIZZAZIONE JLABLE telefonoLabel
	    telefonoLabel = new JLabel("telefono"); 
	  
	    // INIZIALIZZAZIONE JTEXTFIELD telefono
	    telefono = new JTextField(20); 
	    
	    //INIZIALIZZAZIONE JLABLE  etaLabel
	    etaLabel = new JLabel("Eta"); 
	  
	    // INIZIALIZZAZIONE JTEXTFIELD eta
	    eta = new JTextField(5); 
	  
	    //INIZIALIZZAZIONE JBUTTON salva
	    salva = new JButton("SALVA"); 
	  
	    //INIZIALIZZAZIONE JBUTTON esci
	    esci = new JButton("ESCI"); 

	    // AGGIUNGO JLABEL E JTEXTFIELD AL JPANEL 
	    p1.add(nomeLabel); 

	    p1.add(nome); 

	    p1.add(cognomeLabel); 

	    p1.add(cognome); 

	    p1.add(indirizzoLabel); 
	
	    p1.add(indirizzo); 
	
	    p1.add(telefonoLabel); 
	
	    p1.add(telefono); 
	    
	    p1.add(etaLabel); 

	    p1.add(eta); 
	
	    p1.add(salva); 
	    
	    p1.add(esci); 
	  

	    //impostazioni del contenuto del pannello p1
		this.setContentPane(p1);


	    

	    //istanza di JDialog per la chiusura
	    this.esci.addActionListener((c) -> this.dispose());


	    
	 }  

	
	
		
	// COSTRUTTORE 1 (esegue il salvataggio una volta creato un nuovo utente in rubrica)
	public MyDialog(MyTableModel modello, JFrame paginaPrincipale) {
		
		this(paginaPrincipale);


		this.salva.addActionListener((s) -> {
	    	Persona daAggiungere = new Persona();
	    	daAggiungere.setNome(this.nome.getText());
	    	daAggiungere.setCognome(this.cognome.getText());
	    	daAggiungere.setIndirizzo(this.indirizzo.getText());
	    	daAggiungere.setTelefono(this.telefono.getText());
	    	if(verificaEta(daAggiungere) == true) { 
	    	daAggiungere.setEta(Integer.parseInt(this.eta.getText()));
	    	
	    	
	    	modello.addPersona(daAggiungere);
	    	
    		DatiFile.salva(modello.getPersoneList());
    		
	    	this.dispose();
	    	}
	    	
	    });
		
		
	}
	
	

	
	//COSTRUTTORE 2 (sempre per il salvataggio ma lavora sulle persone modificate )
	public MyDialog(MyTableModel modello, JFrame paginaPrincipale, int currentSelectionIndex){
		
		this(paginaPrincipale);
		
		Persona personaDaModificare = modello.getPersona(currentSelectionIndex);
		
		this.nome.setText(personaDaModificare.getNome());
		this.cognome.setText(personaDaModificare.getCognome());
		this.indirizzo.setText(personaDaModificare.getIndirizzo());
		this.telefono.setText(personaDaModificare.getTelefono());
		this.eta.setText(Integer.toString(personaDaModificare.getEta()));

		
	    this.salva.addActionListener((s) -> {
	    	Persona daAggiungere = new Persona();
	    	daAggiungere.setNome(this.nome.getText());
	    	daAggiungere.setCognome(this.cognome.getText());
	    	daAggiungere.setIndirizzo(this.indirizzo.getText());
	    	daAggiungere.setTelefono(this.telefono.getText());
	    	daAggiungere.setEta(Integer.parseInt(this.eta.getText()));
	    	
	    	
	    	modello.modificaPersona(currentSelectionIndex,daAggiungere);

   		DatiFile.salva(modello.getPersoneList());
    		
	    	this.dispose();
	    	
	    });
	
	}


	
	//METODO PRIVATO PER IL CONTROLLO DELL'ETA (INTERO)
	private boolean verificaEta(Persona daAggiungere) {
		
		JOptionPane messaggio = new JOptionPane(JOptionPane.CANCEL_OPTION);
		boolean bool=true;
		
		if (this.eta.getText()!= null){
	        try{
	            Integer.parseInt(this.eta.getText());
	        } catch (NumberFormatException e) {
	        	messaggio.showMessageDialog(null, "Eta deve essere un Intero", "Errore Inserimento", 0 , null);
	        	bool = false;
	        	this.dispose();
	        }
	    }
		return bool;
	}     
  
	
	
	
}
	


