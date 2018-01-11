package it.capone.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import it.capone.entity.Categoria;

@Local
public interface CategoriaDAOLocal {
	
	public List<String> getCompletamenti(String testo);
	
	public List<Categoria> getCategorie();
	
	void closeLogicaJPA();

}
