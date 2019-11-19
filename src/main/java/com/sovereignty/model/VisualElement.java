package com.sovereignty.model;

public class VisualElement {
	String eltID;
	int updated;
	String eltType;
	int xPosition, yPosition, height, width;
	String text, font;
	String imageURL;
	String pageID;
	
	public VisualElement() {} // constructor for text
	
	public VisualElement(String image) {} // constructor for Image
	
	public VisualElement(String eltID, String eltType, int xPos, int yPos, int height, int width, String text, String font, String imageURL, String pageID, int updated) {
		this.eltID = eltID;
		this.eltType = eltType;
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.height = height;
		this.width = width;
		this.text = text;
		this.font = font; 
		this.imageURL = imageURL;
		this.pageID = pageID;
		this.updated = updated;
	}
	
	public String getEltID() {
		return eltID;
	}
	public void setEltID(String eltID) {
		this.eltID = eltID;
	}
	public int getUpdated() {
		return updated;
	}
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	public String getEltType() {
		return eltType;
	}
	public void setEltType(String elementType) {
		this.eltType = elementType;
	}
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPos) {
		this.xPosition = xPos;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPos) {
		this.yPosition = yPos;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageUrl) {
		this.imageURL = imageUrl;
	}
	
	public String getPageID() {
		return pageID;
	}
	public void setPageID(String pageID) {
		this.pageID = pageID;
	}
	
}
