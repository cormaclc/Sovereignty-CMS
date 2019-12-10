package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;

import com.sovereignty.db.PageDAO;
import com.sovereignty.http.CreateCardRequest;
import com.sovereignty.http.CreateCardResponse;
import com.sovereignty.http.GetCardResponse;
import com.sovereignty.http.UpdateCardRequest;
import com.sovereignty.http.UpdateCardResponse;
import com.sovereignty.model.Card;

public class UpdateCardHandler implements RequestHandler<UpdateCardRequest, UpdateCardResponse> {
	CardDAO cardDAO = new CardDAO();
	PageDAO pageDAO = new PageDAO();

	private Card mapUpdateCardRequestToCard(UpdateCardRequest input)  {
		Card card = new  Card();
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

	private String validateUpdateCardRequest(UpdateCardRequest input) {
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
        context.getLogger().log("Input updateCard: " + input);
        
        try {
        	String validationError = this.validateUpdateCardRequest(input);
        	if (validationError != null)  {
        		return new UpdateCardResponse(400, validationError);
        	}
        	
        	Card card = this.mapUpdateCardRequestToCard(input);
        	cardDAO.updateCard(card);
        	return new UpdateCardResponse(200, "Successfully updated card", card);
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			e.printStackTrace();
			return new UpdateCardResponse(500, e.getMessage());

		}
    }

}
