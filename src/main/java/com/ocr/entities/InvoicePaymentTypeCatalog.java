package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvoicePaymentTypeCatalog {


 	@Id
    private String id;
    private String paymentType;
    private String owner;
    
    public InvoicePaymentTypeCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}



}
