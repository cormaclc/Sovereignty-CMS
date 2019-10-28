package com.sovereignty.cms;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.cms.http.DeleteCardRequest;
import com.sovereignty.cms.http.DeleteCardResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteCardTest {

    private static DeleteCardRequest input;

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
    public void testDeleteCard() {
        DeleteCardHandler handler = new DeleteCardHandler();
        Context ctx = createContext();

        DeleteCardResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
