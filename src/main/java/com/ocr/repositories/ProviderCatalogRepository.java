package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.ProviderCatalog;

public interface ProviderCatalogRepository extends JpaRepository<ProviderCatalog, String> {

	List<ProviderCatalog> findBynif(String nif);

	List<ProviderCatalog> findByowner(String owner);
	
}
