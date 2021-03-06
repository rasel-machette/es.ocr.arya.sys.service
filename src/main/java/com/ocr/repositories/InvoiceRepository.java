package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.Callback;
import com.ocr.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

	List<Invoice> findBybusinessOwner(String businessOwner);
	
	List<Invoice> findByfileName(String fileName);

	List<Invoice> findByowner(String owner);
	
}
