package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.BusinessSublevelsCatalog;

public interface BusinessSublevelsCatalogRepository extends JpaRepository<BusinessSublevelsCatalog, String> {

	List<BusinessSublevelsCatalog> findBymainCif(String mainCif);

	
}
