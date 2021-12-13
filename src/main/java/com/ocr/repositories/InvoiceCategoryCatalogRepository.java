package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.InvoiceCategoryCatalog;

public interface InvoiceCategoryCatalogRepository extends JpaRepository<InvoiceCategoryCatalog, String> {

	List<InvoiceCategoryCatalog> findByowner(String owner);
	
}
