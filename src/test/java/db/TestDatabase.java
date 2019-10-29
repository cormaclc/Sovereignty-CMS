package test.java.db;


import java.util.UUID;

import junit.framework.TestCase;
import main.java.db.CardsDAO;
import main.java.model.Card;

public class TestDatabase extends TestCase {

	public void testFind() {
	    CardsDAO cd = new CardsDAO();
	    try {
	    	Card c = cd.getCard("default_card");
	    	System.out.println("Card EventType: " + c.getEventType() + " Recipient: " + c.getRecipient());
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	public void testCreate() {
	    CardsDAO cd = new CardsDAO();
	    try {
	    	// can add it
	    	String id = UUID.randomUUID().toString().substring(0, 20); // no more than 20 because of DB restrictions...
	    	Card card = new Card(id, Card.EventType.BACK_TO_SCHOOL, "Test Recipient",Card.Orientation.PORTRAIT);
	    	boolean b = cd.addCard(card);
	    	System.out.println("add constant: " + b);
	    	
	    	// can retrieve it
	    	Card c2 = cd.getCard(card.getCardID());
	    	System.out.println("C2:" + c2.getCardID());
	    	
	    	// can delete it
	    	assertTrue (cd.deleteCard(c2));
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
