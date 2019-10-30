package com.sovereignty.db;


import java.util.UUID;

import com.sovereignty.model.Card;

import junit.framework.TestCase;


public class TestDatabase extends TestCase {

	public void testFind() {
	    CardDAO cd = new CardDAO();
	    try {
	    	Card c = cd.getCardByRecipientAndEventType("Andrew", "ANNIVERSARY");
	    	System.out.println("constant " + c.getEvent() + " = " + c.getRecipient());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    	
	    }
	}
	
	public void testCreate() {
	    CardDAO cd = new CardDAO();
	    try {
	    	// can add it
	    	String id = UUID.randomUUID().toString().substring(0, 20); // no more than 20 because of DB restrictions...
	    	System.out.println(id);
	    	Card card = new Card("test_id", "BACK_TO_SCHOOL", "Test Recipient","PORTRAIT");
	    	boolean b = cd.addCard(card);
	    	System.out.println("add constant: " + b);
	    	
	    	// can retrieve it
	    	Card c2 = cd.getCardByID(card.getCardID());
	    	System.out.println("C2:" + c2.getCardID());
	    	
	    	// can delete it
	    	assertTrue (cd.deleteCard(c2.getCardID()));
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
