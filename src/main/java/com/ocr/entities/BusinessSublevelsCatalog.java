package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessSublevelsCatalog {


 	@Id
 	private int id;
    private String mainCif;
    private String subbusiness1;
    private String subbusiness2;
    
    public BusinessSublevelsCatalog() {
    }

	public String getMainCif() {
		return mainCif;
	}

	public void setMainCif(String mainCif) {
		this.mainCif = mainCif;
	}

	public String getSubbusiness1() {
		return subbusiness1;
	}

	public void setSubbusiness1(String subbusiness1) {
		this.subbusiness1 = subbusiness1;
	}

	public String getSubbusiness2() {
		return subbusiness2;
	}

	public void setSubbusiness2(String subbusiness2) {
		this.subbusiness2 = subbusiness2;
	}



}
