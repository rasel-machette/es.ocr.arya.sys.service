package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.BusinessInfoCatalog;

public interface BusinessInfoCatalogRepository extends JpaRepository<BusinessInfoCatalog, String> {

	List<BusinessInfoCatalog> findBycif(String cif);
	
}
