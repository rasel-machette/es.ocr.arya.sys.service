package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.ProviderContactCatalog;

public interface ProviderContactCatalogRepository extends JpaRepository<ProviderContactCatalog, String> {

	List<ProviderContactCatalog> findByowner(String owner);
	
}
