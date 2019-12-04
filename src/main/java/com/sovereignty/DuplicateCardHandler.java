package com.sovereignty;

import java.util.Iterator;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.db.PageDAO;
import com.sovereignty.http.DuplicateCardRequest;
import com.sovereignty.http.DuplicateCardResponse;
import com.sovereignty.model.Card;
import com.sovereignty.model.Page;
import com.sovereignty.model.VisualElement;

public class DuplicateCardHandler implements RequestHandler<DuplicateCardRequest, DuplicateCardResponse>{
	
	CardDAO cardDao = new CardDAO();
	PageDAO pageDao = new PageDAO();
	
	// Includes new IDs for each page and visual element
	private Card mapDuplicateCardRequestToCard(Card input)  {
		input.setCardID(UUID.randomUUID().toString().substring(0, 20));
		
		String frontPageID = UUID.randomUUID().toString().substring(0, 20);
		input.getFrontPage().setPageID(frontPageID);
		Iterator<VisualElement> frontPageIterator = input.getFrontPage().getListVisualElements().iterator();
		while (frontPageIterator.hasNext()) {
			VisualElement velt = frontPageIterator.next();
			velt.setEltID(UUID.randomUUID().toString().substring(0, 20));
			velt.setPageID(frontPageID);
		}
		
		String leftPageID = UUID.randomUUID().toString().substring(0, 20);
		input.getLeftPage().setPageID(leftPageID);
		Iterator<VisualElement> leftPageIterator = input.getLeftPage().getListVisualElements().iterator();
		while (leftPageIterator.hasNext()) {
			VisualElement velt = leftPageIterator.next();
			velt.setEltID(UUID.randomUUID().toString().substring(0, 20));
			velt.setPageID(leftPageID);
		}
		
		String rightPageID = UUID.randomUUID().toString().substring(0, 20);
		input.getRightPage().setPageID(rightPageID);
		Iterator<VisualElement> rightPageIterator = input.getRightPage().getListVisualElements().iterator();
		while (rightPageIterator.hasNext()) {
			VisualElement velt = rightPageIterator.next();
			velt.setEltID(UUID.randomUUID().toString().substring(0, 20));
			velt.setPageID(rightPageID);
		}
		
		return input;
	}
	
	private String validateDuplicateCardRequest(DuplicateCardRequest input) {
		if ( input.getCardID() == null || input.getCardID().isEmpty()) 
			return "cardID required";
		if ( input.getRecipient() == null || input.getRecipient().isEmpty()) 
			return "recipient name required";
		if ( input.getEventType() == null || input.getEventType().isEmpty()) 
			return "event type required";
		return null;
	}
	
    @Override
    public DuplicateCardResponse handleRequest(DuplicateCardRequest input, Context context) {
        context.getLogger().log("InputDuplicateCard: " + input);
		
        try {
        	String validationError = this.validateDuplicateCardRequest(input);
        	if (validationError != null)  {
        		return new DuplicateCardResponse(400, validationError);
        	}
	        
        	// See if a card already exists for that recipient and event type
	        Card check_existing_card = cardDao.getCardByRecipientAndEventType(input.getRecipient(), input.getEventType());
			if (check_existing_card != null) {
				return new DuplicateCardResponse(409, "conflict, card already exists");
			}
			
			// Do The stuff
	        Card card = cardDao.getCardByID(input.getCardID());

			card = this.mapDuplicateCardRequestToCard(card);
			// Reset the recipient and event
			card.setRecipient(input.getRecipient());
			card.setEventType(input.getEventType());
		
			boolean cardAdded = cardDao.saveCard(card);
			
			if (! cardAdded) {
				return new DuplicateCardResponse(500, "failed adding card");
			}
			
			return new DuplicateCardResponse(200, "successfully added new card", card);
        } catch (Exception e) {
        	return new DuplicateCardResponse(500, "Encountered an error while adding card");
		}
    }

}
