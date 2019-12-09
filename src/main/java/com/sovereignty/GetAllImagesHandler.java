package com.sovereignty;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.ImageDAO;
import com.sovereignty.http.AllImagesResponse;
import com.sovereignty.model.Image;

public class GetAllImagesHandler implements RequestHandler<Object, AllImagesResponse> {

    public AllImagesResponse handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        ImageDAO imageDAO = new ImageDAO();
        
        try {
        	List<Image> allImages = imageDAO.getAllImages();
        	return new AllImagesResponse(200, "Got all images", allImages);
        }catch (Exception e) {
        	return new AllImagesResponse(500, e.getMessage());
		}
    }
}
