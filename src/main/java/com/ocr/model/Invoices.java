package com.ocr.model;

import java.util.ArrayList;
import java.util.List;

import com.ocr.entities.Invoice;

public class Invoices {

	private ArrayList<Invoice> invoices;
	private List<String> categories;
	private List<String> types;
	private String owner;
	private String encodedFile;
	private String businessOwner;
	private String businessOwnerName;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(ArrayList<Invoice> invoices) {
		this.invoices = invoices;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getEncodedFile() {
		return encodedFile;
	}
	public void setEncodedFile(String encodedFile) {
		this.encodedFile = encodedFile;
	}
	public String getBusinessOwner() {
		return businessOwner;
	}
	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}
	public String getBusinessOwnerName() {
		return businessOwnerName;
	}
	public void setBusinessOwnerName(String businessOwnerName) {
		this.businessOwnerName = businessOwnerName;
	}
}
