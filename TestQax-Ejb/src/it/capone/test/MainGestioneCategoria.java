package it.capone.test;

import java.util.List;
import java.util.Map;

import it.capone.bean.CategoriaBean;
import it.capone.service.CGestioneCategoria;

public class MainGestioneCategoria {

	public static void main(String args[]) {
	
		CGestioneCategoria cGestCat = new CGestioneCategoria();
		
		String testo = "Ja";
		//List<String> completamenti = cGestCat.getCompletamenti(testo);   //OK
		//stampaCategorie(completamenti);
		
		//Map<Integer, String> categorie = cGestCat.getCategorie();        //OK
		//stampaCateg(categorie);
		
		 //OPERAZIONE DA EFFETTUARE SEMPRE ALLA FINE PER CHIUDERE L'ENTITY MANAGER
		cGestCat.closeLogicaJPA();
		
	}
	
	public static void stampaCategorie(List<String> completamenti) {
		for(String cat : completamenti) {
			System.out.println("Categoria: " +cat+ "\n");
		}
	}
	
	
	public static void stampaCateg(Map<Integer, String> categorie) {
		for (Map.Entry<Integer, String> cat: categorie.entrySet()) {
		    Object key = cat.getKey();
		    Object value= cat.getValue();
		    System.out.println("Categoria -> id:" +key+ "-- nome: " +value+ "\n");
		}

	}
	
	
}
