package com.sovereignty.http;

public class UploadImageRequest {
	String imageName;
	String image64;
	String eltID;
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImage64() {
		return image64;
	}
	public void setImage64(String image64) {
		this.image64 = image64;
	}
	public String getEltID() {
		return eltID;
	}
	public void setEltID(String eltID) {
		this.eltID = eltID;
	}
}
