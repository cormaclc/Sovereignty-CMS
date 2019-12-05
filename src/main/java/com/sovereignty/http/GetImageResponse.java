package com.sovereignty.http;

import com.sovereignty.model.Image;

public class GetImageResponse {
	Image image;
	int code;
	String error;
	String imageURL;
	
	public GetImageResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public GetImageResponse(int code, String errorMessage, Image img) {
		this.code = code;
		this.error = errorMessage;
		this.image = img;
	}
	
	public GetImageResponse(int code, String errorMessage, String imageUrl) {
		this.code = code;
		this.error = errorMessage;
		this.imageURL = imageUrl;
	}
}
