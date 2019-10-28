package com.sovereignty.cms.http;

import java.util.List;
import com.sovereignty.cms.model.Card;

public class AllCardsResponse {
	List<Card> cards;
	int code;
	String error;
	
	public AllCardsResponse(int code, String errorMessage) {
		this.code = code;
		this.error = errorMessage;
	}
	
	public AllCardsResponse(int code, String errorMessage, List<Card> cards) {
		this.code = code;
		this.error = errorMessage;
		this.cards = cards;
	}
}
