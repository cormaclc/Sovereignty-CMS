package com.sovereignty.model;

import java.util.ArrayList;
import java.util.List;

public class Page {
	String pageID;
	int isModifiable;
	List<VisualElement> listVisualElements; 
	
	public Page(String pageID, int isModifiable, List<VisualElement> listVE) {
		this.pageID = pageID;
		this.isModifiable = isModifiable;
		this.listVisualElements = listVE;
	}
	
	public Page(String pageID, int isModifiable) {
		this.pageID = pageID;
		this.isModifiable = isModifiable;
		this.listVisualElements = new ArrayList<VisualElement>();
	}
	
	public Page(String pageID) {
		this.pageID = pageID;
		this.isModifiable = 1;
		this.listVisualElements = new ArrayList<VisualElement>();
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

	public List<VisualElement> getListVisualElements() {
		return listVisualElements;
	}

	public void setListVisualElements(List<VisualElement> listVisualElements) {
		this.listVisualElements = listVisualElements;
	}
	
	
}