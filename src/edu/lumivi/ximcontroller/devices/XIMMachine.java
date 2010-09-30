package edu.lumivi.ximcontroller.devices;

import processing.core.PApplet;

public class XIMMachine
{
	PApplet p;
	
	XIMFloor floor;
	XIMTracking tracking;
	XIMLargeScreen screen; 
	XIMSound sound; 
	
	public XIMMachine(PApplet p, XIMFloor f, XIMTracking t, XIMLargeScreen s, XIMSound sp)
	{
		this.p = p;
		this.floor = f;
		this.tracking = t;
		this.screen = s; 
		this.sound = sp; 
	}

	public XIMFloor getFloor()
	{
		return floor;
	}

	public void setFloor(XIMFloor floor)
	{
		this.floor = floor;
	}

	public XIMTracking getTracking()
	{
		return tracking;
	}

	public void setTracking(XIMTracking tracking)
	{
		this.tracking = tracking;
	}

	public XIMLargeScreen getScreen()
	{
		return screen;
	}

	public void setScreen(XIMLargeScreen screen)
	{
		this.screen = screen;
	}
	
	public XIMSound getSound()
	{
		return sound;
	}

	public void setSound(XIMSound sound)
	{
		this.sound = sound;
	}
}
