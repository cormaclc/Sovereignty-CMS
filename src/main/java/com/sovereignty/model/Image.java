package com.sovereignty.model;

public class Image {

	String imageID;
	String imageName;
	String imageURL;
	
	public Image(String imageID, String imageName, String imageURL) {
		this.imageID = imageID;
		this.imageName = imageName;
		this.imageURL = imageURL;
	}

	public String getImageID() {
		return imageID;
	}

	public void setImageID(String imageID) {
		this.imageID = imageID;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
