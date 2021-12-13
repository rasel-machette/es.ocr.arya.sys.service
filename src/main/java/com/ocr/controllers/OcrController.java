package com.ocr.controllers;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.entities.BusinessCatalog;
import com.ocr.entities.BusinessInfoCatalog;
import com.ocr.entities.Callback;
import com.ocr.entities.Invoice;
import com.ocr.model.Archive;
import com.ocr.model.Event;
import com.ocr.model.FileList;
import com.ocr.model.Invoices;
import com.ocr.model.Predict;
import com.ocr.model.PredictInvoice;
import com.ocr.model.Reponse;
import com.ocr.model.Training;
import com.ocr.repositories.BusinessCatalogRepository;
import com.ocr.repositories.BusinessInfoCatalogRepository;
import com.ocr.repositories.CallbackRepository;
import com.ocr.repositories.InvoiceRepository;
import com.ocr.services.DigitalService;



@Controller
public class OcrController {

	@Value("${training.url}")
	private String trainingtUrl;
	
	@Value("${predict.url}")
	private String predictUrl;

	@Value("${predict.method}")
	private String predictMethod;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OcrController.class);

	private static final String NULL = null;
    
    @Autowired
    DigitalService digitalService;
    
    @Autowired
    CallbackRepository callbackRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    BusinessCatalogRepository businessCatalogRepository;
    
    @Autowired
    BusinessInfoCatalogRepository businessInfoCatalogRepository;

    @PutMapping(path = "/invoices", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Invoice>> updateInvoices(@RequestBody List<Invoice> invoices, @RequestParam(name = "owner", required = true) String owner) throws InvalidKeyException, IOException, ParseException {
    	Reponse resp = new Reponse();
    	
    	LOGGER.info("Update Invoices :"+owner);
    	for(Invoice invoice : invoices)
    	{
    		Invoice invoiceToUpdate = invoiceRepository.getOne(invoice.getId());
    		invoiceToUpdate.setCategory(invoice.getCategory());
    		invoiceToUpdate.setTypes(invoice.getTypes());
    		invoiceRepository.save(invoiceToUpdate);
    	}
    	
    	return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
    }
    

    @DeleteMapping(path = "/invoices", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Invoice>> deleteInvoices(@RequestBody List<Invoice> invoices, @RequestParam(name = "owner", required = true) String owner) throws InvalidKeyException, IOException, ParseException {
    	Reponse resp = new Reponse();
    	
    	LOGGER.info("Delete Invoices :"+owner);
    	for(Invoice invoice : invoices)
    	{
    		Invoice invoiceToDelete = invoiceRepository.getOne(invoice.getId());
    		invoiceToDelete.setId(invoice.getId());
    		invoiceRepository.delete(invoiceToDelete);
    	}
    	
    	return new ResponseEntity<List<Invoice>>(invoices, HttpStatus.OK);
    }
    
    @GetMapping(path = "/invoices", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Invoices> getInvoices(@RequestParam(name = "business", required = false) String business, @RequestParam(name = "owner", required = false) String owner, @RequestParam(name = "status", required = false) String status) throws InvalidKeyException, IOException, ParseException {
    	Reponse resp = new Reponse();
    	Invoices invoice = new Invoices();
    	
    	LOGGER.info("Get Invoices :"+owner);
    	
    	//List<Invoice> invoiceList = new ArrayList<Invoice>();
    	//List<Callback> callbackList = callbackRepository.findByowner(owner);
    	//for(Callback callback : callbackList)
    	//{
    	List<BusinessCatalog> businesses = businessCatalogRepository.findByowner(owner);
    	LOGGER.info("Get business :"+business);
    	ArrayList<Invoice> invoices = new ArrayList<Invoice>();
    	for(BusinessCatalog businessOwner: businesses)
    	{
    		invoices.addAll(invoiceRepository.findBybusinessOwner(businessOwner.getCif()));
    	}
    	
    	invoices.addAll(invoiceRepository.findBybusinessOwner(business));
    	//List<Invoice> invoices = invoiceRepository.findByowner(owner);
    	//}

    	/*if(!status.equals(""))
    		for(int i = 0; i < invoiceList.size(); i++)
    			if(!status.equals(invoiceList.get(i).getStatus()))
    			{
    				invoiceList.remove(i);
    				i--;
    			}*/
    	invoice.setInvoices(invoices);
    	return new ResponseEntity<Invoices>(invoice, HttpStatus.OK);
    }

    @PostMapping(path = "/files", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Reponse> processFiles(@RequestBody Archive archive, @RequestParam(name = "callbackUrl", required = true) String callbackUrl, @RequestParam(name = "owner", required = true) String owner, @RequestParam(name = "businessOwner", required = true) String businessOwner) throws InvalidKeyException, IOException{
    	Reponse resp = new Reponse();
    	LOGGER.info("Set Invoice :"+owner);
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    	//String id = UUID.randomUUID().toString().replace("-", "");
    	/*for(com.ocr.model.File file: files.getFiles())
    	{
        	Callback callback = new Callback();
        	callback.setUid(id);
        	callback.setCallbackUrl(callbackUrl);
        	callback.setFileName(file.getName());
        	callback.setOwner(owner);
        	callbackRepository.save(callback);
    	}*/

    	LOGGER.info("Init Get PushHotFolder ...");
    	int errorSaved = digitalService.pushHotfolder(archive);
    	LOGGER.info("Get PushHotFolder :: error Drive :: "+errorSaved);
    	
    	//for(com.ocr.model.Archive file: files.getFiles())
    	//{
        	
            Predict predict = new Predict();
            predict.setData(archive.getEncode()+"==");
            List<String> fieldList = new ArrayList<String>();
            //fieldList.add("filename");
            fieldList.add("transmittername");
            fieldList.add("transmittercif");
            fieldList.add("receptorname");
            fieldList.add("receptorcif");
            fieldList.add("invoicedate");
            fieldList.add("invoicenumber");
            fieldList.add("concept");
            //fieldList.add("imponiblebase");
            fieldList.add("total");
            //fieldList.add("paymenttype");
            fieldList.add("retention");
            //fieldList.add("taxes");
            //Bases e ivas
            fieldList.add("base0");
            fieldList.add("base1");
            fieldList.add("base2");
            fieldList.add("base3");
            fieldList.add("iva0");
            fieldList.add("iva1");
            fieldList.add("iva2");
            fieldList.add("iva3");
            predict.setFieldnames(fieldList);
            
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
    		try {
    		  json = mapper.writeValueAsString(predict);
    		} catch (JsonProcessingException e) {
    		   e.printStackTrace();
    		}

            
            HttpEntity<String> request = new HttpEntity<String>(json, headers);
            //Predict
            
            String resultAsJsonStr = "";
            PredictInvoice predictInvoice = new PredictInvoice();
    		try {
    			LOGGER.info("Predict: "+predictUrl+" :: "+predictMethod+" :: "+json);
    			resultAsJsonStr = restTemplate.postForObject(predictUrl+predictMethod, request, String.class);
                JsonNode jsonNode = mapper.readTree(resultAsJsonStr);
                String data = jsonNode.textValue();
    			predictInvoice = mapper.readValue(data, PredictInvoice.class);
    		} catch (Exception e) {
    		   LOGGER.info("Prediction fail: "+archive.getName());
     		   e.printStackTrace();
     		}

            
            
            //Parse invoice
            
            
            //JSONObject obj = new JSONObject(resultAsJsonStr);
            
            mapper = new ObjectMapper();
			//PredictInvoice predictInvoice = mapper.readValue(jsonNode, PredictInvoice.class);
            
        	Invoice invoice = new Invoice(); 
        	
        	///
        	invoice.setTransmitterName(predictInvoice.getTransmittername());
        	invoice.setTransmitterCif(predictInvoice.getTransmittercif());
        	invoice.setInvoiceNumber(predictInvoice.getInvoicenumber());
        	invoice.setInvoiceDate(predictInvoice.getInvoicedate());
        	invoice.setReceptorName(predictInvoice.getReceptorname());
        	invoice.setReceptorCif(predictInvoice.getReceptorcif());
        	invoice.setConcept(predictInvoice.getConcept());
        	invoice.setTotal(predictInvoice.getTotal());
        	invoice.setPaymentType(predictInvoice.getPaymenttype());
            invoice.setImportsD(0.0);
            invoice.setBruteTotalD(0.0);
            ///Bases
            invoice.setBase0(predictInvoice.getBase0());
            invoice.setBase1(predictInvoice.getBase1());
            invoice.setBase2(predictInvoice.getBase2());
            invoice.setBase3(predictInvoice.getBase3());
            
            //Ivas
            invoice.setIva0(predictInvoice.getIva0());
            invoice.setIva1(predictInvoice.getIva1());
            invoice.setIva2(predictInvoice.getIva2());
            invoice.setIva3(predictInvoice.getIva3());
            if(predictInvoice.getImponiblebase() != null)
            	invoice.setBase(predictInvoice.getImponiblebase());
            else
            	invoice.setBase("0.0");
            
            if(predictInvoice.getTaxes() != null)
            	invoice.setIva(predictInvoice.getTaxes());
            else
            	invoice.setIva("0.0");
            
            if(predictInvoice.getTotal() != null)
            	invoice.setTotal(predictInvoice.getTotal());
            else
            	invoice.setTotal("0.0");
            
            if(predictInvoice.getRetention() != null)
            	invoice.setRetentionImports(predictInvoice.getRetention());
            else
            	invoice.setRetentionImports("0.0");
            
            try {
            if(predictInvoice.getImponiblebase() != null)
            	invoice.setBaseD(digitalService.parseDouble(predictInvoice.getImponiblebase()));
            else
            	invoice.setBaseD(0.0);
            }catch(Exception e)
            {
            	invoice.setBaseD(0.0);
            }
            

            try {
            if(predictInvoice.getTaxes() != null)
            	invoice.setIvaD(digitalService.parseDouble(predictInvoice.getTaxes()));
            else
            	invoice.setIvaD(0.0);
            }
            catch(Exception e)
            {
            	invoice.setIvaD(0.0);
            }

            try {
            if(predictInvoice.getTotal() != null)
            	invoice.setTotalD(digitalService.parseDouble(predictInvoice.getTotal()));
            else
            	invoice.setTotalD(0.0);
            }
            catch(Exception e)
            {
            	invoice.setTotalD(0.0);
            }
            
            invoice.setIrpfD(0.0);
            invoice.setRetentionBaseD(0.0);
            

            try {
            if(predictInvoice.getRetention() != null)
            	invoice.setRetentionImportsD(digitalService.parseDouble(predictInvoice.getRetention()));
            else
            	invoice.setRetentionImportsD(0.0);
            }
            catch(Exception e)
            {
            	invoice.setRetentionImportsD(0.0);
            }
            
            invoice.setSurchargesPercentageD(0.0);
            invoice.setSurchargesBaseD(0.0);
            invoice.setSurchargesImportsD(0.0);
        	
        	invoice.setFileName(archive.getName());
        	//
        	headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ArrayList<Invoice> array_invoices = new ArrayList<>();
            array_invoices.add(invoice);
        	Invoices invoices = new Invoices();
        	invoices.setEncodedFile(archive.getEncode());
        	
        	LOGGER.info("Searching business ::- "+businessOwner);
        	businessOwner = businessOwner.substring(0,businessOwner.indexOf("::")-3);
        	List<BusinessCatalog> businesses = businessCatalogRepository.findBycif(businessOwner);
        	//Asignación del owner de validación
    		invoices.setOwner(owner);
        	if(businesses.size() > 1)
        	{
        		for(int i = 0; i < businesses.size(); i++)
        		{
        			BusinessCatalog bc = businesses.get(i);
        			if(bc.getOwner().equals(owner) && bc.getRole().equals("CONTROLLER"))
        			{
        				invoices.setOwner(owner);
        				i = businesses.size();
        			}
        			else if(bc.getRole().equals("CONTROLLER"))
                		invoices.setOwner(bc.getOwner());
        		}
        	}
            //Se deberá de asignar en caso de que el rol sea CUSTOMER a el grupo en BPM.

        	LOGGER.info("Searching business name :: "+businessOwner);
        	List<BusinessInfoCatalog> businessName = businessInfoCatalogRepository.findBycif(businessOwner);
        	if(businessName.size() > 0)
        	{
        		LOGGER.info("Setting name :: "+businessName.get(0).getName());
        		invoices.setBusinessOwnerName(businessName.get(0).getName());
        	}
        	else
        	{
        		LOGGER.info("Setting name :: NO REGISTRADO");
        		invoices.setBusinessOwnerName("NO REGISTRADO");
        	}
        		
        	
        	
            invoices.setBusinessOwner(businessOwner);
            invoices.setInvoices(array_invoices);
            //invoices.setCategories(digitalService.getCategories(owner));
            //invoices.setTypes(digitalService.getTypes(owner));
            HttpEntity<Invoices> requestInvoice = new HttpEntity<Invoices>(invoices, headers); 
            LOGGER.info("Invoking Invoicing :: "+callbackUrl);
            resultAsJsonStr = restTemplate.postForObject(callbackUrl, requestInvoice, String.class);
            LOGGER.info("Invoicing invoked :: "+archive.getName());
    		
    	//}
    	
        return new ResponseEntity<Reponse>(resp, HttpStatus.OK);
    }

    @PostMapping(path = "/invoices", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Reponse> processInvoices(@RequestBody Invoices invoices) throws InvalidKeyException, IOException, ParseException {
    	Reponse resp = new Reponse();
    	Invoice invoice = new Invoice();
        LOGGER.info("Set invoices: "+invoices.getInvoices().get(0).getFileName()+" :: "+invoices.getInvoices().get(0).getBusinessOwner());
        LOGGER.info("Count invoices: "+invoices.getInvoices().size());
        int val = 0;
        for(Invoice inv : invoices.getInvoices())
        {
        	if(!inv.getPercentageBase().equals("") && !inv.getPercentageBase().equals("0"))
        	{
        		inv.setId(inv.getOwner()+"::"+inv.getFileName()+"::"+val);
        		invoice = digitalService.setInvoices(inv);
        		val++;
        	}
        }
        if(val == 0)
        {
        	invoice = invoices.getInvoices().get(0);
    		invoice.setId(invoice.getOwner()+"::"+invoice.getFileName()+"::"+val);
    		invoice = digitalService.setInvoices(invoice);
        }
        
        invoice = invoices.getInvoices().get(0);
    	PredictInvoice train = new PredictInvoice();
        train.setTransmittername(invoice.getTransmitterName());
        train.setTransmittercif(invoice.getTransmitterCif());
        train.setReceptorname(invoice.getReceptorName());
        train.setReceptorcif(invoice.getReceptorCif());
        train.setInvoicedate(invoice.getInvoiceDate());
        //train.setInvoice_number(invoice.getInvoiceNumber());
        train.setInvoicenumber(invoice.getInvoiceNumber());
        train.setConcept(invoice.getConcept());
        //train.setImponiblebase(invoice.getBase());
        train.setTotal(invoice.getTotal());
        //train.setPaymenttype(invoice.getPaymentType());
        train.setRetention(invoice.getRetentionImports());
        //train.setTaxes(invoice.getIva());
        
        //Se debe incorprar el aprendizaje de los tipos de base e iva
        train.setBase0(invoice.getBase0());
        train.setBase1(invoice.getBase1());
        train.setBase2(invoice.getBase2());
        train.setBase3(invoice.getBase3());
        train.setIva0(invoice.getIva0());
        train.setIva1(invoice.getIva1());
        train.setIva2(invoice.getIva2());
        train.setIva3(invoice.getIva3());
        
    	RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
    	headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Training training = new Training();
        training.setFile(invoices.getEncodedFile());
        training.setFilename(invoices.getInvoices().get(0).getFileName());
        training.setCorrect_data(train);
        
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
		try {
		  json = mapper.writeValueAsString(training);
		  //System.out.println("ResultingJSONstring = " + json);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
        HttpEntity<String> requestInvoice = new HttpEntity<String>(json, headers); 
		
        try {
    	  LOGGER.info("Training  ..."+invoices.getInvoices().get(0).getFileName());
  		  String resultAsJsonStr = restTemplate.postForObject(trainingtUrl+"files", requestInvoice, String.class);
    	  LOGGER.info("Trained :: "+invoices.getInvoices().get(0).getFileName());
        } catch (Exception e) {
   		   e.printStackTrace();
   		   LOGGER.info("Training fail ..."+json);
   		}
    	resp.setRegistros_status(""+true);
        return new ResponseEntity<Reponse>(resp, HttpStatus.OK);
    }
    
    @PostMapping(value = "/drive", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<String> setFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "mime") String mimeType, @RequestParam(name = "parent", required = false) String parent){
		
		return new ResponseEntity<String>("Complete", HttpStatus.OK);
	}
    

    @GetMapping(path = "/test", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Date get1(@RequestParam(name = "value", required = true) String value) throws InvalidKeyException, IOException, ParseException {
        String date = value;
        String year = "";
        Invoice invoice = new Invoice();
        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        invoice.setInvoiceContableDateDt(dt1.parse(date));
         
        return dt1.parse(date);
    }
}
