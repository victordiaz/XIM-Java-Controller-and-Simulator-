package edu.lumivi.stressgame;

import processing.core.PApplet;
import edu.lumivi.ximcontroller.applications.XIMApplication;
import edu.lumivi.ximcontroller.devices.XIMMachine;
import edu.lumivi.ximcontroller.devices.XIMSound;

public class StressChecker1 extends XIMApplication
{

	// GAME MODES
	public static final int WAITING = 1;
	public static final int PLAYING = 2;
	public static final int FINISHED = 3;

	// PLAYING MODES
	public static final int TRAINING = 1;
	public static final int NOSTRESS = 2;
	public static final int STRESS = 3;
	
	PApplet applet = this.getApplet();
	
	Recorder r;
	
	int[][] targets;

	int currentTarget;
	float changeTime;
	float transitionTime;

	int targetColor = this.getApplet().color(255, 255, 255);

	boolean playing;
	boolean inTransition;

	int status;
	int mode;

	String transMessage;

	int score;
	int reached;
	
	float timeStart;
	float timeLimit;
	float time; 
	
	String screenMessage; 

	int tempX, tempY, tempC;

	public StressChecker1(PApplet applet, XIMMachine m, int[][] targets,
			float timeLimit)
	{
		super(applet, m);
		this.targets = targets;
		this.timeLimit = timeLimit * 1000;
		this.initialize();
	}

	public StressChecker1(PApplet applet, XIMMachine m, int numTargets,
			float timeLimit)
	{
		super(applet, m);

		// CHASTA!!
		applet.randomSeed(987654321);

		this.generateTargets(numTargets, 5);
		this.timeLimit = timeLimit * 1000;
		this.initialize();
	}

	private void initialize()
	{
		this.transitionTime = 5000;
		this.screenMessage = "";
		
		r = new Recorder(applet, this.getMachine(), "records/test.db");
		
		this.setStatus(WAITING);
		//this.r.setRecording(false);
	}

	public int getStatus()
	{
		return status;
	}

	private void setStatus(int status)
	{
		switch (status)
		{
		case WAITING:
			this.currentTarget = 0;
			r.insertSubject();
			this.setPlaying(false);
			
			this.getScreen().clean();
			
			break;
		case PLAYING:
			this.setMode(TRAINING);
			this.setPlaying(true);
			break;
		case FINISHED:
			this.setPlaying(false);
			this.getScreen().clean();
			getSound().stop(); 

			
			break;
		default:
			this.setStatus(WAITING);
		}

		this.timeStart = applet.millis();
		this.setInTransition(true);
		this.status = status;
	}

	public int getMode()
	{
		return mode;
	}

	public void setMode(int mode)
	{
		
		switch (mode)
		{
		case TRAINING:
			// CONDICIONES DE TRAINING... 
			this.screenMessage = "Training"; 
			//getSound().play(XIMSound.STRESS); 
			this.getScreen().setColor(applet.color(255, 255, 255)); 
			this.getScreen().setFrequency((float) 0.1); 
			this.getScreen().sendColors(); 
			
			break;
			
		case NOSTRESS:
			// CONDICIONES DE NO STRESS...
			this.screenMessage = "Game 1"; 
			//getSound().play(XIMSound.STRESS); 
			this.getScreen().setColor(applet.color(0, 0, 255)); 
			this.getScreen().setFrequency((float) 0.1); 			
			this.getScreen().sendColors(); 

			break;
			
		case STRESS:
			// CONDICIONES DE ESTRES... 
			this.screenMessage = "Game 2"; 
			getSound().play(XIMSound.STRESS); 
			this.getScreen().setColor(applet.color(255, 0, 0)); 
			this.getScreen().setFrequency((float) 1); 
			this.getScreen().sendColors(); 
			
			break;
			
		default:
			this.setMode(TRAINING);
		}
		
		this.mode = mode;
		this.resetSession();
		r.insertSession(this.mode);
		this.setInTransition(true);
	}

	public boolean isPlaying()
	{
		return playing;
	}

	public void setPlaying(boolean playing)
	{
		this.playing = playing;
	}

	public boolean isInTransition()
	{
		return inTransition;
	}

	public void setInTransition(boolean inTransition)
	{
		this.inTransition = inTransition;
	}

	private void resetSession()
	{
		this.score = 0; 
		this.reached = 0;
		
		this.changeTime = 2500;
		this.timeStart = applet.millis();
	}

	@Override
	public void execute()
	{
		if (!inTransition)
		{
			switch (status)
			{
			case WAITING:
				loopWaiting();
				break;
			case PLAYING:
				loopPlaying();
				break;
			case FINISHED:
				loopFinished();
				break;
			}
		} else
		{
			this.loopTransition(); 
		}

	}


	private void loopWaiting()
	{
		// Waiting to start
		if (applet.millis() - time > 1500)
		{
			time = applet.millis();

			tempX = (int) applet.random(8);
			tempY = (int) applet.random(9);
			tempC = (int) applet.color(applet.random(255), applet.random(255),
					applet.random(255));
		}

		this.getFloor().setTile(tempX, tempY, tempC);
	}

	private void loopPlaying()
	{
		// Playing

		if (this.isPlaying())
		{
			if (applet.millis() - timeStart < this.timeLimit)
			{
				if (applet.millis() - time > changeTime)
				{
					time = applet.millis();
					this.targetFailed();
					if (!this.nextTarget())
						this.advance();
				}

				if (this.isTargetReached())
				{
					time = applet.millis();
					this.targetReached();
					if (!this.nextTarget())
						this.advance();
				}
				
				r.recordMovement(this.getTracking().getTileX(), this.getTracking().getTileY());

			} else
			{
				r.insertScore(score, reached);
				r.insertMovements();
				this.advance();
			}
		}
		
		if (this.getMode() == STRESS)
		{
			this.getFloor().setTiles(applet.color(255, 255, 0));
		}
		
		int x = targets[currentTarget][0];
		int y = targets[currentTarget][1];

		this.getFloor().setTile(x, y, this.targetColor);
	}

	private void loopFinished()
	{
		// Game finished
		if (applet.millis() - time > 1500)
		{
			time = applet.millis();

			tempC = (int) applet.color(applet.random(255), applet.random(255),
					applet.random(255));
		}

		this.getFloor().setTiles(tempC);
	}

	public void loopTransition()
	{
		if (applet.millis() - timeStart < transitionTime)
		{ 
			
			this.getFloor().setTiles(this.targetColor); 
			this.getScreen().setMessage(this.screenMessage); 
			this.getScreen().sendMessage(); 
			
//			int[] colors =
//			{ applet.color(255, 255, 255), applet.color(0, 0, 0) };
//
//			if (applet.millis() - time > 250)
//			{
//				time = applet.millis();
//
//				if (this.tempC == colors[0])
//					tempC = colors[1];
//				else
//					tempC = colors[0];
//			} 

			this.getFloor().setTiles(targetColor);
		} else
		{
			this.setInTransition(false); 
			this.getScreen().setMessage(""); 
			this.getScreen().sendMessage(); 
			this.timeStart = applet.millis();
		}
	}

	private boolean isTargetReached()
	{
		// System.out.println("Coords: " + this.getTracking().getTileX() + ", "
		// + this.getTracking().getTileY());
		// System.out.println("Target: " + this.targets[currentTarget][0] + ", "
		// + this.targets[currentTarget][1]);

		if (this.getTracking().getTileX() == this.targets[currentTarget][0]
				&& this.getTracking().getTileY() == this.targets[currentTarget][1])
		{
			return true;
		} else
		{
			return false;
		}
	}

	private void targetReached()
	{
		getSound().play(XIMSound.OK); 
		this.reached++;
		this.score += 100 + (this.reached * 5);
	}

	private void targetFailed()
	{ 
		getSound().play(XIMSound.FAIL); 
		// QUE PASA SI FALLA...
	}

	private boolean nextTarget()
	{
		if (this.currentTarget < this.targets.length - 1)
		{
			this.currentTarget++;
			this.changeTime -= 8;
			return true;
		} else
		{
			return false;
		}
	}

	private void advance()
	{
		if (status == PLAYING)
		{
			if (mode == TRAINING)
				this.setMode(NOSTRESS);
			else if (mode == NOSTRESS)
				this.setMode(STRESS);
			else
				this.setStatus(FINISHED);
		}
	}

	private int[][] generateTargets(int n)
	{
		this.targets = new int[n][2];

		for (int i = 0; i < n - 1; i++)
		{
			this.targets[i][0] = (int) applet.random(8);
			this.targets[i][1] = (int) applet.random(9);
		}

		return targets;
	}

	private int[][] generateTargets(int nTargets, int maxDistance)
	{
		this.targets = new int[nTargets][2];

		targets[0][0] = 4;
		targets[0][1] = 4;

		for (int i = 1; i < nTargets; i++)
		{
			int dX;
			int dY;
			int newX;
			int newY;
			boolean good = false;

			do
			{
				dX = (int) applet.random(-maxDistance, maxDistance);
				newX = targets[i - 1][0] + dX;
				if (newX > 0 && newX < 8)
					good = true;
			} while (good == false);

			good = false;

			do
			{
				dY = (int) applet.random(-maxDistance, maxDistance);
				newY = targets[i - 1][1] + dY;
				if (newY > 0 && newY < 9)
					good = true;
			} while (good == false);

			targets[i][0] = newX;
			targets[i][1] = newY;

			// System.out.println("Target: " + newX + ", " + newY);
		}

		return targets;
	}

	public void keyPressed(char key)
	{
		if (key == 'r')
			this.setStatus(WAITING); 
		if (key == 's')
			this.setStatus(PLAYING);
	}
}
