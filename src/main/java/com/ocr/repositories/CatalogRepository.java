package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.Catalog;
import com.ocr.entities.Classification;

public interface CatalogRepository extends JpaRepository<Catalog, String> {

	List<Catalog> findBytag(String tag);
	
}
