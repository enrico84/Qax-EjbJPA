package it.capone.utility;

import java.util.ArrayList;
import java.util.List;

public class ErrMsg {
	
	private List<String> errori; 
	
	public ErrMsg() {
		errori = new ArrayList<String>();
	}
	
	public List<String> getErrori() {
		return this.errori;
	}
	
	public void add(String msg) {
		this.errori.add(msg);
	}
	
	public void listClear() {
		this.errori.clear();
	}
	
	public boolean isListErr() {
		return !errori.isEmpty();
	}

}
