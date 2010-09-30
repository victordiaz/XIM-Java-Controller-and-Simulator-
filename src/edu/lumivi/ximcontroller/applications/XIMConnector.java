package edu.lumivi.ximcontroller.applications;

import processing.core.PApplet;


import edu.lumivi.ximcontroller.devices.XIMMachine;

public abstract class XIMConnector extends XIMApplication
{
	int ui;		// Updating interval
	PApplet p;
	int time = 0;
	
	public XIMConnector(PApplet p, XIMMachine m, int ui)
	{
		super(p, m);
		this.p = p;
		this.ui = ui;
	}
	
	public int getTime()
	{
		return time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}

	public boolean isUpdateTime()
	{
		if (p.millis() - this.time > this.ui)
		{
			this.time = p.millis();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void execute()
	{
		if (this.isUpdateTime())
			this.update();
	}
	

	public abstract void update();
}
