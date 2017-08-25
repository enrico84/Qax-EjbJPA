package it.capone.bean;

import it.capone.utility.Data;

public class RispostaBean {
	
	private int idrisposta;
	private String descrizione;
	private LoginBean utente;
	private Data dataCreazione;
	private DomandaBean domanda;
	
	public RispostaBean(int idrisposta, String descrizione, LoginBean utente, Data data, DomandaBean domanda) {
		this.idrisposta = idrisposta;
		this.descrizione = descrizione;
		this.utente = utente;
		this.dataCreazione = data;
		this.domanda = domanda;
	}

	
	/**
	 * @return the idrisposta
	 */
	public int getIdrisposta() {
		return idrisposta;
	}


	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the utente
	 */
	public LoginBean getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(LoginBean utente) {
		this.utente = utente;
	}

	/**
	 * @return the dataCreazione
	 */
	public Data getDataCreazione() {
		return dataCreazione;
	}

	/**
	 * @param dataCreazione the dataCreazione to set
	 */
	public void setDataCreazione(Data dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	/**
	 * @return the domanda
	 */
	public DomandaBean getDomanda() {
		return domanda;
	}

	/**
	 * @param domanda the domanda to set
	 */
	public void setDomanda(DomandaBean domanda) {
		this.domanda = domanda;
	}

	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RispostaBean [descrizione=" + descrizione + ", utente= " + utente.getNome()
				+ ", dataCreazione=" + dataCreazione + ", domanda= " + domanda.getTitolo() + "]";
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
		if (!(obj instanceof RispostaBean))
			return false;
		RispostaBean other = (RispostaBean) obj;
		if (dataCreazione == null) {
			if (other.dataCreazione != null)
				return false;
		} else if (!dataCreazione.equals(other.dataCreazione))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (domanda == null) {
			if (other.domanda != null)
				return false;
		} else if (!domanda.equals(other.domanda))
			return false;
		if (idrisposta != other.idrisposta)
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}



	
	
	
	
	
}
