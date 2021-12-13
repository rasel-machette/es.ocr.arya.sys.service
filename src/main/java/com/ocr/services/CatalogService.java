package com.ocr.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.entities.Catalog;
import com.ocr.repositories.CatalogRepository;


@Service
public class CatalogService implements ICatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

	@Override
	public Optional<Catalog> findBytag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
