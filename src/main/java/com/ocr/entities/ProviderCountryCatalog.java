package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProviderCountryCatalog {


 	@Id
    private String id;
    private String country;
    private String owner;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProviderCountryCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}





}
