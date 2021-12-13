package com.ocr.services;

import java.util.Optional;

import com.ocr.entities.File;

public interface IFileService {

	Optional<File> findByfileName(String fileName);
}
