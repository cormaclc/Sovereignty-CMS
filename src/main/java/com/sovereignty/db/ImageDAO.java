package com.sovereignty.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sovereignty.model.Image;

public class ImageDAO {

	java.sql.Connection conn;
	
	public ImageDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}
	}
	
	// Return null if image does not exist
	public Image getImageByID(String imageID) throws Exception{
		try {
			Image image = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Images WHERE imageID = ?;");
			ps.setString(1,  imageID);

            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                image = generateImage(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return image;
		} catch (Exception e) {
			throw new Exception("Could not get image with ID"+imageID);
		}
	}
	
	// Return null if image does not exist
	public Image getImageByName(String imageName) throws Exception{
		try {
			Image image = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Images WHERE imageName = ? ;");
			ps.setString(1,  imageName);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                image = generateImage(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return image;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Could not get image");
		}
	}
	
	// Generates a new card for createCard()
	public boolean addImage(Image image) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Images "
					+ "(imageID, imageName, imageURL) "
					+ "values(?,?,?);");
            ps.setString(1, image.getImageID());
            ps.setString(2, image.getImageName());
            ps.setString(3, image.getImageURL());

            ps.execute();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed to insert image: " + e.getMessage());
        }
		
	}

	
    private Image generateImage(ResultSet res) throws Exception {
    	String imageID = res.getString("imageID");
        String imageName  = res.getString("imageName");
        String imageURL = res.getString("imageURL");

        return new Image (imageID, imageName, imageURL);
    }
    
    public List<Image> getAllImages() throws Exception{
    	List<Image> allImages = new ArrayList<>();
    	try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM Images";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	Image i = generateImage(resultSet);
            	allImages.add(i);
            }
            resultSet.close();
            statement.close();
            return allImages;
    	}catch (Exception e) {
    		throw new Exception("Failed getting all images: "+ e.getMessage());
    	}    	
    }
    
    
    public boolean deleteImage(String imageID) throws Exception{
    	try {
    		
    		PreparedStatement ps = conn.prepareStatement("DELETE FROM Images WHERE imageID = ?;");
    		ps.setString(1, imageID);
    		int numAffected = ps.executeUpdate();
    		ps.close();
    		
    		return numAffected == 1;
    	}catch (Exception e) {
			throw new Exception("Failed to delete Image: "+e.getMessage());
		}
    }
	
}
