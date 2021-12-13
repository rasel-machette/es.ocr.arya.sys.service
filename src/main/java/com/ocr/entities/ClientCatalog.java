package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientCatalog {


 	@Id
    private String id;
    private String name;
    private String nif;
    private String type;
    private String address;
    private String poblation;
    private String cp;
    private String providence;
    private String country;
    private String comercialName;
    private String vat;
    private String email;
    private String phone;
    private String mobile;
    private String website;
    private String contactType;
    private String groupType;
    private String owner;
    
    public ClientCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getPoblation() {
		return poblation;
	}

	public void setPoblation(String poblation) {
		this.poblation = poblation;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvidence() {
		return providence;
	}

	public void setProvidence(String providence) {
		this.providence = providence;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComercialName() {
		return comercialName;
	}

	public void setComercialName(String comercialName) {
		this.comercialName = comercialName;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



}
