package com.sovereignty.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sovereignty.model.VisualElement;

import junit.framework.TestCase;

public class TestVisualElementDAO extends TestCase {

	public void testFindByID() {
		VisualElementDAO vd = new VisualElementDAO();
		try {
			VisualElement ve = vd.getVisualElementByID("test_elt");
			assertEquals(ve.getPageID(), "default_back");
			assertEquals(ve.getEltType(), "TEXT");
			assertEquals(ve.getxPosition(), 10);
			assertEquals(ve.getyPosition(), 15);
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void testFindByPage() {
		VisualElementDAO vd = new VisualElementDAO();
		try {
			List<VisualElement> ve = vd.getVisualElementByPageID("default_back");
			assertEquals(ve.size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}

	public void testCreate() {
		VisualElementDAO vd = new VisualElementDAO();
		try {
			// can add it
			VisualElement ve = new VisualElement();
			ve.setEltID("test_id");
			ve.setEltType("TEXT");
			ve.setxPosition(0);
			ve.setyPosition(0);
			ve.setHeight(0);
			ve.setWidth(0);
			ve.setText("RANDOM TEXT");
			ve.setFont("ANDREW'S FONT");
			
			// NEW element so set isUpdated to 1
			ve.setUpdated(VisualElementDAO.CHANGED);
			
			VisualElement updated_elt = vd.update(ve);
			
			// Check
			assertEquals("test_id",updated_elt.getEltID());
			assertEquals("TEXT",updated_elt.getEltType());
			assertEquals(0,updated_elt.getxPosition());
			assertEquals(0,updated_elt.getyPosition());
			assertEquals(0,updated_elt.getHeight());
			assertEquals(0,updated_elt.getWidth());
			assertEquals("RANDOM TEXT", updated_elt.getText());
			assertEquals("ANDREW'S FONT", updated_elt.getFont());
			// Updated is reset
			assertEquals(VisualElementDAO.UNCHANGED ,updated_elt.getUpdated());

			// I need to make sure these happen in the right order
			fakeUpdate();
			update();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void fakeUpdate() {
		VisualElementDAO vd = new VisualElementDAO();
		try {
			// can add it
			VisualElement ve = new VisualElement();
			ve.setEltID("test_id");
			ve.setEltType("TEXT");
			ve.setxPosition(1);
			ve.setyPosition(1);
			ve.setHeight(1);
			ve.setWidth(1);
			ve.setText("RANDOM UPDATED TEXT");
			ve.setFont("ANDREW'S FONT");
			
			// NEW element so set isUpdated to 1
			ve.setUpdated(VisualElementDAO.UNCHANGED);
			
			ve = vd.update(ve);
			VisualElement updated_elt = vd.getVisualElementByID(ve.getEltID());
			
			// Check
			assertEquals("test_id",updated_elt.getEltID());
			assertEquals("TEXT",updated_elt.getEltType());
			assertEquals(0,updated_elt.getxPosition());
			assertEquals(0,updated_elt.getyPosition());
			assertEquals(0,updated_elt.getHeight());
			assertEquals(0,updated_elt.getWidth());
			assertEquals("RANDOM TEXT", updated_elt.getText());
			assertEquals("ANDREW'S FONT", updated_elt.getFont());
			// Updated is reset
			assertEquals(VisualElementDAO.UNCHANGED ,updated_elt.getUpdated());

		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void update() {
		VisualElementDAO vd = new VisualElementDAO();
		try {
			// can add it
			VisualElement ve = new VisualElement();
			ve.setEltID("test_id");
			ve.setEltType("TEXT");
			ve.setxPosition(1);
			ve.setyPosition(1);
			ve.setHeight(1);
			ve.setWidth(1);
			ve.setText("RANDOM UPDATED TEXT");
			ve.setFont("ANDREW'S FONT");
			
			// NEW element so set isUpdated to 1
			ve.setUpdated(VisualElementDAO.CHANGED);
			
			VisualElement updated_elt = vd.update(ve);
			
			// Check
			assertEquals("test_id",updated_elt.getEltID());
			assertEquals("TEXT",updated_elt.getEltType());
			assertEquals(1,updated_elt.getxPosition());
			assertEquals(1,updated_elt.getyPosition());
			assertEquals(1,updated_elt.getHeight());
			assertEquals(1,updated_elt.getWidth());
			assertEquals("RANDOM UPDATED TEXT", updated_elt.getText());
			assertEquals("ANDREW'S FONT", updated_elt.getFont());
			// Updated is reset
			assertEquals(VisualElementDAO.UNCHANGED ,updated_elt.getUpdated());

		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}
	
	public void testDelete() {
		VisualElementDAO vd = new VisualElementDAO();
		try {
			// can add it
			VisualElement ve = new VisualElement();
			ve.setEltID("test_id");
			ve.setEltType("TEXT");
			ve.setxPosition(1);
			ve.setyPosition(1);
			ve.setHeight(1);
			ve.setWidth(1);
			ve.setText("RANDOM UPDATED TEXT");
			ve.setFont("ANDREW'S FONT");
			
			// NEW element so set isUpdated to 1
			ve.setUpdated(VisualElementDAO.DELETE);
			
			VisualElement updated_elt = vd.update(ve);
			
			// Check
			assertEquals("test_id",updated_elt.getEltID());
			assertEquals("TEXT",updated_elt.getEltType());
			assertEquals(1,updated_elt.getxPosition());
			assertEquals(1,updated_elt.getyPosition());
			assertEquals(1,updated_elt.getHeight());
			assertEquals(1,updated_elt.getWidth());
			assertEquals("RANDOM UPDATED TEXT", updated_elt.getText());
			assertEquals("ANDREW'S FONT", updated_elt.getFont());
			// Updated is reset
			assertEquals(VisualElementDAO.UNCHANGED ,updated_elt.getUpdated());

		} catch (Exception e) {
			e.printStackTrace();
			fail("didn't work:" + e.getMessage());
		}
	}

}
