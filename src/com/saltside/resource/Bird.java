package com.saltside.resource;

import java.util.Date;

public class Bird {
	String id;
	String name;
	String family;
	String[] continents;
	Date added;
	boolean visible;
	
	public Bird(String name, String family, String[] continents) {
		this.name = name;
		this.family = family;
		this.continents = continents;
		this.added = new Date();
		this.visible = true;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String[] getContinents() {
		return continents;
	}
	public void setContinents(String[] continents) {
		this.continents = continents;
	}
	public Date getAdded() {
		return added;
	}
	public void setAdded(Date added) {
		this.added = added;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
