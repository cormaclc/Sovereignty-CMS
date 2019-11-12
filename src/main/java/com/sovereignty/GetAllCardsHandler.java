package com.sovereignty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.AllCardsResponse;
import com.sovereignty.model.Card;


  public class GetAllCardsHandler implements RequestHandler<Object, AllCardsResponse> {
  
	  public AllCardsResponse handleRequest(Object input, Context context) {
		  context.getLogger().log("Input: "+ input); 
		  CardDAO cardsDAO = new CardDAO();
	  
	  try { 
		  List<Card> allCards = cardsDAO.getAllCards(); 
		  return new  AllCardsResponse(200, "successful operation", allCards); 
		  } catch (Exception  e) { 
			  return new AllCardsResponse(500, e.getMessage()); }
	  }
	  
  }
