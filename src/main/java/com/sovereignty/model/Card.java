package com.sovereignty.model;

public class Card {
	
	String cardID;
	String recipient;
	String eventType;
	String orientation;
	
	Page frontPage;
	Page leftPage;
	Page rightPage;
	Page backPage;
	
	public Card()  {}
	
	public Card(String cardID, String recipientName, String eventType, String orientation) {
		this.cardID = cardID;
		this.recipient = recipientName;
		this.eventType = eventType;
		this.orientation = orientation;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String uuid) {
		this.cardID = uuid;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String event) {
		this.eventType = event;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public Page getFrontPage() {
		return frontPage;
	}

	public void setFrontPage(Page frontPage) {
		this.frontPage = frontPage;
	}

	public Page getLeftPage() {
		return leftPage;
	}

	public void setLeftPage(Page leftPage) {
		this.leftPage = leftPage;
	}

	public Page getRightPage() {
		return rightPage;
	}

	public void setRightPage(Page rightPage) {
		this.rightPage = rightPage;
	}

	public Page getBackPage() {
		return backPage;
	}

	public void setBackPage(Page backPage) {
		this.backPage = backPage;
	}

	
	
	
}
