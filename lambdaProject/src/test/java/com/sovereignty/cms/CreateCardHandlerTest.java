package com.sovereignty.cms;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.cms.db.CardDAO;
import com.sovereignty.cms.http.CreateCardRequest;
import com.sovereignty.cms.http.CreateCardResponse;
import com.sovereignty.cms.http.DeleteCardRequest;
import com.sovereignty.cms.http.DeleteCardResponse;
import com.sovereignty.cms.model.Card;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateCardHandlerTest {

    private static CreateCardRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new CreateCardRequest();
        input.setCardID("test_card1");
        input.setEventType("BIRTHDAY");
        input.setRecipient("Cormac");
        input.setOrientation("LANDSCAPE");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testCreateCard() {
        CreateCardHandler handler = new CreateCardHandler();
        Context ctx = createContext();

        CreateCardResponse output = handler.handleRequest(input, ctx);
        
        System.out.println(output.error);
		// TODO: validate output here if needed.
        Assert.assertEquals(200, output.getCode());
		
        
       
    }
}
