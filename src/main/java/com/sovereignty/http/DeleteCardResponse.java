package com.sovereignty.http;

import com.sovereignty.model.Card;

public class DeleteCardResponse {
	int code;
	String error;
	Card card;
	
	public DeleteCardResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public DeleteCardResponse(int code, String errorMessage, Card card) {
		this.code = code;
		this.error = errorMessage;
		this.card = card;
	}
}
