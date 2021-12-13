package com.ocr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocr.entities.Callback;
import com.ocr.entities.File;
import com.ocr.entities.Invoice;

public interface FileRepository extends JpaRepository<File, String> {

	List<File> findByfileName(String fileName);
	
}
