
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Junction extends Point {

	private int[][] roadIn = new int[30][2];
	private int[][] roadOut = new int[30][2];
	private int i=0;
	private int rOut_i;
	private int rIn_i;
	private String[] roadOutNames = new String[30];
	private String[] roadInNames = new String[30];
	private ArrayList<Integer> passengersIDs = new ArrayList<Integer>();
	private final int maxX = 800;
	private final int maxY = 600;
		
	public Junction() {
		createJ();
	}
		
	public Junction(double new_x, double new_y) {
		createSpecificP (new_x, new_y);
	}
		
	@Override
	public double[] createP (double[] coordinates) {
		return coordinates;
	}
		
	public void createJ() {
		id = ++id_global;
		Random rand = new Random();
		setX(rand.nextInt(799) + rand.nextDouble());
		setY(rand.nextInt(599) + rand.nextDouble());
		System.out.printf("Creating Junction " + getID() + " at Point (%.2f, %.2f)\n",getX(), getY());
	}
		
	@Override
	public void createSpecificP (double new_x, double new_y) {
		Random rand = new Random();
		id = ++id_global;
		
		try {
			isPointValidX(new_x);
			setX(new_x);
			}
		catch (Exception e){
			setX(rand.nextInt(maxX) + rand.nextDouble());
			System.out.println(new_x + e.getMessage() + getX());
		}
	
		try {
			isPointValidY(new_y);
			setY(new_y);
			}
		catch (Exception e){
			setY(rand.nextInt(maxY) + rand.nextDouble());
			System.out.println(new_y + e.getMessage() + getY());
		}
		System.out.printf("Creating Junction " + id + " at Point (%.2f, %.2f)\n",getX(), getY());
	}
		
	public void setExitingRoads(Junction J1, Junction J2) {
		 {
			// Starting junction
			roadOut[rOut_i][0] = J1.getID();
			// Ending junction
			roadOut[rOut_i][1] = J2.getID();
			roadOutNames[rOut_i] = "Road from Junction " + Integer.toString(roadOut[rOut_i][0]) + " to Junction " + roadOut[rOut_i][1];
			rOut_i++;
		}
	}
		
	public void setEnteringRoads(Junction J1, Junction J2) {
		 {
			// Starting Junction
			roadIn[rIn_i][0] = J1.getID();
			// Ending Junction
			roadIn[rIn_i][1] = J2.getID();
			roadInNames[rIn_i] = "Road from Junction " + Integer.toString(roadIn[rIn_i][0]) + " to Junction " + roadIn[rIn_i][1];
			rIn_i++;
		}
	}
		
	public String getExitingRoads() {
		String[] str2 = new String[rOut_i];
		for (i=0 ; i < rOut_i ; i++) {
			str2[i] = roadOutNames[i];
		}
		return Arrays.toString(str2);
	}
		
	public int[][] get_roadOut() {
		return roadOut;
	}
		
	public int[][] get_roadIn() {
		return roadIn;
	}
		
	public String getEnteringRoads() {
		String[] str2 = new String[rIn_i];
		for (i=0 ; i < rIn_i ; i++) {
			str2[i] = roadInNames[i];
		}
		return Arrays.toString(str2);
	}
		
	public String[] getEnteringRoadsNames() {
		return roadInNames;
	}
		
	public String getSpecificEnteringRoads(int n) {
		return roadInNames[n];
	}
		
	public String getSpecificExitRoads(int n) {
		return roadOutNames[n];
	}
		
	public int getCurrent_rIn_i() {
		return rIn_i;
	}
		
	public int getCurrent_rOut_i() {
		return rOut_i;
	}
		
	@Override
	public String toString() {
		return Integer.toString(id);
	}
	
	public void addPassengerID (int pID) {
		passengersIDs.add(pID);
	}
	
	public void removePassengerID () {
		passengersIDs.remove(0);
	}
	
	public ArrayList<Integer> getPassengersIDs () {
		return passengersIDs;
	}
	
	public int getID() {
		return id;
	}	
}
