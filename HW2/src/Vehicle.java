
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

import java.util.ArrayList;
import java.util.Random;

public class Vehicle implements Dynamic {
	private int speed;
	private final int minSpeed = 30;
	private final int maxSpeed = 120;
	public static int Vehicle_id_global = 0;
	private int id = 0;
	private double timeOnRoad = 0;
	private Road currRoad;
	private int currRoadIndx = 0;
	private Map map;
	private ArrayList<Road> vehiclePath = new ArrayList<Road>();
	private Boolean vehicleArrived = false;
	private int passengerID = 0;
	private int arrivedJunctionID = 0;
	
	public Vehicle (Map map1) {
		setSpeed();
		setID();
		map = new Map(map1.getJunctions(), map1.getRoads(), map1.getS_TL(), map1.getR_TL());
		map.creatRandomPath();
		vehiclePath = map.getRandomPathRoads();
		currRoad = vehiclePath.get(0);
		System.out.println("Creating Vehicle " + getID() + ",speed: " + getSpeed() + ", path: " + map.getrandomPathString());
	}
	
	private void setSpeed() {
		Random rand = new Random();	
	
		speed = minSpeed + rand.nextInt(maxSpeed-minSpeed+1);
		}
	
	public int getSpeed() {
		return speed;
	}
	
	private void setID() {
		id = ++Vehicle_id_global;
		}
	
	public int getID() {
		return id;
	}
	
	public void singleMove() {
		String currentGreen = null;
		Boolean nextF = false;
		
		if (currRoadIndx < (vehiclePath.size())) {
			currRoad = vehiclePath.get(currRoadIndx);
			if ((speed*timeOnRoad)< currRoad.getLength()) {
				System.out.println("Vehicle " + getID() + " is moving on the " + currRoad.getName());
				timeOnRoad++;
			}
			else {
				currentGreen = null;
				if (currRoad.getTLJunctionID() != 0) {
					if (getSTL(currRoad.getTLJunctionID()) != null) {
						currentGreen = getSTL(currRoad.getTLJunctionID()).getCurrentGreen();
					}
					else if (getRTL(currRoad.getTLJunctionID()) != null) {
						currentGreen = getRTL(currRoad.getTLJunctionID()).getCurrentGreen();
					}
				}
								
				if (currentGreen == null) {
					nextF = true;
				}
				else if (currentGreen.equals(currRoad.getName())) {
					nextF = true;
				}
				else {
					nextF = false;
				}
				
				if (nextF == true) {
					currRoadIndx++;
					timeOnRoad = 0;
					if (currRoadIndx < vehiclePath.size()) {
						currRoad = vehiclePath.get(currRoadIndx);
						if (currRoad.getStart().getPassengersIDs().size() != 0) {
							if (currRoad.getStart().getPassengersIDs().get(0) != null & getPassengerID() == 0) {
								setPassengerID(currRoad.getStart().getPassengersIDs().get(0));
								currRoad.getStart().removePassengerID();
								setArrivedJunction(currRoad.getStart().getID());
								currRoadIndx = 0;
								System.out.println("Vehicle " + getID() + " is collecting Passenger " + getPassengerID() + " at Junction " + getArrivedJunction());
							}
						}
						else {
							System.out.println("Vehicle " + getID() + " is moving on the " + currRoad.getName());
						}
					}
					else {
						System.out.println("Vehicle " + getID() + " arrived to it's destination: Junction " + currRoad.getEnd());
						vehicleArrived = true;
					}
				}
				else {
					System.out.println("Vehicle " + getID() + " is waiting for green light on Junction " + currRoad.getEnd());
				}
			}
		}
		else {
			System.out.println("Vehicle " + getID() + " arrived to it's destination: Junction " + currRoad.getEnd());
		}
	}
	
	private SequentialTL getSTL(int JunctionID) {
		SequentialTL seqTL = null;
		
		for (int i = 0 ; i < map.getS_TL().size() ; i++) {
			if (map.getS_TL().get(i).getJunctionID() == JunctionID) {
				seqTL = map.getS_TL().get(i);
			}
		}
		return seqTL;
	}
	
	private RandomTL getRTL(int JunctionID) {
		RandomTL ranTL = null;
		
		for (int i = 0 ; i < map.getS_TL().size() ; i++) {
			if (map.getS_TL().get(i).getJunctionID() == JunctionID) {
				ranTL = map.getR_TL().get(i);
			}
		}
		return ranTL;
	}
	
	public Boolean vehicleArrived () {
		return vehicleArrived;
	}
	
	private void setPassengerID(int pID) {
		passengerID = pID;
	}
	
	public int getPassengerID() {
		return passengerID;
	}
	
	public void setArrivedJunction (int arrivedJID) {
		arrivedJunctionID = arrivedJID;
	}
	
	public int getArrivedJunction () {
		return arrivedJunctionID;
	}
	
	public void setVehiclePath(ArrayList<Road> vehPath) {
		vehiclePath = vehPath;
	}
	
}
