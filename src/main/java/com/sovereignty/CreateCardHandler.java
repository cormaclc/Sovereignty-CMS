package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.CreateCardRequest;
import com.sovereignty.http.CreateCardResponse;
import com.sovereignty.model.Card;

public class CreateCardHandler implements RequestHandler<CreateCardRequest, CreateCardResponse> {
	CardDAO cardDao = new CardDAO();
	private Card mapCreateCardRequestToCard(CreateCardRequest input)  {
		Card card = new  Card();
		card.setCardID(input.getCardID());
		card.setRecipient(input.getRecipient());
		card.setEvent(input.getEventType());
		card.setOrientation(input.getOrientation());
		return card;
	}
	
	private String validateCreateCardRequest(CreateCardRequest input) {
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
    public CreateCardResponse handleRequest(CreateCardRequest input, Context context) {
        context.getLogger().log("Input: " + input);
		
        try {
        	String validationError = this.validateCreateCardRequest(input);
        	if (validationError != null)  {
        		return new CreateCardResponse(400, "invalid card");
        	}
	        Card card = cardDao.getCardByRecipientAndEventType(input.getRecipient(), input.getEventType());
			if (card != null) {
				return new CreateCardResponse(409, "conflict, card already exists");
			}
			
			card = this.mapCreateCardRequestToCard(input);
		
			boolean cardAdded = cardDao.addCard(card);
			
			if (! cardAdded) {
				return new CreateCardResponse(500, "failed adding card");
			}
			
			return new CreateCardResponse(200, "successfully added new card", card);
        } catch (Exception e) {
        	return new CreateCardResponse(500, "Encountered and Error while adding card");
		}
    }

}
