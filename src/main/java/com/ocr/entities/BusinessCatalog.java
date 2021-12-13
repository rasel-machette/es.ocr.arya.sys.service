package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessCatalog {


 	@Id
    private String id;
    private String owner;
    private String cif;
    private String role;
    
    public BusinessCatalog() {
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}



}
