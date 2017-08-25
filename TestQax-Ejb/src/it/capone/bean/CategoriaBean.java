package it.capone.bean;

public class CategoriaBean {
	
	private int idcategoria;
	private String nome;
	
	public CategoriaBean() {
		
	}
	
	public CategoriaBean(int id, String nome){
		this.idcategoria = id;
		this.nome = nome;
	}
	
	public int getIdcategoria() {
		return this.idcategoria;
	}

	
	public void setIdcategoria(int id) {
		this.idcategoria=id;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Override
	public String toString() {
		return "Categoria[ id = "+idcategoria+" -- nome = " +nome+ "]";
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equals=false;
		if(!(object instanceof CategoriaBean))
			return equals;
		
		 CategoriaBean other = (CategoriaBean) object;
	     if ( (this.idcategoria != 0 && other.idcategoria != 0) && this.idcategoria == (other.idcategoria) )  {
	            if( (this.nome != null && other.nome != null) && (this.nome.equals(other.nome)) ) {
	            	equals = true;
	            	return equals;
	            }
	     }   
	     
	     return equals;
	}
	
	
	

}
