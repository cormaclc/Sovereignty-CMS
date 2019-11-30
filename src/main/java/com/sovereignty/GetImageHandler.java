package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.ImageDAO;
import com.sovereignty.http.GetImageRequest;
import com.sovereignty.http.GetImageResponse;
import com.sovereignty.model.Image;

public class GetImageHandler implements RequestHandler<GetImageRequest, GetImageResponse> {
	ImageDAO imageDao = new ImageDAO();
	
    public String validateGetImageRequest(GetImageRequest input) {
		if (input.getImageID() == null || input.getImageName().isEmpty()) {
			return "imageID and imageName are required";
		}
		return null;
	}
    
    public GetImageResponse handleRequest(GetImageRequest input, Context context) {
        context.getLogger().log("Input: " + input);
        Image i ;
        try {
        	String validationError = this.validateGetImageRequest(input);
        	if (validationError != null) {
        		return new GetImageResponse(400, validationError);
        	}
        	i = imageDao.getImageByName(input.getImageName());
        	return new GetImageResponse(200, "successfully got image" , i);
        }catch (Exception e) {
        	
		}
        //return new GetImageRequest();
        return ;
    }

}
