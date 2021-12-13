package com.ocr.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Business {


 	@Id
    private String id;
    private String owner;
    private String cif;
    private String name;
    
    public Business() {
    }


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



}
