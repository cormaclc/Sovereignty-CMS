package com.sovereignty;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.http.GetCardRequest;
import com.sovereignty.http.GetCardResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetCardTest {

    private static GetCardRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new GetCardRequest();
        input.setCardID("ce8abc20-ff61-11e9-");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testGetCard() {
        GetCardHandler handler = new GetCardHandler();
        Context ctx = createContext();

        GetCardResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200, output.getCode());
//        Assert.assertEquals("ANNIVERSARY", output.getCard().getEventType());
//        Assert.assertEquals("Andrew", output.getCard().getRecipient());
//        Assert.assertEquals("PORTRAIT", output.getCard().getOrientation());
    }
}

