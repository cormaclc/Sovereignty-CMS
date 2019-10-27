package com.sovereignty.cms.http;

public class CreateCardRequest {
	String uuid;
	String recipient;
	String eventType;
	String orientation;
	
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
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	
}
