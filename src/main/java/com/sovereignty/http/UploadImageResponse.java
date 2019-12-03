package com.sovereignty.http;

public class UploadImageResponse {
	int code;
	String error;
	String imageURL;
	
	public UploadImageResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public UploadImageResponse(int code, String errorMessage, String url) {
		this.code = code;
		this.error = errorMessage;
		this.imageURL = url;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return error;
	}

	public void setErrorMessage(String errorMessage) {
		this.error = errorMessage;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}
