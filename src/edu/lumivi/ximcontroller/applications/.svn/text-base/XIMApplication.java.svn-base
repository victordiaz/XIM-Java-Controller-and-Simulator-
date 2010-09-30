package edu.lumivi.ximcontroller.applications;

import processing.core.PApplet;

import edu.lumivi.ximcontroller.devices.XIMFloor;
import edu.lumivi.ximcontroller.devices.XIMLargeScreen;
import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMSound;
import edu.lumivi.ximcontroller.devices.XIMTracking;

public abstract class XIMApplication
{
	PApplet applet;
	
	XIMMachine machine;
	XIMFloor floor;
	XIMTracking tracking;
	XIMLargeScreen screen; 
	XIMSound sound; 
	
	public XIMApplication(PApplet applet, XIMMachine machine)
	{
		this.applet = applet;
		
		this.machine = machine;
		this.floor = machine.getFloor();
		this.tracking = machine.getTracking();
		this.screen = machine.getScreen(); 
		this.sound = machine.getSound(); 
	}

	public XIMMachine getMachine()
	{
		return machine;
	}

	public void setMachine(XIMMachine machine)
	{
		this.machine = machine;
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

	public PApplet getApplet()
	{
		return applet;
	}
	
	public abstract void execute();
	
	public void keyPressed(char key)
	{
	}
}
