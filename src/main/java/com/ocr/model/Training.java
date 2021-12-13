package com.ocr.model;

public class Training {

	private String file;
	private String filename;
	private PredictInvoice correct_data;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public PredictInvoice getCorrect_data() {
		return correct_data;
	}
	public void setCorrect_data(PredictInvoice correct_data) {
		this.correct_data = correct_data;
	}

}
