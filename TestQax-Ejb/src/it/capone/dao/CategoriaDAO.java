package it.capone.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import it.capone.db.LogicaJPA;
import it.capone.entity.Categoria;


/**
 * Session Bean implementation class CategoriaDAO
 */
@Stateless
public class CategoriaDAO implements CategoriaDAOLocal, CategoriaDAORemote {
	
//	@PersistenceContext(unitName="Qax-EJB")
//	protected EntityManager em;	
	
	LogicaJPA logica = new LogicaJPA("TestQax-Ejb");
	
	@Override
	public List<String> getCompletamenti(String testo) {
		
		List<String> categorie = null;
		try {
			String query = "SELECT c.nome FROM Categoria c WHERE c.nome LIKE '"+testo+"%' ORDER BY c.nome";
			categorie = (ArrayList<String>) logica.readSimpleQuery(query).getResultList();
			
		} catch(NoResultException ex) {
			categorie = new ArrayList<String>();
		}
		
		return categorie;
		
		
	}

	@Override
	public List<Categoria> getCategorie() {
		
		List<Categoria> categorie = null;
		
		try {
			
			String query = "SELECT c FROM Categoria c ORDER BY c.nome";
			categorie = (ArrayList<Categoria>) logica.readSimpleQuery(query).getResultList();
			
		}catch(NoResultException ex){
			categorie = new ArrayList<Categoria>();
		}
		
		return categorie;
		
	}
	
	
	public void closeLogicaJPA() {
		logica.closeLogicaJPA();
	}
}
