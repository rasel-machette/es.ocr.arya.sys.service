package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvoiceCategoryCatalog {


 	@Id
    private String id;
    private String category;
    private String owner;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InvoiceCategoryCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}




}
