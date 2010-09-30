package edu.lumivi.ximcontroller.applications;

import processing.core.PApplet;

import edu.lumivi.ximcontroller.devices.XIMMachine;

public class Follower extends XIMApplication
{
	int color;
	
	
	public Follower(PApplet applet, XIMMachine m, int c)
	{
		super(applet, m);
		this.color = c;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}
	
	@Override
	public void execute()
	{
		int tipoX = this.getTracking().getTileX();
		int tipoY = this.getTracking().getTileY();
		
		this.getFloor().setTile(tipoX, tipoY, this.getColor());
	}

}
