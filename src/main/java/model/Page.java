package main.java.model;

import java.util.ArrayList;

public class Page {
	
	public ArrayList<VisualElement> visualElements;
	public boolean isModifiable;

	public Page(ArrayList<VisualElement> elements, boolean isMod) {
		this.visualElements = elements;
		this.isModifiable = isMod;
	}

}
