import java.util.ArrayList;

/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

public class Passenger {
	private Map map;
	public static int Passenger_id_global = 0;
	private int id = 0;
	private ArrayList<Road> passengerPath = new ArrayList<Road>();
	private Junction startJ = null;
	private Junction endJ = null;

	public Passenger (Map map1) {
		setID();
		map = new Map(map1.getJunctions(), map1.getRoads(), map1.getS_TL(), map1.getR_TL());
		map.creatRandomPath();
		passengerPath = map.getRandomPathRoads();
		startJ = passengerPath.get(0).getStart();
		startJ.addPassengerID(getID());
		endJ = passengerPath.get(passengerPath.size()-1).getEnd();
		System.out.println("Passenger " + getID() + " is waiting for vehicle at Junction " + startJ + ", path: " + map.getrandomPathString());
	}
	
	public Junction getStartJunction () {
		return startJ;
	}
	
	public Junction getEndJunction () {
		return endJ;
	}
	
	private void setID() {
		id = ++Passenger_id_global;
		}
	
	public int getID() {
		return id;
		}
	
	public ArrayList<Road> getPassengerPath() {
		return passengerPath;
	}
}
