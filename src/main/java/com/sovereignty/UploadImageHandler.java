package com.sovereignty;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sovereignty.db.ImageDAO;
import com.sovereignty.http.UploadImageRequest;
import com.sovereignty.http.UploadImageResponse;
import com.sovereignty.model.Image;

public class UploadImageHandler implements RequestHandler<UploadImageRequest, UploadImageResponse> {
	private final String JPG_TYPE = (String) "jpg";
	private final String JPG_MIME = (String) "image/jpeg";
	private final String PNG_TYPE = (String) "png";
	private final String PNG_MIME = (String) "image/png";

	ImageDAO dao = new ImageDAO();

	public UploadImageHandler() {
	}

	public UploadImageResponse handleRequest(UploadImageRequest input, Context context) {
		context.getLogger().log("Received image to be uploaded: " + input.getImageName());

		String bucketName = "sovereignty-images";
		String bucketURL = "https://sovereignty-images.s3.amazonaws.com/";

		String key = input.getImageName() + "-" + input.getImageID();
		String imageURL = bucketURL + key;
		
		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

		try {
			byte[] imageByteArray = Base64.getDecoder().decode(	input.getImage64());
			InputStream inputStream = new ByteArrayInputStream(imageByteArray);

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(imageByteArray.length);
			
			PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, meta);
			s3.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
			
			try {
				Image img = new Image(input.getImageID(), key, imageURL);
				dao.addImage(img);
				return new UploadImageResponse(200, "image uploaded:", imageURL);
			} catch (Exception e) {
				System.out.println("---------------");
				e.printStackTrace();
				return new UploadImageResponse(500, "Can't add image to database, Error: " + e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new UploadImageResponse(500, "Can't upload image to s3. AmazonServiceException Error: " + e.getMessage());
		}
	}
}