package com.sovereignty;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sovereignty.db.VisualElementDAO;
import com.sovereignty.http.UploadImageRequest;
import com.sovereignty.http.UploadImageResponse;

//public class UploadImageHandler implements RequestHandler<S3Event, String> {
public class UploadImageHandler implements RequestHandler<UploadImageRequest, UploadImageResponse> {
	private static final float MAX_WIDTH = 100;
    private static final float MAX_HEIGHT = 100;
    private final String JPG_TYPE = (String) "jpg";
    private final String JPG_MIME = (String) "image/jpeg";
    private final String PNG_TYPE = (String) "png";
    private final String PNG_MIME = (String) "image/png";
    
    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
    VisualElementDAO veDAO = new VisualElementDAO();
    
    public UploadImageHandler() {}

    UploadImageHandler(AmazonS3 s3) { this.s3 = s3; }

    @Override
    public UploadImageResponse handleRequest(UploadImageRequest event, Context context) {
        context.getLogger().log("Received image to be uploaded: " + event.getImage64());
        
// Get the object from the event and show its content type
//        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
//        String key = event.getRecords().get(0).getS3().getObject().getKey();
       
        String bucket = "sovereignty-cms/images";
        String key = event.getImageName();
        
     // Infer the image type.
        Matcher matcher = Pattern.compile(".*\\.([^\\.]*)").matcher(event.getImageName());
        if (!matcher.matches()) {
            System.out.println("Unable to infer image type for key "+ key);
        }
        String imageType = matcher.group(1);
        if (!(JPG_TYPE.equals(imageType)) && !(PNG_TYPE.equals(imageType))) {
            System.out.println("Skipping non-image " + key);
        }
        
        // base64 to imageFile
        File imageFile = new File(event.getImageName());
        byte[] imgBytes = Base64.decodeBase64(event.getImage64());

        try {
        	BufferedImage srcImage = ImageIO.read(new ByteArrayInputStream(imgBytes));
           
        	ByteArrayOutputStream os = new ByteArrayOutputStream();
        	ImageIO.write(srcImage, imageType, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            ObjectMetadata meta = new ObjectMetadata();

            // Set Content-Length and Content-Type
            meta.setContentLength(os.size());
            if (JPG_TYPE.equals(imageType)) { meta.setContentType(JPG_MIME); }
            if (PNG_TYPE.equals(imageType)) { meta.setContentType(PNG_MIME); }
            
        	ImageIO.write(srcImage, imageType, os);
        	
        	
        	try {
        		// write the file to s3        		
                s3.putObject(bucket, key, is, meta);
                // TODO: create a visualElement to store? 
                return new UploadImageResponse(200, "image uploaded");
            }
            catch(AmazonServiceException e) {
                System.err.println(e.getErrorMessage());
                System.exit(1);
    			return new UploadImageResponse(500, "Can't upload image to s3. AmazonServiceException Error: "+ e.getMessage());
            }
		} catch (IOException e1) {
			e1.printStackTrace();
			return new UploadImageResponse(500, "failed uploading image. IOException Error: "+ e1.getMessage());
		}
        
//	getting response from s3               
//        try {        	
//            S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
//            String contentType = response.getObjectMetadata().getContentType();
//            context.getLogger().log("CONTENT TYPE: " + contentType);
//             
//            return contentType;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            context.getLogger().log(String.format(
//                "Error getting object %s from bucket %s. Make sure they exist and"
//                + " your bucket is in the same region as this function.", key, bucket));
//            throw e;
//        }
    }
}