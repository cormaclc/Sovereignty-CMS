package com.sovereignty.http;

import com.sovereignty.model.Card;

public class UpdateCardResponse {
	Card card;
	int code;
	String error;
	
	public UpdateCardResponse(int code, String errorMessage){
			
		}
	public UpdateCardResponse(int code, String errorMessage, Card card){
			
		}
	public Card getCard() {
		return card;
	}

	public int getCode() {
		return code;
	}
}
