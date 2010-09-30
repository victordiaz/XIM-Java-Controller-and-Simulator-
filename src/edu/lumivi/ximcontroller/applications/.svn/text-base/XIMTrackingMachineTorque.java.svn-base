package edu.lumivi.ximcontroller.applications;

import processing.core.PApplet;


import de.bezier.data.sql.MySQL;
import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMTracking;

public class XIMTrackingMachineTorque extends XIMTrackingReader
{

	// 192.168.1.246
	// 11987

	MySQL connection;

	String host = "192.168.1.220";
	String port = "3306";

	String username = "pclub";
	String password = "globo2002";
	String dbName = "VisitorDB";
	String tableName = "location";

	public XIMTrackingMachineTorque(PApplet applet, XIMMachine m, int ui)
	{
		super(applet, m, ui);
		this.connection = new MySQL(this.p, this.host, this.dbName,
				this.username, this.password);
	}

	// acceso a la bd
	public void update()
	{
		XIMTracking t = this.getTracking();

		if (this.connection.connect())
		{
			this.connection.query("SELECT * FROM " + this.tableName);

			if (this.connection.next())
			{

				t.setX(this.connection.getFloat("pos_x"));
				t.setY(this.connection.getFloat("pos_y"));

				p.println("pos_x: " + this.connection.getFloat("pos_x"));
				p.println("pos_y: " + this.connection.getFloat("pos_y"));
			}

		} else
		{
			System.out.println("Connection failure!");
		}
	}
}
