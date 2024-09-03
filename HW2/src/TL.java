
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

import java.util.Random;

class TL {
	private int delay;
	private final int minDelay = 2;
	private final int maxDelay = 4;
	
	public TL() {
		setDelay();
	}
	
	private void setDelay() {
		Random rand = new Random();
		delay = minDelay + rand.nextInt(maxDelay-minDelay+1);
	}
	
	public int getDelay() {
		return delay;
	}
}

class SequentialTL extends TL implements Dynamic{
	private int TL_num = 0;
	private int pulseCounter = 0;
	private int JunctionID;
	private String[] roadNames;
	private int numOfEnteringRoads;
	private String currentGreen;
	
	SequentialTL(Junction J) {
		JunctionID = J.getID();
		roadNames=J.getEnteringRoadsNames();
		numOfEnteringRoads = J.getCurrent_rIn_i();
	}
	
	
	public void singleMove() {
		if (pulseCounter < getDelay()-1) {
			pulseCounter++;
		}
		else
		{
			System.out.println("Sequential TrafficLights Junction " + JunctionID + " delay= " + getDelay() + ": green light on " + roadNames[TL_num]);
			currentGreen = roadNames[TL_num];
			TL_num++;
			if (TL_num == numOfEnteringRoads) {
				TL_num = 0;
			}
			pulseCounter=0;
		}
	}
	
	public String getCurrentGreen() {
		return currentGreen;
	}
	
	public int getJunctionID() {
		return JunctionID;
	}
}

class RandomTL extends TL implements Dynamic{
	private int TL_num = 0;
	private int pulseCounter = 0;
	private int JunctionID;
	private String[] roadNames;
	private int numOfEnteringRoads;
	private String currentGreen;
	
	RandomTL(Junction J) {
		JunctionID = J.getID();
		roadNames=J.getEnteringRoadsNames();
		numOfEnteringRoads = J.getCurrent_rIn_i();
	}
	
	
	public void singleMove() {
		Random rand = new Random();
		
		if (pulseCounter < getDelay()-1) {
			pulseCounter++;
		}
		else
		{
			System.out.println("Random TrafficLights Junction " + JunctionID + " delay= " + getDelay() + ": green light on " + roadNames[TL_num]);
			currentGreen = roadNames[TL_num];
			if (numOfEnteringRoads > 1) {
				TL_num = rand.nextInt(numOfEnteringRoads-1);
			}
			pulseCounter = 0;
		}	
	}
	
	public String getCurrentGreen() {
		return currentGreen;
	}
	
	public int getJunctionID() {
		return JunctionID;
	}
	
}
