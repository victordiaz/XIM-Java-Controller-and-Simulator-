package edu.lumivi.ximcontroller.applications;

import processing.core.PApplet;

import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMTracking;

public abstract class XIMTrackingReader extends XIMConnector
{
	
	public XIMTrackingReader(PApplet p, XIMMachine m, int ui)
	{
		super(p, m, ui);
	}
}
