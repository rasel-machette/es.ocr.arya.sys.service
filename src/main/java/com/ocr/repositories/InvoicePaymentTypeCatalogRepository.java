package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.InvoicePaymentTypeCatalog;
import com.ocr.entities.ProviderCatalog;

public interface InvoicePaymentTypeCatalogRepository extends JpaRepository<InvoicePaymentTypeCatalog, String> {

	List<InvoicePaymentTypeCatalog> findByowner(String owner);
	
}
