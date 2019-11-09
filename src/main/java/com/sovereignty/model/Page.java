package com.sovereignty.model;

import java.util.ArrayList;

public class Page {
	
	String pageID;
	int isModifiable;
	
	ArrayList<VisualElement> elements;
	
	public Page(String pageID) {
		this.pageID = pageID;
		this.isModifiable = 1;
		this.elements = new ArrayList<VisualElement>();
	}
	
	public Page(String pageID, int isModifiable) {
		this.pageID = pageID;
		this.isModifiable = isModifiable;
		this.elements = new ArrayList<VisualElement>();
	}

	public String getPageID() {
		return pageID;
	}

	public void setPageID(String pageID) {
		this.pageID = pageID;
	}

	public int getIsModifiable() {
		return isModifiable;
	}

	public void setIsModifiable(int isModifiable) {
		this.isModifiable = isModifiable;
	}

	public ArrayList<VisualElement> getElements() {
		return elements;
	}

	public void setElements(ArrayList<VisualElement> elements) {
		this.elements = elements;
	}
	
	
	
}
