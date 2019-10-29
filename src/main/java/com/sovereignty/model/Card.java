package com.sovereignty.model;

public class Card {
	String cardID;
	String recipient;
	String event;
	String orientation;
// TODO: card model in database 
	public Card()  {}
	public Card(String cardID, String recipientName, String eventType, String orientation) {
		this.cardID = cardID;
		this.recipient = recipientName;
		this.event = eventType;
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
}
