package com.sovereignty;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.http.CreateCardRequest;
import com.sovereignty.http.CreateCardResponse;
import com.sovereignty.http.DeleteCardRequest;
import com.sovereignty.http.DeleteCardResponse;


/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class TestCreateDeleteCardHandler {

    private static CreateCardRequest inputCreate;
    private static DeleteCardRequest inputDelete;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        inputCreate = new CreateCardRequest();
        inputCreate.setCardID("test_card1");
        inputCreate.setEventType("BIRTHDAY");
        inputCreate.setRecipient("Cormac");
        inputCreate.setOrientation("LANDSCAPE");
        
        // TODO: set up your sample input object here.
    	inputDelete = new DeleteCardRequest();
        inputDelete.setCardID("test_card1");
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

        CreateCardResponse output = handler.handleRequest(inputCreate, ctx);
        
		// TODO: validate output here if needed.
        Assert.assertEquals(200, output.getCode());
        Assert.assertEquals("test_card1", output.getCard().getCardID());
        Assert.assertEquals("Cormac", output.getCard().getRecipient());
        Assert.assertEquals("BIRTHDAY", output.getCard().getEventType());
        Assert.assertEquals("LANDSCAPE", output.getCard().getOrientation());
        Assert.assertNotNull(output.getCard().getFrontPage());
        Assert.assertEquals(output.getCard().getFrontPage().getIsModifiable(), 1);
        Assert.assertNotNull(output.getCard().getLeftPage());
        Assert.assertEquals(output.getCard().getLeftPage().getIsModifiable(), 1);
        Assert.assertNotNull(output.getCard().getRightPage());
        Assert.assertEquals(output.getCard().getRightPage().getIsModifiable(), 1);
        Assert.assertNotNull(output.getCard().getBackPage());
        Assert.assertEquals(output.getCard().getBackPage().getIsModifiable(), 0);
    }
    
    @Test
    public void testDeleteCard() {
        DeleteCardHandler handler = new DeleteCardHandler();
        Context ctx = createContext();

        DeleteCardResponse output = handler.handleRequest(inputDelete, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200, output.getCode());
    }
    
}
