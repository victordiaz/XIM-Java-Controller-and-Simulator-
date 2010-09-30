package edu.lumivi.ximcontroller.applications;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import processing.core.PApplet;

import edu.lumivi.ximcontroller.devices.XIMMachine;

public class XIMTrackingMachineUDP extends XIMTrackingReader implements
		Runnable
{

	// 192.168.1.246
	// 11987

	DatagramSocket socket;
	Thread trunner;

	String host = "192.168.1.246";
	String port = "11987";

	float x, y;

	public XIMTrackingMachineUDP(PApplet applet, XIMMachine m, int ui)
	{
		super(applet, m, ui);

		try
		{
			socket = new DatagramSocket(11987);
			// System.out.println("socket: "+ socket.toString());
		} catch (SocketException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace(); 
			System.out.println("Cannot connect to floor"); 
		}

		this.trunner = new Thread(this);
		this.trunner.start();
	}

	// acceso a la bd
	public void update()
	{
		getTracking().setX(x);
		getTracking().setY(y);

	} 
	

	public void run()
	{
		while (true)
		{

			// System.out.println("running");
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);

			try
			{
				// System.out.println("qq"); 
				socket.receive(packet);
				// System.out.println(packet.getData().toString());

				String msg = new String(packet.getData());
				//System.out.println(msg);

				String[] msgCoords = msg.split(",");

				if (msgCoords.length > 1)
				{
					x = Float.parseFloat(msgCoords[1]); 
					y = Float.parseFloat(msgCoords[2]); 
					
//					System.out.println(x); 
//					System.out.println(y); 

				}

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				// e.printStackTrace(); 
				
				System.out.println("Cannot connect to Tracking"); 
			} 

		}

	}
}
