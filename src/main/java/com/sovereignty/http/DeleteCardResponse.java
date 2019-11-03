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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
