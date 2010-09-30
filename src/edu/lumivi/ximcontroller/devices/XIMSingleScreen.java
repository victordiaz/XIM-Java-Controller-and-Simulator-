package edu.lumivi.ximcontroller.devices;

import processing.core.PApplet;
import processing.core.PFont;

public class XIMSingleScreen {

	PApplet p;
	int width, height;
	int x, y;
	int mainColor; 
	
	static int screenNumTotal = 0; 
	int screenNum; 
	int fading; 
	float fadingFrequency; 

	String message; 
	PFont infoFont;

	public XIMSingleScreen(PApplet p, int x, int y, int width, int height) {

		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.infoFont = p.loadFont("ArialMT-48.vlw");

		screenNum = screenNumTotal;
		screenNumTotal = screenNumTotal + 1; 
		
		initialize(); 

	}

	public void initialize() {
		screenBg(); 
		mainColor = p.color(100); 
		message = ""; 

	}

	private void screenBg() {
		p.fill(this.mainColor);
		p.noStroke();
		p.rect(0, 0, width, height);

	}

	public void calculate() {

	}

	public void render() {
		p.pushMatrix();
		p.translate(this.x, this.y);

		this.screenBg();

		//this.mainColor = p.color(255, 0, 0);
		//p.fill(mainColor);
		// ellipse(10, 10, 10, 10);

		this.info();
		p.popMatrix();

	}

	private void info() {
		p.fill(0, 100);
		p.rect(0, 0, this.width / 3, 15);

		p.textFont(infoFont, 10);

		p.fill(0);
		p.text("screen" + " " + screenNum, 5, 10); 
		
		p.text(this.message, 50, 50); 


	}


	/**
	 * @return the mainColor
	 */
	public int getMainColor() {
		return mainColor;
	}

	/**
	 * @param mainColor
	 *            the mainColor to set
	 */
	public void setMainColor(int mainColor) { 
		
		this.mainColor = mainColor;
	}

	/**
	 * @return the fading
	 */
	public int getFading()
	{
		return fading;
	}

	/**
	 * @param fading the fading to set
	 */
	public void setFading(int fading)
	{
		this.fading = fading;
	}

	/**
	 * @return the fadingFrequency
	 */
	public float getFadingFrequency()
	{
		return fadingFrequency;
	}

	/**
	 * @param d the fadingFrequency to set
	 */
	public void setFadingFrequency(float d)
	{
		this.fadingFrequency = d;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	

}
