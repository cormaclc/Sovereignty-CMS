package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.http.GetImageRequest;

public class GetImageHandler implements RequestHandler<Object, String> {
	CardDAO cardDao = new CardDAO();
    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        
        try {
        	
        }catch (Exception e) {
			// TODO: handle exception
		}
        return new GetImageRequest();
    }

}
