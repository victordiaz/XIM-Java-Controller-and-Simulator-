package edu.lumivi.ximcontroller.applications;

import processing.core.PApplet;

import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMTracking;



public class XIMTrackingMouse extends XIMTrackingReader
{	
	int xStart = 200;
	int xEnd = 550;
	int yStart = 0;
	int yEnd = 300;
	
	float minX = -279; 
	float minY = 264; 
	float maxX = 279;
	float maxY = -264;
	
	
	public XIMTrackingMouse(PApplet p, XIMMachine m, int ui)
	{
		super(p, m, ui);
	}
	
	
	//acceso a la bd 
	public void update()
	{
		XIMTracking t = this.getTracking();
		
		int mx = p.mouseX;
		int my = p.mouseY;
		if (mx >= xStart && mx <= xEnd && my >= yStart && my <= yEnd)
		{
			t.setX(p.map(mx, xStart, xEnd, minX, maxX));
			t.setY(p.map(my, yStart, yEnd, minY, maxY));
		}

	}
}
