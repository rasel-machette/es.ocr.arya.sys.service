package com.ocr.controllers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ocr.client.moqui.MoquiMapper;
import com.ocr.client.moqui.model.MoquiClientCatalogModel;
import com.ocr.client.moqui.MoquiConnector;
import com.ocr.client.moqui.Moqui;
import com.ocr.entities.BusinessCatalog;
import com.ocr.entities.BusinessInfoCatalog;
import com.ocr.entities.ClientCatalog;
import com.ocr.entities.InvoiceCategoryCatalog;
import com.ocr.entities.InvoicePaymentTypeCatalog;
import com.ocr.entities.InvoiceTypeCatalog;
import com.ocr.entities.ProviderCatalog;
import com.ocr.entities.ProviderContactCatalog;
import com.ocr.entities.ProviderCountryCatalog;
import com.ocr.entities.ProviderGroupCatalog;
import com.ocr.model.Business;
import com.ocr.model.Catalogs;
import com.ocr.model.Reponse;
import com.ocr.repositories.BusinessCatalogRepository;
import com.ocr.repositories.BusinessInfoCatalogRepository;
import com.ocr.repositories.ClientCatalogRepository;
import com.ocr.repositories.InvoiceCategoryCatalogRepository;
import com.ocr.repositories.InvoicePaymentTypeCatalogRepository;
import com.ocr.repositories.InvoiceRepository;
import com.ocr.repositories.InvoiceTypeCatalogRepository;
import com.ocr.repositories.ProviderCatalogRepository;
import com.ocr.repositories.ProviderContactCatalogRepository;
import com.ocr.repositories.ProviderCountryCatalogRepository;
import com.ocr.repositories.ProviderGroupCatalogRepository;

@RestController
public class OcrCatalogsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OcrCatalogsController.class);

	private static final String NULL = null;

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	ClientCatalogRepository clientCatalogRepository;
	@Autowired
	InvoiceCategoryCatalogRepository invoiceCategoryCatalogRepository;
	@Autowired
	InvoiceTypeCatalogRepository invoiceTypeCatalogRepository;
	@Autowired
	ProviderCatalogRepository providerCatalogRepository;
	@Autowired
	ProviderContactCatalogRepository providerContactCatalogRepository;
	@Autowired
	ProviderCountryCatalogRepository providerCountryCatalogRepository;
	@Autowired
	ProviderGroupCatalogRepository providerGroupCatalogRepository;
	@Autowired
	InvoicePaymentTypeCatalogRepository invoicePaymentTypeCatalogRepository;
	@Autowired
	BusinessCatalogRepository businessCatalogRepository;
	@Autowired
	BusinessInfoCatalogRepository businessInfoCatalogRepository;

	@Autowired
	MoquiConnector moqui;
	

	@GetMapping(path = "/clients", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<ClientCatalog> getClients(@RequestParam(name = "owner", required = true) String owner)
			throws InvalidKeyException, IOException, ParseException {
		ClientCatalog resp = new ClientCatalog();
		List<ClientCatalog> clients = clientCatalogRepository.findByowner(owner);
		if (clients.size() > 0)
			resp = clientCatalogRepository.findByowner(owner).get(0);
		return new ResponseEntity<ClientCatalog>(resp, HttpStatus.OK);
	}

	@PostMapping(path = "/clients", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Reponse> setClients(@RequestBody ClientCatalog clientCatalog)
			throws InvalidKeyException, IOException, ParseException {
		Reponse resp = new Reponse();
		
		LOGGER.info("Set clients: " + clientCatalog.getNif());
		clientCatalog.setId(clientCatalog.getNif() + "::" + clientCatalog.getOwner());
		
			

		if (clientCatalogRepository.save(clientCatalog) != null) {
			//String result = moqui.saveClient(MoquiMapper.mapClientCatalogs(clientCatalog));
			//LOGGER.info(result);
			
			MoquiClientCatalogModel moquiClientCatalogModel = new MoquiClientCatalogModel();
			
			MoquiClientCatalogModel postdata = MoquiMapper.mapClientCatalogs(clientCatalog);
			moqui.postClient(postdata);
			
			LOGGER.info("Set clients: " + moquiClientCatalogModel.getPartyId());
			
			
			
			return new ResponseEntity<Reponse>(resp, HttpStatus.OK);
		} else
			return new ResponseEntity<Reponse>(resp, HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(path = "/catalogs", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Catalogs> getCatalogs(@RequestParam(name = "owner", required = true) String owner,
			@RequestParam(name = "nif", required = false) String nif)
			throws InvalidKeyException, IOException, ParseException {
		Catalogs resp = new Catalogs();
		LOGGER.info("Get clients: " + nif + " :: user :: " + owner);
		resp.setInvoiceCategoryCatalog(invoiceCategoryCatalogRepository.findByowner(owner));
		resp.setInvoiceTypeCatalog(invoiceTypeCatalogRepository.findByowner(owner));
		resp.setInvoicePaymentTypeCatalog(invoicePaymentTypeCatalogRepository.findByowner(owner));
		List<ProviderCatalog> providers = providerCatalogRepository.findBynif(nif);
		List<ProviderCatalog> providersMatch = new ArrayList<ProviderCatalog>();
		resp.setProviderCatalog(new ArrayList<ProviderCatalog>());
		if (providers.size() > 0)
			for (int i = 0; i < providers.size(); i++)
				if (owner.equals(providers.get(i).getOwner()))
					providersMatch.add(providers.get(i));
		resp.setProviderCatalog(providersMatch);
		resp.setProviderContactCatalog(providerContactCatalogRepository.findByowner(owner));
		resp.setProviderCountryCatalog(providerCountryCatalogRepository.findByowner(owner));
		resp.setProviderGroupCatalog(providerGroupCatalogRepository.findByowner(owner));
		resp.setProviderGroupCatalog(providerGroupCatalogRepository.findByowner(owner));

		return new ResponseEntity<Catalogs>(resp, HttpStatus.OK);
	}

	@PostMapping(path = "/providers", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Reponse> processInvoices(@RequestBody ProviderCatalog provider)
			throws InvalidKeyException, IOException, ParseException {
		Reponse resp = new Reponse();
		LOGGER.info("Set providers: " + provider.getNif() + " :: user :: " + provider.getOwner());
		provider.setId(provider.getNif() + "::" + provider.getOwner());
		if (providerCatalogRepository.save(provider) != null)
			return new ResponseEntity<Reponse>(resp, HttpStatus.OK);
		else
			return new ResponseEntity<Reponse>(resp, HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(path = "/business", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<List<Business>> getBusiness(@RequestParam(name = "owner", required = true) String owner)
			throws InvalidKeyException, IOException, ParseException {
		List<Business> resp = new ArrayList<Business>();
		List<Business> businessData = new ArrayList<Business>();
		List<BusinessCatalog> business = businessCatalogRepository.findByowner(owner);
		if (business.size() > 0) {
			for (BusinessCatalog bc : business) {
				Business b = new Business();
				b.setCif(bc.getCif());
				b.setId(bc.getId());

				List<BusinessInfoCatalog> bic = businessInfoCatalogRepository.findBycif(bc.getCif());
				if (bic.size() > 0) {
					b.setName(bic.get(0).getName());
				} else {
					b.setName("NO REGISTRADO");
					LOGGER.info("Not founded: " + bc.getCif());
				}
				b.setOwner(bc.getOwner());
				businessData.add(b);
			}
			resp = businessData;
		}
		return new ResponseEntity<List<Business>>(resp, HttpStatus.OK);

	}

//........................Moqui..........................................

	@PostMapping(path = "/catalog/category", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<InvoiceCategoryCatalog>> insertInvoiceCategoryCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<InvoiceCategoryCatalog> invoiceCategoryCatalog)
			throws InvalidKeyException, IOException, ParseException {

		System.out.println("Hello");

		LOGGER.info("Start process");

		try {

			if (invoiceCategoryCatalog.size() <= 0) {
				List<InvoiceCategoryCatalog> icc_list = invoiceCategoryCatalogRepository.findByowner("general");
				for (InvoiceCategoryCatalog icc : icc_list) {
					icc.setOwner(business);
					// String postdata = MoquiMapper.mappClientCatalog(icc);

					invoiceCategoryCatalogRepository.save(icc);
					// moquiClient.invoiceCategoryCatalogSave(postdata);

				}

			} else {
				for (InvoiceCategoryCatalog icc : invoiceCategoryCatalog) {

					icc.setOwner(business);
					invoiceCategoryCatalogRepository.save(icc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/type", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<InvoiceTypeCatalog>> insertInvoiceTypeCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<InvoiceTypeCatalog> invoiceTypeCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (invoiceTypeCatalog.size() <= 0) {
				List<InvoiceTypeCatalog> itc_list = invoiceTypeCatalogRepository.findByowner("general");
				for (InvoiceTypeCatalog itc : itc_list) {
					itc.setOwner(business);
					invoiceTypeCatalogRepository.save(itc);
				}

			} else {
				for (InvoiceTypeCatalog itc : invoiceTypeCatalog) {

					itc.setOwner(business);
					invoiceTypeCatalogRepository.save(itc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/paymentType", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<InvoicePaymentTypeCatalog>> insertInvoicePaymentTypeCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<InvoicePaymentTypeCatalog> invoicePaymentTypeCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (invoicePaymentTypeCatalog.size() <= 0) {
				List<InvoicePaymentTypeCatalog> iptc_list = invoicePaymentTypeCatalogRepository.findByowner("general");
				for (InvoicePaymentTypeCatalog iptc : iptc_list) {
					iptc.setOwner(business);
					invoicePaymentTypeCatalogRepository.save(iptc);
				}

			} else {
				for (InvoicePaymentTypeCatalog iptc : invoicePaymentTypeCatalog) {

					iptc.setOwner(business);
					invoicePaymentTypeCatalogRepository.save(iptc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/providerContact", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<ProviderContactCatalog>> insertProviderContactCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<ProviderContactCatalog> providerContactCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (providerContactCatalog.size() <= 0) {
				List<ProviderContactCatalog> pcc_list = providerContactCatalogRepository.findByowner("general");
				for (ProviderContactCatalog pcc : pcc_list) {
					pcc.setOwner(business);
					providerContactCatalogRepository.save(pcc);
				}

			} else {
				for (ProviderContactCatalog pcc : providerContactCatalog) {

					pcc.setOwner(business);
					providerContactCatalogRepository.save(pcc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/providerCountry", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<ProviderCountryCatalog>> insertProviderCountryCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<ProviderCountryCatalog> providerCountryCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (providerCountryCatalog.size() <= 0) {
				List<ProviderCountryCatalog> pcc_list = providerCountryCatalogRepository.findByowner("general");
				for (ProviderCountryCatalog pcc : pcc_list) {
					pcc.setOwner(business);
					providerCountryCatalogRepository.save(pcc);
				}

			} else {
				for (ProviderCountryCatalog pcc : providerCountryCatalog) {

					pcc.setOwner(business);
					providerCountryCatalogRepository.save(pcc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/providerGroup", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<ProviderGroupCatalog>> insertProviderGroupCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<ProviderGroupCatalog> providerGroupCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (providerGroupCatalog.size() <= 0) {
				List<ProviderGroupCatalog> pgc_list = providerGroupCatalogRepository.findByowner("general");
				for (ProviderGroupCatalog pgc : pgc_list) {
					pgc.setOwner(business);
					providerGroupCatalogRepository.save(pgc);
				}

			} else {
				for (ProviderGroupCatalog pgc : providerGroupCatalog) {

					pgc.setOwner(business);
					providerGroupCatalogRepository.save(pgc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/business", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<BusinessCatalog>> insertBusinessCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<BusinessCatalog> businessCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (businessCatalog.size() <= 0) {
				List<BusinessCatalog> bc_list = businessCatalogRepository.findByowner("general");
				for (BusinessCatalog bc : bc_list) {
					bc.setOwner(business);
					businessCatalogRepository.save(bc);
				}

			} else {
				for (BusinessCatalog bc : businessCatalog) {

					bc.setOwner(business);
					businessCatalogRepository.save(bc);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "/catalog/businessInfo", params = "business", consumes = "application/json", produces = "application/json")

	public ResponseEntity<List<BusinessInfoCatalog>> insertBusinessInfoCatalog(
			@RequestParam(required = true, value = "business") String business,
			@RequestBody List<BusinessInfoCatalog> businessInfoCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");
		try {

			if (businessInfoCatalog.size() <= 0) {
				List<BusinessInfoCatalog> bic_list = businessInfoCatalogRepository.findBycif("general");
				for (BusinessInfoCatalog bic : bic_list) {
					bic.setCif(business);
					businessInfoCatalogRepository.save(bic);
				}

			} else {
				for (BusinessInfoCatalog bic : businessInfoCatalog) {

					bic.setCif(business);
					businessInfoCatalogRepository.save(bic);

				}

			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/category", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceCategoryCatalog>> updateInvoiceCategoryCatalog(
			@Validated @RequestBody List<InvoiceCategoryCatalog> invoiceCategoryCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (InvoiceCategoryCatalog icc : invoiceCategoryCatalog) {

				icc.setId(icc.getId());
				icc.setOwner(icc.getOwner());
				icc.setCategory(icc.getCategory());
				invoiceCategoryCatalogRepository.save(icc);

			}
			return ResponseEntity.ok().body(invoiceCategoryCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/type", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceTypeCatalog>> updateInvoiceTypeCatalog(
			@Validated @RequestBody List<InvoiceTypeCatalog> invoiceTypeCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (InvoiceTypeCatalog itc : invoiceTypeCatalog) {

				itc.setId(itc.getId());
				itc.setOwner(itc.getOwner());
				itc.setType(itc.getType());
				invoiceTypeCatalogRepository.save(itc);

			}
			return ResponseEntity.ok().body(invoiceTypeCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/paymentType", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoicePaymentTypeCatalog>> updateInvoicePaymentTypeCatalog(
			@Validated @RequestBody List<InvoicePaymentTypeCatalog> invoicePaymentTypeCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (InvoicePaymentTypeCatalog ipc : invoicePaymentTypeCatalog) {

				ipc.setId(ipc.getId());
				ipc.setOwner(ipc.getOwner());
				ipc.setPaymentType(ipc.getPaymentType());
				invoicePaymentTypeCatalogRepository.save(ipc);

			}
			return ResponseEntity.ok().body(invoicePaymentTypeCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/providerContact", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProviderContactCatalog>> updateProviderContactCatalog(
			@Validated @RequestBody List<ProviderContactCatalog> providerContactCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (ProviderContactCatalog pcc : providerContactCatalog) {

				pcc.setId(pcc.getId());
				pcc.setOwner(pcc.getOwner());
				pcc.setContactType(pcc.getContactType());
				providerContactCatalogRepository.save(pcc);

			}
			return ResponseEntity.ok().body(providerContactCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/providerCountry", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProviderCountryCatalog>> updateProviderCountryCatalog(
			@Validated @RequestBody List<ProviderCountryCatalog> providerCountryCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (ProviderCountryCatalog pcc : providerCountryCatalog) {

				pcc.setId(pcc.getId());
				pcc.setOwner(pcc.getOwner());
				pcc.setCountry(pcc.getCountry());
				providerCountryCatalogRepository.save(pcc);

			}
			return ResponseEntity.ok().body(providerCountryCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/providerGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProviderGroupCatalog>> updateProviderGroupCatalog(
			@Validated @RequestBody List<ProviderGroupCatalog> providerGroupCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (ProviderGroupCatalog pgc : providerGroupCatalog) {

				pgc.setId(pgc.getId());
				pgc.setOwner(pgc.getOwner());
				pgc.setGroupType(pgc.getGroupType());
				providerGroupCatalogRepository.save(pgc);

			}
			return ResponseEntity.ok().body(providerGroupCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/business", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BusinessCatalog>> updateBusinessCatalog(
			@Validated @RequestBody List<BusinessCatalog> businessCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (BusinessCatalog bc : businessCatalog) {

				bc.setId(bc.getId());
				bc.setOwner(bc.getOwner());
				bc.setCif(bc.getCif());
				bc.setRole(bc.getRole());
				businessCatalogRepository.save(bc);

			}
			return ResponseEntity.ok().body(businessCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping(value = "/catalog/businessInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BusinessInfoCatalog>> updateBusinessInfoCatalog(
			@Validated @RequestBody List<BusinessInfoCatalog> businessInfoCatalog)
			throws InvalidKeyException, IOException, ParseException {

		LOGGER.info("Start process");

		try {

			for (BusinessInfoCatalog bic : businessInfoCatalog) {

				bic.setCif(bic.getCif());
				bic.setAddress(bic.getAddress());
				bic.setName(bic.getName());
				businessInfoCatalogRepository.save(bic);

			}
			return ResponseEntity.ok().body(businessInfoCatalog);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/category", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteInvoiceCategoryCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			invoiceCategoryCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/type", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteInvoiceTypeCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			invoiceTypeCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/paymentType", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteInvoicePaymentTypeCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			invoicePaymentTypeCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/providerContact", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteProviderContactCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			providerContactCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/providerCountry", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteProviderCountryCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			providerCountryCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/providerGroup", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteProviderGroupCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			providerGroupCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/business", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteBusinessCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			businessCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@DeleteMapping(value = "/catalog/businessInfo", consumes = "application/json", produces = "application/json")

	@ResponseBody
	public ResponseEntity<InvoiceTypeCatalog> deleteBusinessInfoCatalog()
			throws InvalidKeyException, IOException, ParseException {
		LOGGER.info("Start process");

		try {
			businessInfoCatalogRepository.deleteAll();

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

}
