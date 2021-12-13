package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessInfoCatalog {


 	@Id
    private String cif;
    private String name;
    private String address;
    
    public BusinessInfoCatalog() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}
