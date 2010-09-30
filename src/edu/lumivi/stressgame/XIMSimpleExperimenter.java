package edu.lumivi.stressgame;

import processing.core.PApplet;
import processing.core.PImage;
import edu.lumivi.ximcontroller.applications.Follower;
import edu.lumivi.ximcontroller.applications.XIMFloorMachine;
import edu.lumivi.ximcontroller.applications.XIMTrackingMachineUDP;
import edu.lumivi.ximcontroller.applications.XIMTrackingMouse;
import edu.lumivi.ximcontroller.applications.XIMTrackingReader;
import edu.lumivi.ximcontroller.devices.XIMFloor;
import edu.lumivi.ximcontroller.devices.XIMLargeScreen;
import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMSound;
import edu.lumivi.ximcontroller.devices.XIMTracking;

public class XIMSimpleExperimenter extends PApplet
{

	private static final long serialVersionUID = 200911032120L;

	PImage logo;

	// views
	XIMFloor f;
	XIMLargeScreen ls;
	XIMTracking t;
	XIMSound sp;
	XIMMachine xim;

	XIMTrackingMachineUDP tx;
	XIMTrackingMouse tm;
	XIMTrackingReader tr; 
	
	
	XIMFloorMachine fx;

	Menu m;

	Follower appFollower;
	StressChecker1 appStress1;

	public void setup()
	{
		size(650, 400);

		smooth();
		background(255);

		logo = loadImage("./lumivi.png");

		// views
		f = new XIMFloor(this);
		ls = new XIMLargeScreen(this);
		t = new XIMTracking(this); 
		sp = new XIMSound(this); 
		xim = new XIMMachine(this, f, t, ls, sp);
		m = new Menu(this);

		fx = new XIMFloorMachine(this, xim, 10);
		tx = new XIMTrackingMachineUDP(this, xim, 10);
		tm = new XIMTrackingMouse(this, xim, 200); 
		

//		tr = tm;
		tr = tx;

		frameRate(32);

		appFollower = new Follower(this, xim, this.color(100, 200, 50));
		appStress1 = new StressChecker1(this, xim, 200, 30);
		initialize();

	}

	void initialize()
	{

	}

	public void draw()
	{
		background(0);

		tr.execute();

		appStress1.execute();
		appFollower.execute();

		// prueba mapeo
		// f.setTile(2, 5, color(255, 0, 0));
		// f.setTile(18, color(255, 0, 0));
		
		//this.clearFloor();
		
		f.render(); // floor
		ls.render(); // screen
		
		
		fx.execute(); 

		f.setOffTiles();
		logo();

	} 
	
	public void clearFloor() { 
		f.setTiles(0);
		
	}

	public void logo()
	{
		pushMatrix();
		translate(520, 340);
		scale((float) 0.5);
		image(logo, 0, 0);
		popMatrix();

	}

	public void keyPressed()
	{
		this.appStress1.keyPressed(key);
	}

}
