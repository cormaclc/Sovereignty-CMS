package com.sovereignty;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.UpdateCardRequest;
import com.sovereignty.http.UpdateCardResponse;
import com.sovereignty.model.Card;
import com.sovereignty.model.Page;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class UpdateCardTest {

    private static UpdateCardRequest input;
    private static CardDAO cd = new CardDAO();
    static Card c = new Card();
    

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
		try {
			c = cd.getCardByID("test_card");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        input = new UpdateCardRequest();
        input.setCardID(c.getCardID());
        input.setEventType("Birthday");
        input.setRecipient(c.getRecipient());
        input.setOrientation(c.getOrientation());
        input.setFrontPage(c.getFrontPage());
        input.setLeftPage(c.getFrontPage());
        input.setRightPage(c.getFrontPage());
        input.setBackPage(c.getFrontPage());
        
        
        try {
			cd.updateCard(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testUpdateCard() {
        UpdateCardHandler handler = new UpdateCardHandler();
        Context ctx = createContext();

        UpdateCardResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Birthday", output.getCard().getEventType());
        
        try {
			cd.updateCard(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
