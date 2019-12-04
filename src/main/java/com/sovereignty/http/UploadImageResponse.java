package com.sovereignty.http;

public class UploadImageResponse {
	int code;
	String errorMessage;
	
	public UploadImageResponse(int code, String errorMessage) {
		this.code = code;
		this.errorMessage = errorMessage;
	}
}
