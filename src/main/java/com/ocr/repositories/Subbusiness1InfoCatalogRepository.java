package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.Subbusiness1InfoCatalog;

public interface Subbusiness1InfoCatalogRepository extends JpaRepository<Subbusiness1InfoCatalog, String> {

	List<Subbusiness1InfoCatalog> findByid(String id);
	
}
