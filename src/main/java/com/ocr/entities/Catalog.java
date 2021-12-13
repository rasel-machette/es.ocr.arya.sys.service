package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Catalog {


 	@Id
	private String id;
    private String tag;
    private String rule;
    
    public Catalog() {
    }

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}




}
