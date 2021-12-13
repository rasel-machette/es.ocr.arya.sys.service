package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subbusiness1InfoCatalog {

	@Id
    private String id;
    private String name;
    private String address;
    
    public Subbusiness1InfoCatalog() {
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



}
