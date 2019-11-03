package com.sovereignty.model;

public class Card {
	
	String cardID;
	String recipient;
	String eventType;
	String orientation;
	
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
}
