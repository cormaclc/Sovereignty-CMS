package com.sovereignty.http;

import java.util.List;
import com.sovereignty.model.Image;

public class AllImagesResponse {
	List<Image> images;
	int code;
	String error;
	
	public AllImagesResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public AllImagesResponse(int code, String errorMessage, List<Image> images) {
		this.code = code;
		this.error = errorMessage;
		this.images = images;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
