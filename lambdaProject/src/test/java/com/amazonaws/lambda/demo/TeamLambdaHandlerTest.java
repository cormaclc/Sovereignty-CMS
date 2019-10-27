package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sovereignty.cms.CreateCardHandler;
import com.sovereignty.cms.http.CreateCardRequest;
import com.sovereignty.cms.http.CreateCardResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class TeamLambdaHandlerTest {

    private static CreateCardRequest input;

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
    public void testLambdaFunctionHandler() {
        CreateCardHandler handler = new CreateCardHandler();
        Context ctx = createContext();

        CreateCardResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
