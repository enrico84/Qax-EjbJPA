package it.capone.bean;

import it.capone.utility.Data;
import it.capone.utility.ErrMsg;

public class LoginBean {
	
	private int idutente;
	private String password;
	private String nome;
	private String email;
	private Data dataregistrazione;
	private ErrMsg errs;

	public LoginBean(int idUtente, String nome, String password, String email, Data dataregistrazione) {
		this.idutente = idUtente;
		this.nome = nome;
		this.password = password;
		this.email = email;
		this.dataregistrazione = dataregistrazione;
		this.errs = new ErrMsg();
	}
	
	public LoginBean()
	{
		//Costruttore Vuoto: rispetta il pattern di instanziazione dei bean
		//Instanzia questo bean nelle view dove viene utilizzato
	}
	
	
	/**
	 * @return the idutente
	 */
	public int getIdutente() {
		return idutente;
	}
	
	public void setIdUtente(int id) {
		this.idutente = id;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the dataregistrazione
	 */
	public Data getDataregistrazione() {
		return dataregistrazione;
	}
	

	/**
	 * @param dataregistrazione the dataregistrazione to set
	 */
	public void setDataregistrazione(Data dataregistrazione) {
		this.dataregistrazione = dataregistrazione;
	}
	
	
	public ErrMsg getErrs() {
		return errs;
	}

	
	public void setErrs(ErrMsg errs) {
		this.errs = errs;
	}
	
	public String getErrorMsg() {
		return "Utente o password non validi";
	}
	
	
	public String getErrorRegMsg() {
		return "Uno o più campi non sono validi";
	}
	

	/**
	 * Se l'utente è loggato, effettua un logout 
	 */
	public void clear() {
		this.nome=null;
		this.password=null;
	}
	
	
	/**
	 * Controlla se i dati presenti nel bean sono validi per
	 * entrare come utente loggato
	 * 
	 * @return true se è valido, false se ci sono dati mancanti o errati
	 */
	public boolean isValidLogin() {
		boolean valid = false;
		
		if( (nome != null) && !(nome.isEmpty()) && !(nome.equals("")) ) {
			if( (password != null) && !(password.isEmpty()) && !(password.equals("")) ) {
				valid = true;
				
			}
		}
		
		return valid;	
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginBean [password=" + password + ", nome=" + nome + ", email=" + email + ", dataregistrazione="
				+ dataregistrazione + "]";
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LoginBean))
			return false;
		LoginBean other = (LoginBean) obj;
		if (dataregistrazione == null) {
			if (other.dataregistrazione != null)
				return false;
		} else if (!dataregistrazione.equals(other.dataregistrazione))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	

}
