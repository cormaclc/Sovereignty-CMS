package com.sovereignty;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.db.CardDAO;
import com.sovereignty.db.PageDAO;
import com.sovereignty.http.DuplicateCardRequest;
import com.sovereignty.http.DuplicateCardResponse;


/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DuplicateCardHandlerTest {

    private static DuplicateCardRequest input;
    private static CardDAO cd = new CardDAO();

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
	     input = new DuplicateCardRequest();
	    try {
        input.setCardID("test_card");
        input.setRecipient("Cormac");
        input.setEventType("Birthday");
        
		} catch (Exception e) {
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
    public void testDuplicateCard() {
        DuplicateCardHandler handler = new DuplicateCardHandler();
        Context ctx = createContext();

        DuplicateCardResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200, output.getCode());
        Assert.assertEquals(output.getCard().getRecipient(), "Cormac");
        Assert.assertEquals(output.getCard().getEventType(), "Birthday");
        Assert.assertEquals(output.getCard().getOrientation(), "Portrait");
        Assert.assertEquals(output.getCard().getFrontPage().getListVisualElements().get(0).getText(), "Made by Sovereignty CMS");

        try {
			boolean deleted = cd.deleteCard(output.getCard().getCardID());
			Assert.assertTrue(deleted);
		} catch (Exception e) {
			Assert.fail("Failed to delete the duplicated card");
		}
    }
}
