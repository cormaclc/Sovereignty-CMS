package com.sovereignty;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.TestContext;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.AllCardsResponse;
import com.sovereignty.model.Card;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetAllCardsHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testGetAllCardsHandler() {
        GetAllCardsHandler handler = new GetAllCardsHandler();
        Context ctx = createContext();

        AllCardsResponse output = handler.handleRequest(input, ctx);

        // Generate Validation Data
        List<Card> cards = new ArrayList<Card>();
        CardDAO cd = new CardDAO();
        try {
			cards = cd.getAllCards();
			System.out.println(cards);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail ("didn't work:" + e.getMessage());
		}
        
        // TODO: validate output here if needed.
        Assert.assertEquals(cards.size(), output.getCards().size());
        Assert.assertEquals(200, output.getCode());
        
    }
}

