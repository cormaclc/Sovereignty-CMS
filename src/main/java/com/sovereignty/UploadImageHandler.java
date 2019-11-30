package com.sovereignty;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.amazonaws.AmazonServiceException;
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

//public class UploadImageHandler implements RequestHandler<S3Event, String> {
public class UploadImageHandler implements RequestHandler<UploadImageRequest, UploadImageResponse> {
    private final String JPG_TYPE = (String) "jpg";
    private final String JPG_MIME = (String) "image/jpeg";
    private final String PNG_TYPE = (String) "png";
    private final String PNG_MIME = (String) "image/png";
    
    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
    ImageDAO dao = new ImageDAO();
    
//    public UploadImageHandler() {}

//    UploadImageHandler(AmazonS3 s3) { this.s3 = s3; }

    public UploadImageResponse handleRequest(UploadImageRequest input, Context context) {
        context.getLogger().log("Received image to be uploaded: " + input.getImage64());
              
        String bucketName = "sovereignty-images";
        String bucketURL = "https://sovereignty-images.s3.amazonaws.com/";
        
        String key = input.getImageName();
	    String imageURL = bucketURL + key + "-" + input.getImageID();
        
// Infer the image type.
        Matcher matcher = Pattern.compile(".*\\.([^\\.]*)").matcher(input.getImageName());
        if (!matcher.matches()) { System.out.println("Unable to infer image type for key "+ key); }
        String imageType = matcher.group(1);
        if (!(JPG_TYPE.equals(imageType)) && !(PNG_TYPE.equals(imageType))) { System.out.println("Skipping non-image " + key); }
        
// base64 to imageFile (imageByteArray)
        byte[] imageByteArray = Base64.decodeBase64(input.getImage64());

        ObjectMetadata meta = new ObjectMetadata();
        
// Set Content-Length and Content-Type
//      meta.setContentLength(os.size());
		meta.setContentLength(input.getImage64().length());
        if (JPG_TYPE.equals(imageType)) { meta.setContentType(JPG_MIME); }
        if (PNG_TYPE.equals(imageType)) { meta.setContentType(PNG_MIME); }
        
		InputStream inputStream = new ByteArrayInputStream(imageByteArray);
		
//      BufferedImage srcImage = ImageIO.read(new ByteArrayInputStream(imageByteArray));
//      
//      ByteArrayOutputStream os = new ByteArrayOutputStream();
//      ImageIO.write(srcImage, imageType, os);
//      InputStream is = new ByteArrayInputStream(os.toByteArray());
//      ObjectMetadata meta = new ObjectMetadata();
		
		try {
			PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, meta);
			s3.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
		    Image img = new Image(input.getImageID(), key, imageURL);
		    try {
				dao.addImage(img);
			} catch (Exception e) {
				e.printStackTrace();
				return new UploadImageResponse(500, "Can't add image to database, Error: "+e.getMessage());
			}		    
		    
		    return new UploadImageResponse(200, "image uploaded");
		}
		catch(AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		    System.exit(1);
			return new UploadImageResponse(500, "Can't upload image to s3. AmazonServiceException Error: "+ e.getMessage());
		}
    }
}