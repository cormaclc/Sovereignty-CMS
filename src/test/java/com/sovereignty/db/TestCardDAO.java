package com.sovereignty.db;


import java.util.UUID;

import com.sovereignty.model.Card;
import com.sovereignty.model.Page;

import junit.framework.TestCase;


public class TestCardDAO extends TestCase {

	public void testFind() {
	    CardDAO cd = new CardDAO();
	    try {
	    	Card c = cd.getCardByRecipientAndEventType("Andrew", "ANNIVERSARY");
	    	System.out.println("constant " + c.getEventType() + " = " + c.getRecipient());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    	
	    }
	}
	
	public void testCreate() {
	    CardDAO cd = new CardDAO();
	    PageDAO pd = new PageDAO();
	    try {
	    	// can add it
	    	Card card = new Card("test_id", "Test Recipient", "BACK_TO_SCHOOL","PORTRAIT");
	    	card.setFrontPage(new Page(pd.createEmptyPage()));
			card.setLeftPage(new Page(pd.createEmptyPage()));
			card.setRightPage(new Page(pd.createEmptyPage()));
			card.setBackPage(new Page("default_back", 0));
	    	boolean b = cd.addCard(card);
	    	assertTrue(b);
	    	
	    	// can retrieve it
	    	Card c2 = cd.getCardByID(card.getCardID());
	    	assertEquals(c2.getCardID(), "test_id");
	    	assertEquals(c2.getEventType(), "BACK_TO_SCHOOL");
	    	assertEquals(c2.getRecipient(), "Test Recipient");
	    	assertEquals(c2.getOrientation(), "PORTRAIT");
	    	assertEquals(c2.getFrontPage().getPageID(), card.getFrontPage().getPageID());
	    	assertEquals(c2.getFrontPage().getIsModifiable(), card.getFrontPage().getIsModifiable());
	    	assertEquals(c2.getLeftPage().getPageID(), card.getLeftPage().getPageID());
	    	assertEquals(c2.getLeftPage().getIsModifiable(), card.getLeftPage().getIsModifiable());
	    	assertEquals(c2.getRightPage().getPageID(), card.getRightPage().getPageID());
	    	assertEquals(c2.getRightPage().getIsModifiable(), card.getRightPage().getIsModifiable());
	    	assertEquals(c2.getBackPage().getPageID(), card.getBackPage().getPageID());
	    	assertEquals(c2.getBackPage().getIsModifiable(), card.getBackPage().getIsModifiable());

	    	// can delete it
	    	assertTrue (cd.deleteCard(c2.getCardID()));
	    	assertTrue (pd.deletePage(c2.getFrontPage().getPageID()));
	    	assertTrue (pd.deletePage(c2.getLeftPage().getPageID()));
	    	assertTrue (pd.deletePage(c2.getRightPage().getPageID()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
