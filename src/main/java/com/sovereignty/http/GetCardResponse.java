package com.sovereignty.http;

import com.sovereignty.model.Card;

public class GetCardResponse {
	Card card;
	int code;
	String error;
	
	public GetCardResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public GetCardResponse(int code, String errorMessage, Card card) {
		this.code = code;
		this.error = errorMessage;
		this.card = card;
	}
}
