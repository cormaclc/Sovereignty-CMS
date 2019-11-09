package com.sovereignty.db;


import java.util.UUID;

import com.sovereignty.model.Card;
import com.sovereignty.model.Page;

import junit.framework.TestCase;


public class TestPageDAO extends TestCase {

	public void testFind() {
	    PageDAO pd = new PageDAO();
	    try {
	    	Page p = pd.getPageByID("default_back");
	    	assertEquals(p.getPageID(), "default_back");
	    	assertEquals(p.getIsModifiable(), 0);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	public void testCreate() {
	    PageDAO pd = new PageDAO();
	    try {
	    	// can add it
	    	Page page = new Page("test_id", 1);
	    	boolean b = pd.addPage(page);
	    	assertTrue(b);
	    	
	    	// can retrieve it
	    	Page p2 = pd.getPageByID(page.getPageID());
	    	assertEquals(p2.getPageID(), "test_id");
	    	assertEquals(p2.getIsModifiable(), 1);
	    	
	    	// can delete it
	    	assertTrue (pd.deletePage(p2.getPageID()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	
	public void testCreateEmptyPage() {
	    PageDAO pd = new PageDAO();
	    try {
	    	// can add it
	    	String pageID = pd.createEmptyPage();
	    	assertNotNull(pageID);
	    	
	    	// can retrieve it
	    	Page p2 = pd.getPageByID(pageID);
	    	assertEquals(p2.getPageID(), pageID);
	    	assertEquals(p2.getIsModifiable(), 1);
	    	
	    	// can delete it
	    	assertTrue (pd.deletePage(p2.getPageID()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
}
