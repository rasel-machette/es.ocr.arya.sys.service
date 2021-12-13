package com.ocr.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.entities.Catalog;
import com.ocr.entities.Classification;
import com.ocr.entities.ClientCatalog;
import com.ocr.entities.Invoice;
import com.ocr.entities.ProviderCatalog;
import com.ocr.model.Archive;
import com.ocr.model.FileList;
import com.ocr.model.Folder;
import com.ocr.repositories.CatalogRepository;
import com.ocr.repositories.ClassificationRepository;
import com.ocr.repositories.ClientCatalogRepository;
import com.ocr.repositories.FileRepository;
import com.ocr.repositories.InvoiceRepository;
import com.ocr.repositories.ProviderCatalogRepository;


@Service
public class DigitalService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DigitalService.class);

	@Value("${drive.url}")
	private String url;
	
    @Autowired
    ClientCatalogRepository clientCatalogRepository;
    
    @Autowired
    ClassificationRepository classificationRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FileRepository fileRepository;
    
    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    ProviderCatalogRepository providerCatalogRepository;
    
    public int pushHotfolder(Archive archive) throws JsonMappingException, JsonProcessingException {

    	String name =  archive.getName();
    	String classif = archive.getClassification();
    	String encode = archive.getEncode();
        RestTemplate restTemplate = new RestTemplate();
        String resultAsJsonStr  = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String path = "";
        String url_f = url;

        LOGGER.info("Classification: "+classif);
        List<Classification> classifications = classificationRepository.findByclassification(classif);
        LOGGER.info("Size classification: "+classifications.size());
        //LOGGER.info("Type classification: "+classifications);
        //LOGGER.info("classifications 0: "+classifications.get(0));
        //LOGGER.info("classifications path: "+classifications.get(0).getPath());
        
        
        if(!(classifications.size() > 0))
        {
            classifications = classificationRepository.findByclassification("invoices");
        	UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"folders")
 	                .queryParam("name", classif)
 	                .queryParam("parent", classifications.get(0).getPath());
 		    
 		    restTemplate = new RestTemplate();
 	        headers = new HttpHeaders();
 	        headers.setContentType(MediaType.APPLICATION_JSON);
 	        HttpEntity<?> request = new HttpEntity<>(headers); 
 	        path = restTemplate.postForObject(builder.toUriString(), request, String.class);

 			ObjectMapper mapper = new ObjectMapper();
 			Folder folder = mapper.readValue(path, Folder.class);
 	        Classification classification = new Classification();
 	        classification.setClassification(classif);
 	        classification.setPath(folder.getId());
 	        classificationRepository.save(classification);
 	        path = folder.getId();
        }
        else
        	path = classifications.get(0).getPath();
        //String jsonResult = UtilMethods.getJSON(this.jsonURL, null);

		
        
        //System.out.println("URL: "+url_f);
        int saved = 0;
        int errorSaved = 0;
        //for(Archive file:files.getFiles())
        //{
        	
        	
        	MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
        	ByteArrayResource resource = new ByteArrayResource(Base64.getDecoder().decode(encode)) {
        	    @Override
        	    public String getFilename() {
        	        return name;
        	    }
        	};
        	data.add("file", resource);
        	data.add("mime", "application/pdf");
        	data.add("parent", path);

        	HttpHeaders requestHeaders = new HttpHeaders();
        	requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        	HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data, requestHeaders);

        	 try {
             	resultAsJsonStr = restTemplate.postForObject(url_f+"files", requestEntity, String.class);
             	saved++;
             	LOGGER.info("Saved file: "+resultAsJsonStr);
 	        }
 	        catch(Exception e) {
 	            LOGGER.error("Error saving the file: "+name+" :: "+e.getMessage() );
 	            errorSaved++;	
 	        }

            //resp.setResultados_request(rl);
        //}
		return errorSaved;
    }
    
    public Invoice getFilesProcessed(String fileName) {
    	//List<Invoice> invoices = invoiceRepository.findByfileName(fileName);
    	List<com.ocr.entities.File> files = fileRepository.findAll();
    	Invoice invoice = new Invoice();
    	
    	//if(nif.substring(0,2).matches("[a-zA-Z][a-zA-Z]"))//!nif.equals("") && nif != null) && 
		//	vat = nif.substring(2,nif.length());
    	
    	for (com.ocr.entities.File fileO : files )
    	{
        	invoice = new Invoice();
    		String file = fileO.getFileContent();
    		//TOTAL rules
    		List<Catalog> catalogs = catalogRepository.findBytag("total");//Catalogo y ciclo
    	    Pattern pattern = Pattern.compile("(TOTAL|Total)+");//Catalogo y ciclo
    	    
    		//for(int i = 0; i < catalogs.size(); i++)
    		//{
    			//Catalog catalog = catalogs.get(i);
            	//if(file.contains(catalog.getRule()))
            	//{
    	    		int match = 0;
            	    Matcher matcher = pattern.matcher(file);
            	    while (matcher.find()) {
            	        //System.out.print("Start index: " + matcher.start());
            	        match = matcher.start();
            	        //System.out.print("End index: " + matcher.end());
            	        //System.out.println("Finded: "+file.substring(matcher.start(),file.length()));
            	        //System.out.println(invoice.getFileName()+" Found: " + matcher.group());
            	    }
                	

            	    Pattern subpattern = Pattern.compile("([0-9]+\\.)?[0-9]+,[0-9]+");//Va al catálogo y en un ciclo
            	    Matcher submatcher = subpattern.matcher(file.substring(match,file.length()));
            	    System.out.println("File name: "+fileO.getFileName());
            	    Double total = 0.0;
                	while (submatcher.find()) {
                	    System.out.println("DOUBLE: " + submatcher.group());
                		Double totalControl = Double.parseDouble(submatcher.group().replace(".", "").replace(",", "."));
                		if(totalControl > total)
                			total = totalControl;
                	}
            	    subpattern = Pattern.compile("([0-9]+,)?[0-9]+\\.[0-9]+");//Va al catálogo y en un ciclo
            	    submatcher = subpattern.matcher(file.substring(match,file.length()));
            	    System.out.println("File name: "+fileO.getFileName());

                	while (submatcher.find()) 
                	{
                		if(!file.substring(submatcher.end(),submatcher.end()+1).equals(","))
                		{
                    	    System.out.println("DOUBLE: " + submatcher.group());
                			Double totalControl = Double.parseDouble(submatcher.group().replace(",", ""));
                			if(totalControl > total)
                				total = totalControl;
                		}
                	}
            	    System.out.println("TOTAL: " + total);//Debe de encontrar el máximo valor con el . o la ,
            	    String id = UUID.randomUUID().toString().replace("-", "");
            	    invoice.setId(id);
            	    invoice.setFileName(fileO.getFileName());
            	    invoice.setTotal(total+"");
            	    //invoiceRepository.save(invoice);
            	    if(match+20 > file.length())
            	        System.out.println("******************"+file.substring(match,file.length()));
            	    else
            	        System.out.println("******************"+file.substring(match,match+20));
            	    	
        	        //System.out.println("Subpart: "+catalog.getRule()+" :: "+subpart.substring(0,subpart.length()));
                	//i = catalogs.size();
                	//System.out.println("File: "+fileName+" :: "+subpart.substring(subpart.indexOf(":"),15));
            	//}
    			
    		//}
    		//Fin rule 1
    	}
    	
    	//invoice = invoiceRepository.findByfileName(fileName).get(0);
    	//String file = invoice.getBase();
    	
		return invoice;
    }
    
    public List<String> getCategories(String owner) {
    	List<Invoice> invoices = invoiceRepository.findAll();
    	List<String> categories = new ArrayList<String>();
    	
    	for(Invoice invoice : invoices)
    	{
    		String category = invoice.getCategory();
    		if(category != null && invoice.getOwner().equals(owner))
    			categories.add(category);
    	}
		return categories;
    }

    public List<String> getTypes(String owner) {
    	List<Invoice> invoices = invoiceRepository.findAll();
    	List<String> types = new ArrayList<String>();
    	
    	for(Invoice invoice : invoices)
    	{
    		String type = invoice.getTypes();
    		if(type != null && invoice.getOwner().equals(owner))
    			types.add(type);
    	}
		return types;
    }

    public Invoice setInvoices(Invoice invoice) throws ParseException {
    	 invoice.setImportsD(0.0);
         invoice.setBruteTotalD(0.0);
         
         //Base total
         Double base0 = 0.0;
         Double base1 = 0.0;
         Double base2 = 0.0;
         Double base3 = 0.0;
         Double percentage = 0.0;
         if(invoice.getBase0() != null)
          	base0 = parseDouble(invoice.getBase0());
         if(invoice.getBase1() != null)
         	base1 = parseDouble(invoice.getBase1());
         if(invoice.getBase2() != null)
          	base2 = parseDouble(invoice.getBase2());
         if(invoice.getBase3() != null)
          	base3 = parseDouble(invoice.getBase3());
         if(invoice.getPercentageBase() != null && invoice.getPercentageBase() != "")
        	percentage = parseDouble(invoice.getPercentageBase());
         else
        	percentage = 100.0;
          
         invoice.setBaseD(((base0+base1+base2+base3)*percentage)/100);
         invoice.setBase0D(base0*percentage/100);
         invoice.setBase1D(base1*percentage/100);
         invoice.setBase2D(base2*percentage/100);
         invoice.setBase3D(base3*percentage/100);
         
         
         //IVA total
         Double iva0 = 0.0;
         Double iva1 = 0.0;
         Double iva2 = 0.0;
         Double iva3 = 0.0;
         if(invoice.getIva0() != null)
        	 iva0 = parseDouble(invoice.getIva0());
         if(invoice.getIva1() != null)
        	 iva1 = parseDouble(invoice.getIva1());
         if(invoice.getIva2() != null)
        	 iva2 = parseDouble(invoice.getIva2());
         if(invoice.getIva3() != null)
        	 iva3 = parseDouble(invoice.getIva3());
         
         invoice.setIvaD(((iva0+iva1+iva2+iva3)*percentage)/100);
         invoice.setIva0D(iva0*percentage/100);
         invoice.setIva1D(iva1*percentage/100);
         invoice.setIva2D(iva2*percentage/100);
         invoice.setIva3D(iva3*percentage/100);
         
         ///
         if(invoice.getTotal() != null)
         {
         	invoice.setTotalD(parseDouble(invoice.getTotal())*percentage/100);
         	invoice.setTotal(invoice.getTotal());
         }
         else
         {
          	invoice.setTotal("0.0");
         	invoice.setTotalD(0.0);
         }
         
         
         invoice.setIrpfD(0.0);
         invoice.setRetentionBaseD(0.0);
         if(invoice.getRetentionImports() != null)
         {
          	invoice.setRetentionImports(invoice.getRetentionImports());
         	invoice.setRetentionImportsD(parseDouble(invoice.getRetentionImports())*percentage/100);
         }
         else
         {
          	invoice.setRetentionImports("0.0");
         	invoice.setRetentionImportsD(0.0);
         }
         
         
         invoice.setSurchargesPercentageD(0.0);
         invoice.setSurchargesBaseD(0.0);
         invoice.setSurchargesImportsD(0.0);
         
         List<ProviderCatalog> providers = providerCatalogRepository.findAll();
         for(int index = 0; index < providers.size(); index++)
         {
        	 if(!providers.get(index).getNif().equals("") && !providers.get(index).getName().equals(""))
        	 {
	             if(invoice.getTransmitterCif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "").contains(providers.get(index).getNif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "")) || providers.get(index).getNif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "").contains(invoice.getTransmitterCif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "")))
				 {
					 invoice.setTransmitterName(providers.get(index).getName());
					 invoice.setTransmitterCif(providers.get(index).getNif().replace("-", "").replace("/", "").replace(".", "").replace(" ", ""));
					 index = providers.size();
				 }
	             else
	             {
	            	 //String name = invoice.getTransmitterName();
	            	 String cif = invoice.getTransmitterCif();
					 //invoice.setTransmitterName(name);
					 invoice.setTransmitterCif(cif.replace("-", "").replace("/", "").replace(".", "").replace(" ", ""));
	             }
        	 }
         }
         

         List<ClientCatalog> clients = clientCatalogRepository.findAll();
         for(int index = 0; index < clients.size(); index++)
         {
        	 if(!clients.get(index).getNif().equals("") && !clients.get(index).getName().equals(""))
        	 {
	             if(invoice.getReceptorCif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "").contains(clients.get(index).getNif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "")) || clients.get(index).getNif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "").contains(invoice.getReceptorCif().replace("-", "").replace("/", "").replace(".", "").replace(" ", "")))
				 {
					 invoice.setReceptorName(clients.get(index).getName());
					 invoice.setReceptorCif(clients.get(index).getNif().replace("-", "").replace("/", "").replace(".", "").replace(" ", ""));
					 index = clients.size();
				 }
	             else
	             {
	            	 //String name = invoice.getTransmitterName();
	            	 String cif = invoice.getReceptorCif();
					 //invoice.setTransmitterName(name);
					 invoice.setReceptorCif(cif.replace("-", "").replace("/", "").replace(".", "").replace(" ", ""));
	             }
        	 }
         }
         
         String mes = "";
         try{
        	 if(invoice.getInvoiceDate().toLowerCase().indexOf("ene") > 0 || invoice.getInvoiceDate().toLowerCase().indexOf("jan") > 0)
        	 {
        		 mes = "ENERO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("feb") > 0)
        	 {
        		 mes = "FEBRERO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("mar") > 0)
        	 {
        		 mes = "MARZO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("abr") > 0 || invoice.getInvoiceDate().toLowerCase().indexOf("apr") > 0)
        	 {
        		 mes = "ABRIL";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("may") > 0)
        	 {
        		 mes = "MAYO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("jun") > 0)
        	 {
        		 mes = "JUNIO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("jul") > 0)
        	 {
        		 mes = "JULIO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("ago") > 0 || invoice.getInvoiceDate().toLowerCase().indexOf("aug") > 0)
        	 {
        		 mes = "AGOSTO";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("sep") > 0)
        	 {
        		 mes = "SEPTIEMBRE";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("oct") > 0)
        	 {
        		 mes = "OCTUBRE";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("nov") > 0)
        	 {
        		 mes = "NOVIEMBRE";
        	 }
        	 else if(invoice.getInvoiceDate().toLowerCase().indexOf("dic") > 0 || invoice.getInvoiceDate().toLowerCase().indexOf("dec") > 0)
        	 {
        		 mes = "DICIEMBRE";
        	 }
        	 else if(invoice.getInvoiceDate().indexOf("-") > 0)
        	 {
        		 mes = invoice.getInvoiceDate().substring(invoice.getInvoiceDate().indexOf("-")+1,invoice.getInvoiceDate().indexOf("-")+3);
        		 if(mes.equals("01"))
        			 mes = "ENERO";
        		 if(mes.equals("02"))
            		 mes = "FEBRERO";
        		 if(mes.equals("03"))
        			 mes = "MARZO";
        		 if(mes.equals("04"))
        			 mes = "ABRIL";
        		 if(mes.equals("05"))
        			 mes = "MAYO";
        		 if(mes.equals("06"))
        			 mes = "JUNIO";
        		 if(mes.equals("07"))
        			 mes = "JULIO";
        		 if(mes.equals("08"))
        			 mes = "AGOSTO";
        		 if(mes.equals("09"))
        			 mes = "SEPTIEMBRE";
        		 if(mes.equals("10"))
        			 mes = "OCTUBRE";
        		 if(mes.equals("11"))
        			 mes = "NOVIEMBRE";
        		 if(mes.equals("12"))
        			 mes = "DICIEMBRE";
        	 }
        	 else if(invoice.getInvoiceDate().indexOf("/") > 0)
        	 {
        		 mes = invoice.getInvoiceDate().substring(invoice.getInvoiceDate().indexOf("/")+1,invoice.getInvoiceDate().indexOf("/")+3);
        		 if(mes.equals("01"))
        			 mes = "ENERO";
        		 if(mes.equals("02"))
            		 mes = "FEBRERO";
        		 if(mes.equals("03"))
        			 mes = "MARZO";
        		 if(mes.equals("04"))
        			 mes = "ABRIL";
        		 if(mes.equals("05"))
        			 mes = "MAYO";
        		 if(mes.equals("06"))
        			 mes = "JUNIO";
        		 if(mes.equals("07"))
        			 mes = "JULIO";
        		 if(mes.equals("08"))
        			 mes = "AGOSTO";
        		 if(mes.equals("09"))
        			 mes = "SEPTIEMBRE";
        		 if(mes.equals("10"))
        			 mes = "OCTUBRE";
        		 if(mes.equals("11"))
        			 mes = "NOVIEMBRE";
        		 if(mes.equals("12"))
        			 mes = "DICIEMBRE";
        	 }
        	 else if(invoice.getInvoiceDate().indexOf(".") > 0)
        	 {
        		 mes = invoice.getInvoiceDate().substring(invoice.getInvoiceDate().indexOf(".")+1,invoice.getInvoiceDate().indexOf(".")+3);
        		 if(mes.equals("01"))
        			 mes = "ENERO";
        		 if(mes.equals("02"))
            		 mes = "FEBRERO";
        		 if(mes.equals("03"))
        			 mes = "MARZO";
        		 if(mes.equals("04"))
        			 mes = "ABRIL";
        		 if(mes.equals("05"))
        			 mes = "MAYO";
        		 if(mes.equals("06"))
        			 mes = "JUNIO";
        		 if(mes.equals("07"))
        			 mes = "JULIO";
        		 if(mes.equals("08"))
        			 mes = "AGOSTO";
        		 if(mes.equals("09"))
        			 mes = "SEPTIEMBRE";
        		 if(mes.equals("10"))
        			 mes = "OCTUBRE";
        		 if(mes.equals("11"))
        			 mes = "NOVIEMBRE";
        		 if(mes.equals("12"))
        			 mes = "DICIEMBRE";
        	 }
         }
        	
         
         catch(Exception e) {
        	 
         }
         

         
         // *** same for the format String below
         try
         {
         SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
         invoice.setInvoiceContableDateDt(dt1.parse(invoice.getInvoiceContableDate()));
         }
         catch(Exception e)
         {
        	 LOGGER.error("Date process error ... "+invoice.getInvoiceContableDate());
         }
         
    	invoice.setInvoiceDate(mes);
    	
    	if(invoice.getTransmitterCif().contains(invoice.getBusinessOwner()) || invoice.getBusinessOwner().contains(invoice.getTransmitterCif()))
    		invoice.setInvoiceType("EMITIDA");
    	else if(invoice.getReceptorCif().contains(invoice.getBusinessOwner()) || invoice.getBusinessOwner().contains(invoice.getReceptorCif()))
    		invoice.setInvoiceType("RECIBIDA");
    	else
    		invoice.setInvoiceType("NO DEFINIDA");
    		
    	LOGGER.info("Saving ... "+invoice.getFileName()+" :: "+invoice.getBusinessOwner()+" :: "+invoice.getOwner());
    	Invoice invoices = invoiceRepository.save(invoice);
    	LOGGER.info("Updated invoice saved in DB :: "+invoice.getFileName());
    	boolean result = false;
    	if(invoices != null)
    		result  = true;
		return invoice;
    }
    
    
    public Double parseDouble(String num) {
    	Double d = 0.0;
    	num = num.replace(" ","");
    	try
    	{
	    	if(num.replace(" ","") != "" && !num.equals("") && num != null && num != "null")
	    	{
		    	if(num.contains(",") && !num.contains("."))
		    		num = num.replace(",",".");
		    	else if(num.indexOf(",")<num.indexOf("."))
		    	{
		    		num = num.replace(",","");
		    		String s1 = num.substring(num.lastIndexOf("."), num.length());
		    		String s2 = num.substring(0,num.lastIndexOf("."));
		    		num = s2.replace(".", "")+s1;
		    	}
		    	else if(num.indexOf(".")<num.indexOf(","))
		    		num = num.replace(".","").replace(",", ".");
		    	//LOGGER.info("Procesamiento de importe:"+num);
		    	d = Double.parseDouble(num);
	    	}
    	} 
    	catch (Exception e) {
    		// TODO Auto-generated catch block
    		LOGGER.error("Procesamiento de importe:"+num);
    	}
		return d;
    }
}
