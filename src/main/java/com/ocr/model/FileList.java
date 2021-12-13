package com.ocr.model;

import java.util.ArrayList;
import java.util.List;

public class FileList {

	private List<Archive> files;
    private String classification;
	public List<Archive> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<Archive> files) {
		this.files = files;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
}
