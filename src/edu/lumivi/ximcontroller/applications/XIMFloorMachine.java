package edu.lumivi.ximcontroller.applications;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import processing.core.PApplet;

import edu.lumivi.ximcontroller.devices.XIMFloor;
import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMTile;

public class XIMFloorMachine extends XIMConnector
{
	byte[] buf;
	DatagramSocket socket;
	DatagramPacket packet;
	InetAddress address;
	String floorAddress;
	int floorPort;

	int packetsSent = 0;

	// DecimalFormat df1 = new DecimalFormat( "#,###,###,##0.0" );

	public XIMFloorMachine(PApplet p, XIMMachine m, int ui)
	{
		super(p, m, ui);

		floorAddress = "192.168.1.40";
		floorPort = 42419;

		try
		{
			address = InetAddress.getByName(floorAddress);
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
	

	private void makePacket()
	{
		XIMFloor f = this.getFloor();
		
		String content = "";

		for (int i = 0; i < 72; i++)
		{
			XIMTile t = f.getTile(i);

			float rfloat = t.getRed();
			float gfloat = t.getGreen();
			float bfloat = t.getBlue();

			rfloat = rfloat / 255;
			gfloat = gfloat / 255;
			bfloat = bfloat / 255;

			String r = Float.toString(rfloat).substring(0, 5 - 2);
			String g = Float.toString(gfloat).substring(0, 5 - 2);
			String b = Float.toString(bfloat).substring(0, 5 - 2);

			// Float(df1.format(r)).floatValue();
			// g = new Float(df1.format(g)).floatValue();
			// b = new Float(df1.format(b)).floatValue();

			content += r + " " + g + " " + b + " ";
		}

		content = content.trim();

		// p.println(content);

		buf = content.getBytes();

		packet = new DatagramPacket(buf, buf.length, address, floorPort);
		// packet = new DatagramPacket(buf, buf.length, address,
		// this.packetsSent);
	}

	public void update()
	{
		// iterable
		// formato UDP 72 triplets r g b 0..1 0..1 0..1

		this.packetsSent++;
		this.makePacket();

		try
		{
			socket = new DatagramSocket();
			socket.send(packet);
		} catch (IOException ioe)
		{
			//System.out.println("Cannot connect to the floor");
			//ioe.printStackTrace();
		}

	}
}
