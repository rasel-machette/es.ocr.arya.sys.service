package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.ProviderGroupCatalog;

public interface ProviderGroupCatalogRepository extends JpaRepository<ProviderGroupCatalog, String> {

	List<ProviderGroupCatalog> findByowner(String owner);
	
}
