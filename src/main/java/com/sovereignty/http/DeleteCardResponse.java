package com.sovereignty.http;


public class DeleteCardResponse {
	int code;
	String error;
	
	public DeleteCardResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public int getCode() {
		return code;
	}

}
