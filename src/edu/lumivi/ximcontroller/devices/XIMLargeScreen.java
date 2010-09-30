package edu.lumivi.ximcontroller.devices;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;

public class XIMLargeScreen {

	PApplet p; 

	OscP5 oscP5; 	

	String screenAddress2 = "localhost"; 
	
	String screenAddress1 = "192.168.1.32"; 
	//String screenAddress2 = "192.168.1.33"; 
	String screenAddress3 = "192.168.1.34"; 
	String screenAddress4 = "192.168.1.35"; 
	
	NetAddress[] screenOscAddress; 
	

	XIMSingleScreen[] screens; 
	int xPos = 0;
	int yPos = 100;
	int widthSize = 200;
	int heightSize = 100;
	int xOffset = 0;
	int yOffset = 1;

	public XIMLargeScreen(PApplet p) {

		this.p = p; 

		this.screens = new XIMSingleScreen[4]; 

		for (int i = 0; i < 4; i++) {
			screens[i] = new XIMSingleScreen(p, (xPos + xOffset),
					(yPos + yOffset) * i, widthSize, heightSize);
		} 
		
		this.oscP5 = new OscP5(this.p, 12001);
		
		screenOscAddress = new NetAddress[4];
		
		screenOscAddress[0] = new NetAddress(screenAddress1, 12000); 
		screenOscAddress[1] = new NetAddress(screenAddress2, 12000); 
		screenOscAddress[2] = new NetAddress(screenAddress3, 12000); 
		screenOscAddress[3] = new NetAddress(screenAddress4, 12000); 
		

		initialize(); 
	}

	public void initialize() {

	}

	public void calculate() {

		for (int i = 0; i < 4; i++) {
			screens[i].calculate();

		}

	}

	public void render() {

		for (int i = 0; i < 4; i++) {
			screens[i].render();

		}

	} 
	
	public void setColor(int color) { 
		for (int i = 0; i < 4; i++)
		{
			screens[i].setMainColor(color);
		} 
	} 
	
	
	public void setFrequency(float freq) { 
		for (int i = 0; i < 4; i++)
		{
			screens[i].setFadingFrequency(freq); 
		} 
				
	} 
	
	public void setMessage(String message) { 
		for (int i = 0; i < 4; i++)
		{
			screens[i].setMessage(message);
		} 
	} 
	
	public void sendColors() { 
		
		for (int i = 0; i < 4; i++) { 
			
			  OscMessage myMessage = new OscMessage("/videoColor");
			  myMessage.add(screens[i].getMainColor()); /* add an int to the osc message */ 
			  myMessage.add(screens[i].getFadingFrequency()); 

			  oscP5.send(myMessage, screenOscAddress[i]); 
			
		}
	} 
	
	public void sendMessage() { 
		
		for (int i = 0; i < 4; i++) { 
			
			  OscMessage myMessage = new OscMessage("/message");
			  myMessage.add(screens[i].getMessage()); 
			  oscP5.send(myMessage, screenOscAddress[i]); 
			
		}
	} 
	
	public void clean()
	{
		this.setColor(p.color(255,255,255)); 
		this.setFrequency((float) 0.1); 
		this.setMessage("");
		this.sendColors(); 
		this.sendMessage();
	}
	
	public XIMSingleScreen getSingleScreen(int singleScreen) {
		
		return this.screens[singleScreen]; 
		
	} 

}
