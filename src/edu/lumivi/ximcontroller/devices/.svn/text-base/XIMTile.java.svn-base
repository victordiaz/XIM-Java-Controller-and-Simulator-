package edu.lumivi.ximcontroller.devices;
import java.awt.Color;

import processing.core.PApplet;
import processing.core.PShape;

public class XIMTile {

	PApplet p;
	PShape imgTiles;
	PShape tile;
	int numTile;

	int colorTile;
	int r, g, b; 
	
	Color c;
	
	//XIMFloorTile[] neighbours;
	

	public XIMTile(PApplet p, PShape imgTiles, int numTile) {
		this.p = p;
		this.imgTiles = imgTiles;
		this.numTile = numTile; 
		//this.neighbours = new XIMFloorTile[6];


		tile = imgTiles.getChild("" + numTile);
		tile.disableStyle();

		initialize();
	}

	public void initialize() {
		this.setColor(100, 100, 100);
	}

	public void calculate() {

	}

	public void render() {
		tile.disableStyle();
		//p.println(colorTile);

		p.stroke(100); 
		p.strokeWeight(2); 
		p.fill(colorTile);
		p.shape(tile);

	}

	public void setColor(int colorTile) {
		this.colorTile = colorTile; 
		this.c = Color.decode(String.valueOf(this.getColor()));

	} 
	
	public void setColor(int r, int g, int b) {
		this.colorTile = p.color(r, g, b);
		this.c = Color.decode(String.valueOf(this.getColor()));
	}
	
	public int getRed()
	{
		return c.getRed();
	}
	
	public int getGreen()
	{
		return c.getGreen();
	}
	
	public int getBlue()
	{
		return c.getBlue();
	}
	
	public int getColor()
	{
		return this.colorTile;
	}

}
