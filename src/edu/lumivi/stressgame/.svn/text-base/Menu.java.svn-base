package edu.lumivi.stressgame;
import processing.core.PApplet;
import controlP5.ControlEvent;
import controlP5.ControlP5;

public class Menu {

	ControlP5 controlP5;
	PApplet p;

	public int myColorRect = 200;
	public int myColorBackground = 100; 
	
	static int controlWidth = 100; 
	static int controlHeight = 20; 
	static int controlOffsetx = 10; 
	static int controlOffsety = 22; 

	public Menu(PApplet p) {

		this.p = p;
		controlP5 = new ControlP5(p);

		// addSlider(theName, theMin, theMax, theDefaultValue, theX, theY, theW,
		// theH);
		// addSlider(theName, theMin, theMax, theX, theY, theWidth, theHeight); 
		int controlPosx = p.width - controlWidth - controlOffsetx; 
	    int controlPosy = 0; 

		controlP5.addButton("New", 1, controlPosx, controlPosy + controlOffsety * 1, controlWidth / 2, controlHeight).setId(1); 
		controlP5.addButton("Start", 1, controlPosx + controlWidth / 2, controlPosy + controlOffsety * 1, controlWidth / 2, controlHeight).setId(2); 

		//controlP5.addSlider("r", 0, 255, 100, controlPosx, controlPosy + controlOffsety * 1, controlWidth, controlHeight).setId(3);
		//controlP5.addSlider("g", 0, 255, 100, controlPosx, controlPosy + controlOffsety * 2, controlWidth, controlHeight).setId(4);
		//controlP5.addSlider("b", 0, 255, 100, controlPosx, controlPosy + controlOffsety * 3, controlWidth, controlHeight).setId(5);
		
		controlP5.addTextfield("Comment", controlPosx, controlPosy + controlOffsety * 2, 100, 100).setId(3); 
		
		controlP5.addButton("Submit", 2, controlPosx, 100 + controlPosy + controlOffsety * 3, controlWidth, controlHeight).setId(4); 
		
		controlP5.addTextlabel("qq", "qq2", controlPosx, 500); 


	}

	public void render() {
		p.pushMatrix();
		p.fill(0, 0, 255, 100);
		p.translate(200, 0);

		p.popMatrix();

	}

	void controlEvent(ControlEvent theEvent) {
		p.println("got a control event from controller with id "
				+ theEvent.controller().id());
		switch (theEvent.controller().id()) {
		case (1):
			myColorRect = (int) (theEvent.controller().value());
			break;
		case (2):
			myColorBackground = (int) (theEvent.controller().value());
			break;
		}
	}

}
