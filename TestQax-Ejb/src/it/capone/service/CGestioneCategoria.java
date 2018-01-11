package it.capone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.capone.bean.CategoriaBean;
import it.capone.dao.CategoriaDAO;
import it.capone.dao.CategoriaDAORemote;
import it.capone.entity.Categoria;


public class CGestioneCategoria {

	private CategoriaDAORemote categoriaDAO = new CategoriaDAO();
	
	
	public List<String> getCompletamenti(String testo) {
		List<String> categorie = categoriaDAO.getCompletamenti(testo);
		
		return categorie;
	}
	
	
	public Map<Integer, String> getCategorie() {
		
		List<Categoria> listaCategorie = categoriaDAO.getCategorie();
		Map<Integer, String> categorie = new HashMap<Integer, String>();
		
		if(!listaCategorie.isEmpty()) {
			for(Categoria c : listaCategorie) {
				categorie.put(c.getIdcategoria(), c.getNome());
			}
		}
		
		return categorie;
	}
	
	
	public void closeLogicaJPA() {
		categoriaDAO.closeLogicaJPA();
	}
	
//////////////Metodi utilità ////////////////////////////////
	
	/**
	* 
	* @param cat
	* @return Converte una Entity Categoria in un bean -> CategoriaBean
	*/
	private CategoriaBean fromCategoryToBean(Categoria cat) {
		CategoriaBean catBean = new CategoriaBean();
		catBean.setIdcategoria(cat.getIdcategoria());
		catBean.setNome(cat.getNome());
		
		return catBean;
	}
	
}
