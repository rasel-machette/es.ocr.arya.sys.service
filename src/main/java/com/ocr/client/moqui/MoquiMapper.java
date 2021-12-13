package com.ocr.client.moqui;

import org.springframework.beans.factory.annotation.Autowired;
import com.ocr.client.moqui.model.MoquiClientCatalogModel;
import com.ocr.client.moqui.model.MoquiConstants;
import com.ocr.entities.ClientCatalog;
import com.ocr.entities.InvoiceCategoryCatalog;

public class MoquiMapper {

	@Autowired   
	MoquiConnector moquiConnector; 
	
	public  static MoquiClientCatalogModel mapClientCatalogs(ClientCatalog clientCatalog) {

		MoquiClientCatalogModel model = new MoquiClientCatalogModel();
		
		model.setOrganizationName(clientCatalog.getOwner());
		model.setRoleTypeId(MoquiConstants.CLIENT_ROLE);
		model.setAddress1(clientCatalog.getAddress());
		model.setContactNumber(clientCatalog.getPhone());
		model.setAddress2(clientCatalog.getContactType());
		//model.setPostalAddressMechPurposeId(MoquiConstants.POSTAL_ADDRS_PURPOSE_ID);
		//model.setTelecomContactMechId(MoquiConstants.TELECOM_PURPOSE_ID);
	    
		model.toJson();
		
		return model;
	
		
	}

}
