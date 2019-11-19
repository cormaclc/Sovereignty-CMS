package com.sovereignty.db;


import java.util.List;
import java.util.UUID;

import com.sovereignty.model.Card;
import com.sovereignty.model.Page;
import com.sovereignty.model.VisualElement;

import junit.framework.TestCase;


public class TestCardDAO extends TestCase {

	public void testFind() {
	    CardDAO cd = new CardDAO();
	    try {
	    	Card c = cd.getCardByRecipientAndEventType("Andrew", "ANNIVERSARY");
	    	assertEquals(c.getCardID(), "test_card");
	    	assertEquals(c.getEventType(), "ANNIVERSARY");
	    	assertEquals(c.getRecipient(), "Andrew");
	    	assertEquals(c.getOrientation(), "PORTRAIT");
	    	assertEquals(c.getFrontPage().getPageID(), "default_back");
	    	assertEquals(c.getFrontPage().getIsModifiable(), 0);
	    	assertEquals(c.getLeftPage().getPageID(), "default_back");
	    	assertEquals(c.getLeftPage().getIsModifiable(), 0);
	    	assertEquals(c.getRightPage().getPageID(), "default_back");
	    	assertEquals(c.getRightPage().getIsModifiable(), 0);
	    	assertEquals(c.getBackPage().getPageID(), "default_back");
	    	assertEquals(c.getBackPage().getIsModifiable(), 0);
	    	
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
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	public void testUpdate() {
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

	    	// test Update
	    	c2.setEventType("updated event");
	    	c2.setRecipient("updated recipient");
	    	c2.setOrientation("updated orientation");
	    	VisualElement ve1 = new VisualElement("testUpdateElt", "text", 0, 0, 0, 0, "testText", "font",
	    			"", c2.getFrontPage().getPageID(), VisualElementDAO.CHANGED);
	    	VisualElement ve2 = new VisualElement("testUpdateElt2", "text", 0, 0, 0, 0, "testText", "font",
	    			"", c2.getLeftPage().getPageID(), VisualElementDAO.CHANGED);
	    	VisualElement ve3 = new VisualElement("testUpdateElt3", "text", 0, 0, 0, 0, "testText", "font",
	    			"", c2.getRightPage().getPageID(), VisualElementDAO.CHANGED);
	    	c2.getFrontPage().addElement(ve1);
	    	c2.getLeftPage().addElement(ve2);
	    	c2.getRightPage().addElement(ve3);
	    	
	    	Card c3 = cd.updateCard(c2);
	    	
	    	// Verify Changes
	    	assertEquals(c3.getCardID(), "test_id");
	    	assertEquals(c3.getEventType(), "updated event");
	    	assertEquals(c3.getRecipient(), "updated recipient");
	    	assertEquals(c3.getOrientation(), "updated orientation");
	    	
	    	assertEquals(c3.getFrontPage().getPageID(), card.getFrontPage().getPageID());
	    	assertEquals(c3.getFrontPage().getIsModifiable(), card.getFrontPage().getIsModifiable());
	    	assertEquals(c3.getFrontPage().getListVisualElements().size(), 1);
	    	VisualElement uve1 = c3.getFrontPage().getListVisualElements().get(0);
	    	assertEquals(uve1.getEltID(), "testUpdateElt");

	    	
	    	assertEquals(c3.getLeftPage().getPageID(), card.getLeftPage().getPageID());
	    	assertEquals(c3.getLeftPage().getIsModifiable(), card.getLeftPage().getIsModifiable());
	    	assertEquals(c3.getLeftPage().getListVisualElements().size(), 1);
	    	VisualElement uve2 = c3.getLeftPage().getListVisualElements().get(0);
	    	assertEquals(uve2.getEltID(), "testUpdateElt2");
	    	
	    	assertEquals(c3.getRightPage().getPageID(), card.getRightPage().getPageID());
	    	assertEquals(c3.getRightPage().getIsModifiable(), card.getRightPage().getIsModifiable());
	    	assertEquals(c3.getRightPage().getListVisualElements().size(), 1);
	    	VisualElement uve3 = c3.getRightPage().getListVisualElements().get(0);
	    	assertEquals(uve3.getEltID(), "testUpdateElt3");
	    	
	    	assertEquals(c3.getBackPage().getPageID(), card.getBackPage().getPageID());
	    	assertEquals(c3.getBackPage().getIsModifiable(), card.getBackPage().getIsModifiable());
	    	
	    	
	    	// Update Existing Element
	    	List<VisualElement> frontPageElts = c3.getFrontPage().getListVisualElements();
	    	for(VisualElement ve: frontPageElts) {
	    		ve.setText("updated test text");
	    		ve.setFont("updated font");
	    		ve.setUpdated(VisualElementDAO.CHANGED);
	    		ve.setWidth(5);
	    		ve.setHeight(5);
	    		ve.setxPosition(5);
	    		ve.setyPosition(5);
	    		ve.setImageURL("test URL");
	    	}
	    	
	    	Card c4 = cd.updateCard(c3);
	    	
	    	// Verify Changes
	    	Card updateTest = cd.getCardByID(c4.getCardID());
	    	assertEquals(updateTest.getCardID(), "test_id");
	    	assertEquals(updateTest.getEventType(), "updated event");
	    	assertEquals(updateTest.getRecipient(), "updated recipient");
	    	assertEquals(updateTest.getOrientation(), "updated orientation");
	    	
	    	assertEquals(updateTest.getFrontPage().getPageID(), card.getFrontPage().getPageID());
	    	assertEquals(updateTest.getFrontPage().getIsModifiable(), card.getFrontPage().getIsModifiable());
	    	assertEquals(updateTest.getFrontPage().getListVisualElements().size(), 1);
	    	VisualElement uve = updateTest.getFrontPage().getListVisualElements().get(0);
	    	assertEquals(uve.getEltID(), "testUpdateElt");
	    	assertEquals(uve.getText(), "updated test text");
	    	assertEquals(uve.getFont(), "updated font");
	    	assertEquals(uve.getHeight(), 5);
	    	assertEquals(uve.getWidth(), 5);
	    	assertEquals(uve.getxPosition(), 5);
	    	assertEquals(uve.getyPosition(), 5);
	    	assertEquals(uve.getImageURL(), "");	    	
	    	// can delete it
	    	assertTrue (cd.deleteCard(c4.getCardID()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
