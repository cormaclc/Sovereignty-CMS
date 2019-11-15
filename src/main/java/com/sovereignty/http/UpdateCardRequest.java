package com.sovereignty.http;

import com.sovereignty.model.Page;

public class UpdateCardRequest {
	String cardID;
	String recipient;
	String eventType;
	String orientation;
	Page frontPage, leftPage, rightPage, backPage;
	
	public String getCardID() {	return cardID; }
	public void setCardID(String cardID) { this.cardID = cardID; }
	
	public String getRecipient() { return recipient; }
	public void setRecipient(String recipient) { this.recipient = recipient; }
	
	public String getEventType() { return eventType; }
	public void setEventType(String eventType) { this.eventType = eventType; }
	
	public String getOrientation() { return orientation; }
	public void setOrientation(String orientation) { this.orientation = orientation; }
	
	public Page getFrontPage() { return frontPage; }
	public void setFrontPage(Page frontPage) { this.frontPage = frontPage; }
	
	public Page getLeftPage() { return leftPage; }
	public void setLeftPage(Page leftPage) { this.leftPage = leftPage; }
	
	public Page getRightPage() { return rightPage; }
	public void setRightPage(Page rightPage) { this.rightPage = rightPage; }
	
	public Page getBackPage() { return backPage; }
	public void setBackPage(Page backPage) { this.backPage = backPage; }
	
}
