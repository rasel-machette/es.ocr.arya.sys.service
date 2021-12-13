package com.ocr.client.moqui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ocr.client.moqui.model.MoquiClientCatalogModel;

@Service
public class MoquiConnector implements Moqui {

	@Value("${moqui.uri}")
	String moquiUri;

	@Autowired
	RestTemplate restTemplate;

	private HttpEntity<String> getEntity(String json) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<String>(json,headers);

	}
	
	private HttpEntity<String> postHeaders(MoquiClientCatalogModel moquiClientCatalogModel) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MoquiClientCatalogModel> entity = new HttpEntity<MoquiClientCatalogModel>(moquiClientCatalogModel,
				headers);
		return new HttpEntity<String>(headers);

	}


	@Override
	public String postClient(MoquiClientCatalogModel moquiClientCatalogModel) {
		// TODO Auto-generated method stub
		
//		String result = restTemplate.exchange(moquiUri + "aryaAPI/aryaParties", HttpMethod.POST, postHeaders(moquiClientCatalogModel),
//				MoquiClientCatalogModel.class).getBody().toString();
//		System.out.println(result);
//return "";
		
		
		HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<MoquiClientCatalogModel> entity =new HttpEntity<MoquiClientCatalogModel>(moquiClientCatalogModel,headers);
	        
	        try {
	        	
	        restTemplate.exchange(
	        		moquiUri + "aryaAPI/aryaParty", HttpMethod.POST, entity, MoquiClientCatalogModel.class).getBody();
	       
	            }	
	        
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        return moquiClientCatalogModel.getPartyId();
		
		
		
	}

}
