package edu.lumivi.ximcontroller.devices;

import processing.core.PApplet;

public class XIMTracking
{
	PApplet applet;
	
	float minX = -280; 
	float minY = 265; 
	float maxX = 280;
	float maxY = -265;
	
	int tilesX = 8;
	int tilesY = 9;
	
	float x, y;
	int tileX, tileY;
	
	
	public XIMTracking(PApplet applet)
	{
		this.applet = applet;
	}
	
	public float getX()
	{
		return x;
	}
	
	public void setX(float x)
	{
		this.x = x;
		this.tileX = (int) applet.map(this.x, this.minX, this.maxX, 0, this.tilesX);

	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
		this.tileY = (int) applet.map(this.y, this.minY, this.maxY, 0, this.tilesY);
	}

	public int getTileX()
	{
		return tileX;
	}

	public int getTileY()
	{
		return tileY;
	}

}
