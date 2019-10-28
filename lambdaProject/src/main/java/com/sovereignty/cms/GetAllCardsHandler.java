package com.sovereignty.cms;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.cms.db.CardDAO;
import com.sovereignty.cms.http.AllCardsResponse;
import com.sovereignty.cms.model.Card;

public class GetAllCardsHandler implements RequestHandler<Object, AllCardsResponse> {
	    
    public AllCardsResponse handleRequest(Object input, Context context) {
    	context.getLogger().log("Input: "+ input);
		CardDAO cardsDAO = new CardDAO();
		
		try {
			List<Card> allCards = cardsDAO.getAllCards();
			return new AllCardsResponse(200, "successful operation", allCards);
		} catch (Exception e) {
			// TODO: handle exception
			return new AllCardsResponse(500, e.getMessage());
		}

    }

}
