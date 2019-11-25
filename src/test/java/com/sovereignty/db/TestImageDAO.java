package com.sovereignty.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sovereignty.model.Image;

import junit.framework.TestCase;

public class TestImageDAO extends TestCase {

	public void testFindByID() {
		ImageDAO id = new ImageDAO();
		try {
			Image image = id.getImageByID("test_image");
			assertEquals(image.getImageID(), "test_image");
			assertEquals(image.getImageName(), "test_name");
			assertEquals(image.getImageURL(), "test_url");
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void testFindByName() {
		ImageDAO id = new ImageDAO();
		try {
			Image image = id.getImageByName("test_name");
			assertEquals(image.getImageID(), "test_image");
			assertEquals(image.getImageName(), "test_name");
			assertEquals(image.getImageURL(), "test_url");		
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void testGetAllImages() {
		ImageDAO id = new ImageDAO();
		try {
			List<Image> images = id.getAllImages();
			assertEquals(images.size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}

	public void testCreate() {
		ImageDAO id = new ImageDAO();
		try {
			// can add it
			Image image = new Image("test", "name", "url");
			
			boolean addedImage = id.addImage(image);
			
			// Check
			assertTrue(addedImage);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void testDelete() {
		ImageDAO id = new ImageDAO();
		try {
			// can add it
			String imageID = "test";
			
			boolean deletedImage = id.deleteImage(imageID);
			
			// Check
			assertTrue(deletedImage);

		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	

}
