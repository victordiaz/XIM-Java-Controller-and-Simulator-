package edu.lumivi.ximcontroller.applications;
import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;


// TODO: Make it a real app!

public class XIMOsc { 
	
	OscP5 oscP5; 
	
	
	String screenAddress1 = "localhost"; 

	
	//String screenAddress1 = "192.168.1.32"; 
	String screenAddress2 = "192.168.1.33"; 
	String screenAddress3 = "192.168.1.34"; 
	String screenAddress4 = "192.168.1.35"; 
	
	NetAddress[] screen; 
	
	
	public XIMOsc() { 
		
		screen = new NetAddress[4];
		
		screen[0] = new NetAddress(screenAddress1, 12000); 
		screen[1] = new NetAddress(screenAddress2, 12000); 
		screen[2] = new NetAddress(screenAddress3, 12000); 
		screen[3] = new NetAddress(screenAddress4, 12000); 
		
		
	} 
	
	
	public void send(int screenNum, int screenColor, int fadeFrequency, String msg) { 
		
		  /* in the following different ways of creating osc messages are shown by example */
		  OscMessage myMessage = new OscMessage("/screen");
		  myMessage.add(screenColor); /* add an int to the osc message */ 
		  myMessage.add(msg); 
		  myMessage.add(fadeFrequency); 

		  
		  /* send the message */
		  oscP5.send(myMessage, screen[screenNum]); 
		
	}
	
}
