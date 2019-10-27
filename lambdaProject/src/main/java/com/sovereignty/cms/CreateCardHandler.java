package com.sovereignty.cms;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.cms.db.CardDAO;
import com.sovereignty.cms.http.CreateCardRequest;
import com.sovereignty.cms.http.CreateCardResponse;
import com.sovereignty.cms.model.Card;

public class CreateCardHandler implements RequestHandler<CreateCardRequest, CreateCardResponse> {
	
	private Card mapCreateCardRequestToCard(CreateCardRequest input)  {
		Card card = new  Card();
		card.setCardID(input.getUuid());
		card.setRecipient(input.getRecipient());
		card.setEvent(input.getEventType());
		card.setOrientation(input.getOrientation());
		return card;
	}
	
	private String validateCreateCardRequest(CreateCardRequest input) {
		if ( input.getUuid() == null || input.getUuid().isEmpty()) 
			return "uuid required";
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
		CreateCardResponse createCardRes;
		CardDAO cardDao = new CardDAO();
		
        try {
        	String validationError = this.validateCreateCardRequest(input);
        	if (validationError != null)  {
        		createCardRes = new CreateCardResponse(400, "invalid card");
 		        return createCardRes;
        	}
	        Card card = cardDao.getCardByRecipientAndEventType(input.getRecipient(), input.getEventType());
			if (card != null) {
				createCardRes = new CreateCardResponse(409, "conflict, card already exists");
		        return createCardRes;
			}
			
			card = this.mapCreateCardRequestToCard(input);
			boolean cardAdded = cardDao.addCard(card);
			
			if (! cardAdded) {
        		createCardRes = new CreateCardResponse(500, "failed adding card");
		        return createCardRes;
			}
			
    		createCardRes = new CreateCardResponse(200, "successfully added new card", card);
	        return createCardRes;
        } catch (Exception e) {
        	createCardRes = new CreateCardResponse(500, "failed adding card");
	        return createCardRes;
		}
    }

}
