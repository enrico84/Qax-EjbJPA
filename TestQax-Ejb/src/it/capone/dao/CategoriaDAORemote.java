package it.capone.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import it.capone.entity.Categoria;

@Remote
public interface CategoriaDAORemote {
	
	public List<String> getCompletamenti(String testo);
	
	public List<Categoria> getCategorie();
	
	void closeLogicaJPA();

}
