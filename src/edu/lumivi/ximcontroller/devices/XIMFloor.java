package edu.lumivi.ximcontroller.devices;


import processing.core.PShape;


import edu.lumivi.stressgame.XIMSimpleExperimenter;
import edu.lumivi.ximcontroller.applications.XIMFloorMachine;

public class XIMFloor {

	XIMSimpleExperimenter p;
	PShape imgTile;
	
	XIMTile[] tiles;
	XIMTile[][] mTiles;
	
	int numRows = 9;
	int numColumns = 8;
	int numTiles = 72;
	
	//Movie myMovie;
	
	public XIMFloor(XIMSimpleExperimenter p) {
		this.p = p;
		imgTile = p.loadShape("./ximfloor.svg"); 
		

		initialize();
	}

	public void initialize() {
		this.tiles = new XIMTile[numTiles];
		
		this.mTiles = new XIMTile[numColumns][numRows];
		
		int row = 0;
		int column = numColumns;
		boolean up = false;
		
		for (int i = 0; i < numTiles; i++)
		{
			if (column == 0 && up == false)
			{
				up = true;
				row++;
			}
			else if (column == this.numColumns - 1 && up == true)
			{
				up = false;
				row++;
			}
			else
			{
				if (up)
				{
					column++;
				}
				else
				{
					column--;
				}
			}
			
			//System.out.println(up + " " + column + " " + row);
			
			XIMTile t = new XIMTile(p, imgTile, i + 1);
			
			tiles[i] = t;
			mTiles[column][row] = t;
			
		}
		
		//TODO: user observers
		//this.notifyObservers();
	}

	public void calculate() {

	}

	public void render() {
		
		//dibuja en la pantalla 
		p.pushMatrix();
		p.translate(205, 0);
		p.scale((float) 0.5);
		
		for (int i = 0; i < numTiles; i++) {
			//tiles[i].setColor(p.getWsc().); 
			tiles[i].render();
		} 

		p.popMatrix(); 
		//fin dibuja en la pantalla 
		

	} 
	
	public void send() { 
		//transformacion del array y colores a 
		//0.1 0.2 0.8 ......
		
		
	}
	
	public void setTile(int num, int color) {
		tiles[num].setColor(color);
	}
	
	public void setTile(int x, int y, int color)
	{
		mTiles[x][y].setColor(color);
	}
	
	public void setOffTiles()
	{
		this.setTiles(p.color(0));
	}
	
	public void setTiles(int color)
	{
		for (int i = 0; i < numTiles; i++)
		{
			tiles[i].setColor(color); 
		}
		
	}
	
	public XIMTile getTile(int tileNumber)
	{
		return this.tiles[tileNumber];
	}
}
