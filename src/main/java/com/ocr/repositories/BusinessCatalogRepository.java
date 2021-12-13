package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.BusinessCatalog;
import com.ocr.entities.ClientCatalog;

public interface BusinessCatalogRepository extends JpaRepository<BusinessCatalog, String> {

	List<BusinessCatalog> findByowner(String owner);
	
	List<BusinessCatalog> findBycif(String cif);
	
}
