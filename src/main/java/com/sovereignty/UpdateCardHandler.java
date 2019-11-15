package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.CreateCardRequest;
import com.sovereignty.http.UpdateCardRequest;
import com.sovereignty.http.UpdateCardResponse;
import com.sovereignty.model.Card;

public class UpdateCardHandler implements RequestHandler<UpdateCardRequest, UpdateCardResponse> {
	CardDAO cardDao = new CardDAO();
	private Card mapUpdateCardRequestToCard(UpdateCardRequest input) {
		Card card = new Card();
		card.setCardID(input.getCardID());
		card.setRecipient(input.getRecipient());
		card.setEventType(input.getEventType());
		card.setOrientation(input.getOrientation());
		card.setFrontPage(input.getFrontPage());
		card.setLeftPage(input.getLeftPage());
		card.setRightPage(input.getRightPage());
		card.setBackPage(input.getBackPage());
		return card;
	}

	private String validateCreateCardRequest(UpdateCardRequest input) {
		if ( input.getCardID() == null || input.getCardID().isEmpty()) 
			return "cardID required";
		if ( input.getRecipient() == null || input.getRecipient().isEmpty()) 
			return "recipient name required";
		if ( input.getEventType() == null || input.getEventType().isEmpty()) 
			return "event type required";
		if ( input.getOrientation() == null || input.getOrientation().isEmpty()) 
			return "orientation required";
		return null;
	}
	
	@Override
	public UpdateCardResponse handleRequest(UpdateCardRequest input, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
