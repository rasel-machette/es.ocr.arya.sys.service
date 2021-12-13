package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvoiceTypeCatalog {


 	@Id
    private String id;
    private String type;
    private String owner;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InvoiceTypeCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}





}
