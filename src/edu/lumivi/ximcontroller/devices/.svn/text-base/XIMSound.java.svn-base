package edu.lumivi.ximcontroller.devices;

import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class XIMSound
{

	Minim minim;
	AudioPlayer fail, ok, stress, nostress; 

	public final static int FAIL = 0; 
	public final static int OK = 1; 
	public final static int NOSTRESS = 2; 
	public final static int STRESS = 3; 	

	public XIMSound(PApplet p)
	{

		minim = new Minim(p);

		fail = minim.loadFile("./fail.wav"); 
		ok = minim.loadFile("./ok.wav"); 		
		stress = minim.loadFile("./ambient_noise.wav"); 
		//nostress = minim.loadFile("./noestress.mp3"); 


	}

	public void play(int typeSound) { 
		
		switch (typeSound)
		{
		case 0:
			fail.play(); 
			fail.rewind(); 
			break; 
			
		case 1: 
			ok.play(); 
			ok.rewind(); 
			break; 
			
		case 2: 
			//nostress.play(); 
			break; 
			
		case 3: 
			stress.play(); 
			break; 

		default:
			break;
		} 
		
	}	
	
	public void stop() {
		//nostress.close(); 
		stress.pause(); 
		stress.rewind(); 
		//stress.close(); 
		
	}

}
