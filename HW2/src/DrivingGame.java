
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DrivingGame implements Dynamic{
private Map map;
private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
private ArrayList<Object> vehTL = new ArrayList<Object>();
private int vehiclesNum = 0;
File report = new File("report.txt");


	public DrivingGame(int junctionNum, int vehiclesN){
		map = new Map(junctionNum);
		vehiclesNum = vehiclesN;
		for (int i = 0 ; i < vehiclesN ; i++) {
			vehicles.add(new Vehicle(map));
			vehTL.add(vehicles.get(i));
		}
		for (int i = 0 ; i < map.getS_TL().size() ; i++) {
			vehTL.add(map.getS_TL().get(i));
		}
		for (int i = 0 ; i < map.getR_TL().size() ; i++) {
			vehTL.add(map.getR_TL().get(i));
		}
	}
	
	public void play() {
		int i = 0;
		int j = 0;
		Boolean[] vehiclesArrived = new Boolean[vehiclesNum];
			
		System.out.println("");
		allVehiclesArrivedInit(vehiclesArrived);
		fileInit(report);
		
		while (allVehiclesArrived(vehiclesArrived) == false) {
			i++;
			System.out.println("");
			System.out.println("Turn " + (i));
			singleMove();
			for (j = 0 ; j < vehicles.size() ; j++) {
				if (vehicles.get(j).getPassengerID() != 0 ) {
					vehicles.get(j).setVehiclePath(passengers.get(vehicles.get(j).getPassengerID()-1).getPassengerPath());
				}
				if (vehicles.get(j).vehicleArrived() == true & vehiclesArrived[j] != true) {
					vehiclesArrived[j] = true;
					if (vehicles.get(j).getPassengerID() != 0) {
						reportWrite(vehicles.get(j).getID(), vehicles.get(j).getPassengerID(), passengers.get(vehicles.get(j).getPassengerID()-1).getStartJunction().getID(), passengers.get(vehicles.get(j).getPassengerID()-1).getEndJunction().getID());
					}
				}
			}
			
			if (i % 3 == 0) {
				passengers.add(new Passenger(map));
			}
		}
	}
	
	public void singleMove() {
		SequentialTL STL;
		RandomTL RTL;
		Vehicle V;
		
		for (int n = 0 ; n < vehTL.size() ; n++) {
			if (vehTL.get(n).toString().contains("RandomTL")) {
				RTL = (RandomTL) vehTL.get(n);
				RTL.singleMove();
			}
			if (vehTL.get(n).toString().contains("SequentialTL")) {
				STL = (SequentialTL) vehTL.get(n);
				STL.singleMove();
			}
			if (vehTL.get(n).toString().contains("Vehicle")){
				V = (Vehicle) vehTL.get(n);
				V.singleMove();
			}
		}
	}
	
	private void allVehiclesArrivedInit(Boolean[] vehiclesArrived) {
		for (int i = 0 ; i < vehiclesNum ; i++) {
			vehiclesArrived[i] = false;
		}
	}
	
	private Boolean allVehiclesArrived(Boolean[] vehiclesArrived) {
		Boolean result = true;
		
		for (int i = 0 ; i < vehiclesNum ; i++) {
			if (vehiclesArrived[i] == false) {
				result = false;
			}
		}
		return result;
	}
	
	public ArrayList<Road> getPassengerPath(int pID) {
		ArrayList<Road> result = null;
		
		for (int i = 0 ; i < passengers.size() ; i++) {
			if (passengers.get(i).getID() == pID) {
				result = passengers.get(i).getPassengerPath();
			}
		}
		return result;
	}
	
	private void fileInit(File f1) {
		
		if (f1.exists() == false) {
			try {
				f1.createNewFile();
				System.out.println("create file");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter myWriter = new FileWriter("report.txt");
			myWriter.write("  Vehicle   |   Passenger   |     From   |     To    \r\n------------|---------------|------------|------------\r\n");
			myWriter.close();
			}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			}
	}
	
	private void reportWrite(int vehicleID, int passengerID, int startJ, int endJ) {
		
		try {
			FileWriter myWriter = new FileWriter("report.txt", true);
			myWriter.write("Vehicle " + vehicleID + "   |Passenger " + passengerID + "    |Junction " + startJ + "  |Junction " + endJ + " \r\n");
			myWriter.close();
			}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			}
	}
}
