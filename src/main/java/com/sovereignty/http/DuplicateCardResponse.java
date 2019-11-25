package com.sovereignty.http;

import com.sovereignty.model.Card;

public class DuplicateCardResponse {
	Card card;
	int code;
	String error;
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public DuplicateCardResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public DuplicateCardResponse(int code, String errorMessage, Card card) {
		this.code = code;
		this.error = errorMessage;
		this.card = card;
	}

}
