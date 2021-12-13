package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.ClientCatalog;
import com.ocr.entities.ProviderCatalog;

public interface ClientCatalogRepository extends JpaRepository<ClientCatalog, String> {

	List<ClientCatalog> findByowner(String owner);

	List<ClientCatalog> findBynif(String nif);
	
}
