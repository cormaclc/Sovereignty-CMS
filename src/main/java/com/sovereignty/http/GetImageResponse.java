package com.sovereignty.http;

import com.sovereignty.model.VisualElement;

public class GetImageResponse {
	VisualElement image;
	int code;
	String error;
	
	public GetImageResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public GetImageResponse(int code, String errorMessage, VisualElement img) {
		this.code = code;
		this.error = errorMessage;
		this.image = img;
	}
}
