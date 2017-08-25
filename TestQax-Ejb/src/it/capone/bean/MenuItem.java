package it.capone.bean;

public class MenuItem {
	
	String label;
	String url;
	
	public MenuItem(String myLabel, String myUrl) {
		this.label = myLabel;
		this.url = myUrl;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
