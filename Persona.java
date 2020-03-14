package rubrica.persone;

public class Persona {
	
	// Attributi di istanza Persona
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private int eta;
	
	//COSTRUTTORE 1
	public Persona(String nome, String cognome, String indirizzo, String telefono, int eta) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.eta = eta;
	}
	
	//COSTRUTTORE 2
	public Persona() {}

	
	
	//METODI
	public void setNome(String name) {
		nome = name;
	}

	public void setCognome(String surname) {
		cognome = surname;
	}

	public void setIndirizzo(String ind) {
		indirizzo = ind;
	}

	public void setTelefono(String p_number) {
		telefono = p_number;
	}

	public void setEta(int age) {
		eta = age;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public int getEta() {
		return eta;
	}

	
	//OVERRIDE TOSTRING
	@Override
	public String toString() {
		return this.nome + ";" + this.cognome +";" + this.indirizzo + ";" + this.telefono + ";" + this.eta + ";\n";	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
	

