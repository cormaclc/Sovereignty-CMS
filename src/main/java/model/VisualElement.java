package main.java.model;

public class VisualElement {

	public int heightBounds;
	public int widthBounds;
	public int xPos;
	public int yPos; 
	public String backgroundColor;
	
	public VisualElement(int heightBounds, int widthBounds, int xPos, int yPos, String backgroundColor) {
		this.heightBounds = heightBounds;
		this.widthBounds = widthBounds;
		this.xPos = xPos;
		this.yPos = yPos;
		this.backgroundColor = backgroundColor;
	}
	
}
