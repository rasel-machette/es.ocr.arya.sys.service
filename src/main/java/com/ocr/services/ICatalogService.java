package com.ocr.services;

import java.util.Optional;

import com.ocr.entities.Catalog;

public interface ICatalogService {

	Optional<Catalog> findBytag(String tag);
}
