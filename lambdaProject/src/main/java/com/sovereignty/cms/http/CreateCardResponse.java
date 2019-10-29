package com.sovereignty.cms.http;

import com.sovereignty.cms.model.Card;

public class CreateCardResponse {
	String result;
	int code;
	public String error;
	Card card;
	
	public int getCode() {
		return code;
	}
	public void setCode(Integer i) {
		this.code = i;
	}
	public String getContent() {
		return error;
	}
	public void setContent(String content) {
		this.error = content;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
	public CreateCardResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public CreateCardResponse(int code, String errorMessage, Card card) {
		this.code = code;
		this.error = errorMessage;
		this.card = card;
	}
	
	
}

