package model;

import java.util.ArrayList;

public class Card {

	public enum EventType {
		ANNIVERSARY, BACK_TO_SCHOOL, BAPTISM, CHRISTENING, BABY, BAR_BAT_MITZVAH, BIRTHDAY,
		CONFIRMATION, CONGRATULATIONS, ENCOURAGEMENT, FIRST_COMMUNION, GET_WELL, GRADUATION,
		RETIREMENT, SYMPATHY, TEACHER_APPRECIATION, THANK_YOU, WEDDING, RESOURCES
	}
	
	public enum Orientation {
		PORTRAIT, LANDSCAPE
	}
	
	// Card UUID
	String cardID;
	
	// Card Contents
	EventType eventType;
	Orientation orientation;
	String recipient;
	String description; 
	
	// Pages
	Page frontPage;
	Page leftPage;
	Page rightPage;
	Page backPage;
	
	public Card(String cardID, EventType event, String recipient, Orientation orientation) {
		this.cardID = cardID;
		this.eventType = event;
		this.recipient = recipient;
		this.orientation = orientation;
		// Default Pages for Now
		this.frontPage = new Page(new ArrayList<VisualElement>(), true);
		this.leftPage = new Page(new ArrayList<VisualElement>(), true);
		this.rightPage = new Page(new ArrayList<VisualElement>(), true);
		this.backPage = new Page(new ArrayList<VisualElement>(), false);

	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
