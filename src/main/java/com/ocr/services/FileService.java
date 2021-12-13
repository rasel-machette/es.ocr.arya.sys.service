package com.ocr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.entities.File;
import com.ocr.entities.Invoice;
import com.ocr.repositories.FileRepository;
import com.ocr.repositories.InvoiceRepository;


@Service
public class FileService implements IFileService {

    @Autowired
    private FileRepository fileRepository;

	@Override
	public Optional<File> findByfileName(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

    
}
