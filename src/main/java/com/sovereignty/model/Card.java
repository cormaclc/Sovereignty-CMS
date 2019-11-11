package com.sovereignty.model;

public class Card {
	
	String cardID;
	String recipient;
	String eventType;
	String orientation;
	Page frontPage, leftPage, rightPage, backPage;
	
	public Card()  {}
	
	public Card(String cardID, String recipientName, String eventType, String orientation,
			Page front, Page left, Page right, Page back) {
		this.cardID = cardID;
		this.recipient = recipientName;
		this.eventType = eventType;
		this.orientation = orientation;
		this.frontPage = front;
		this.leftPage = left; 
		this.rightPage = right;
		this.backPage = back;
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
}
