package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.InvoiceTypeCatalog;

public interface InvoiceTypeCatalogRepository extends JpaRepository<InvoiceTypeCatalog, String> {

	List<InvoiceTypeCatalog> findByowner(String owner);
	
}
