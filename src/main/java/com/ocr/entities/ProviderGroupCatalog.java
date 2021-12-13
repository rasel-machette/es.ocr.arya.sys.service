package com.ocr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProviderGroupCatalog {


 	@Id
    private String id;
    private String groupType;
    private String owner;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProviderGroupCatalog() {
    }

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}



}
