package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProviderContactCatalog {


 	@Id
    private String id;
    private String contactType;
    private String owner;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProviderContactCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}



}
