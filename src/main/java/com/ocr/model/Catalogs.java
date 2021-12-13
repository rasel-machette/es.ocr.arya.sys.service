package com.ocr.model;

import java.util.List;

import com.ocr.entities.BusinessSublevelsCatalog;
import com.ocr.entities.ClientCatalog;
import com.ocr.entities.InvoiceCategoryCatalog;
import com.ocr.entities.InvoicePaymentTypeCatalog;
import com.ocr.entities.InvoiceTypeCatalog;
import com.ocr.entities.ProviderCatalog;
import com.ocr.entities.ProviderContactCatalog;
import com.ocr.entities.ProviderCountryCatalog;
import com.ocr.entities.ProviderGroupCatalog;

public class Catalogs {

	private List<InvoiceCategoryCatalog> invoiceCategoryCatalog;
	private List<InvoiceTypeCatalog> invoiceTypeCatalog;
	private List<ClientCatalog> clientCatalog;
	private List<ProviderCatalog> providerCatalog;
	private List<ProviderContactCatalog> providerContactCatalog;
	private List<ProviderCountryCatalog> providerCountryCatalog ;
	private List<ProviderGroupCatalog> providerGroupCatalog;
	private List<InvoicePaymentTypeCatalog> invoicePaymentTypeCatalog;
	private List<BusinessSublevelsCatalog> businessSublevelsCatalog;

	public List<InvoiceCategoryCatalog> getInvoiceCategoryCatalog() {
		return invoiceCategoryCatalog;
	}
	public void setInvoiceCategoryCatalog(List<InvoiceCategoryCatalog> invoiceCategoryCatalog) {
		this.invoiceCategoryCatalog = invoiceCategoryCatalog;
	}
	public List<InvoiceTypeCatalog> getInvoiceTypeCatalog() {
		return invoiceTypeCatalog;
	}
	public void setInvoiceTypeCatalog(List<InvoiceTypeCatalog> invoiceTypeCatalog) {
		this.invoiceTypeCatalog = invoiceTypeCatalog;
	}
	public List<ProviderCatalog> getProviderCatalog() {
		return providerCatalog;
	}
	public void setProviderCatalog(List<ProviderCatalog> providerCatalog) {
		this.providerCatalog = providerCatalog;
	}
	public List<ProviderContactCatalog> getProviderContactCatalog() {
		return providerContactCatalog;
	}
	public void setProviderContactCatalog(List<ProviderContactCatalog> providerContactCatalog) {
		this.providerContactCatalog = providerContactCatalog;
	}
	public List<ProviderCountryCatalog> getProviderCountryCatalog() {
		return providerCountryCatalog;
	}
	public void setProviderCountryCatalog(List<ProviderCountryCatalog> providerCountryCatalog) {
		this.providerCountryCatalog = providerCountryCatalog;
	}
	public List<ProviderGroupCatalog> getProviderGroupCatalog() {
		return providerGroupCatalog;
	}
	public void setProviderGroupCatalog(List<ProviderGroupCatalog> providerGroupCatalog) {
		this.providerGroupCatalog = providerGroupCatalog;
	}
	public List<InvoicePaymentTypeCatalog> getInvoicePaymentTypeCatalog() {
		return invoicePaymentTypeCatalog;
	}
	public void setInvoicePaymentTypeCatalog(List<InvoicePaymentTypeCatalog> invoicePaymentTypeCatalog) {
		this.invoicePaymentTypeCatalog = invoicePaymentTypeCatalog;
	}
	public List<BusinessSublevelsCatalog> getBusinessSublevelsCatalog() {
		return businessSublevelsCatalog;
	}
	public void setBusinessSublevelsCatalog(List<BusinessSublevelsCatalog> businessSublevelsCatalog) {
		this.businessSublevelsCatalog = businessSublevelsCatalog;
	}
	public List<ClientCatalog> getClientCatalog() {
		return clientCatalog;
	}
	public void setClientCatalog(List<ClientCatalog> clientCatalog) {
		this.clientCatalog = clientCatalog;
	}
}
