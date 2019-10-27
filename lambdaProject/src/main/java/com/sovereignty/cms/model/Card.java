package com.sovereignty.cms.model;

public class Card {
	String uuid;
	String recipient;
	String event;
	String orientation;
// TODO: card model in database 
	public Card()  {}
	public Card(String uuid, String recipientName, String eventType, String orientation) {
		this.recipient = recipientName;
		this.event = eventType;
		this.orientation = orientation;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
