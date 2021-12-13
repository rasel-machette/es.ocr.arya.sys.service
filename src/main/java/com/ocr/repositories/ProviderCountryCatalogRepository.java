package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.ProviderCountryCatalog;

public interface ProviderCountryCatalogRepository extends JpaRepository<ProviderCountryCatalog, String> {

	List<ProviderCountryCatalog> findByowner(String owner);
	
}
