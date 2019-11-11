package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.DeleteCardResponse;
import com.sovereignty.http.GetCardRequest;
import com.sovereignty.http.GetCardResponse;
import com.sovereignty.model.Card;

public class GetCardHandler implements RequestHandler<GetCardRequest, GetCardResponse> {
	CardDAO cardDao = new CardDAO();
	
	public String validateGetCardRequest(GetCardRequest input) {
		if (input.getCardID() == null || input.getCardID().isEmpty()) {
			return "cardID is required";
		}
		return null;
	}
	
	@Override
	public GetCardResponse handleRequest(GetCardRequest input, Context context) {
        context.getLogger().log("Input: " + input);
        Card c ;
        try {
	        String validationError = this.validateGetCardRequest(input);
			if (validationError != null) {
	    		return new GetCardResponse(400, validationError);
	    	}
			
			c = cardDao.getCardByID(input.getCardID());
			return new GetCardResponse(200, "successfully got card", c);
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			return new GetCardResponse(500, e.getMessage());
		}		
	}

}
