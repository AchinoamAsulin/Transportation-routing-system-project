
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map implements Dynamic {
	private final double TLprob = 0.25;
	private ArrayList<Junction> junctions=new ArrayList<Junction>();
	private ArrayList<Road>roads=new ArrayList<Road>();
	private ArrayList<SequentialTL>S_TL=new ArrayList<SequentialTL>();
	private ArrayList<RandomTL>R_TL=new ArrayList<RandomTL>();
	private String[] randomPathNames = new String[4];
	private ArrayList<Junction> randomJroute = new ArrayList<Junction>();
	private ArrayList<Road> randomPathRoads=new ArrayList<Road>();

	public Map(int n) {
		for (int i=0 ; i < n ; i++) {
			junctions.add(new Junction());
		}
		Random rand = new Random();
		for (int j=0 ; j<n ; j++) {
			for (int i = 0 ; i<n ; i++) {
				if ((i != j) & (rand.nextBoolean())) {
					roads.add(new Road(junctions.get(j), junctions.get(i)));
				}
			}
		}
		build_TL_Array(n);
		for (int i=0 ; i<5 ; i++) {
			singleMove();
		}
		creatRandomPath();
	}
	
	public Map(ArrayList<Junction> junctionsEx, ArrayList<Road>roadsEx, ArrayList <SequentialTL> S_TL_ex, ArrayList <RandomTL> R_TL_ex) {
		junctions=junctionsEx;
		roads=roadsEx;
		S_TL=S_TL_ex;
		R_TL=R_TL_ex;

	}
	
	public Map(ArrayList<Junction> junctionsEx, ArrayList<Road>roadsEx) {
		junctions=junctionsEx;
		roads=roadsEx;
		build_TL_Array(junctions.size());
		for (int i=0 ; i<5 ; i++) {
			singleMove();
		}
	}
	
	private void build_TL_Array(int n) {
		Random rand = new Random();
		for (int i=0 ; i<n ; i=(int)(i+n*TLprob)) {
			if (rand.nextBoolean() == true) {
				S_TL.add(new SequentialTL(junctions.get(i)));
			}
			else {
				R_TL.add(new RandomTL(junctions.get(i)));
			}
			setRoadsTL(junctions.get(i));
		}
	}
	
	private void setRoadsTL(Junction J1) {
		for (int i = 0 ; i < roads.size() ; i++) {
			if (J1.getID() == roads.get(i).getEnd().getID()) {
				roads.get(i).setTLJunctionID(J1.getID());;
			}
		}
	}
	
	public void singleMove() {
		for (int i=0 ; i < S_TL.size() ; i++) {
			S_TL.get(i).singleMove();
		}
		for (int i=0 ; i < R_TL.size() ; i++) {
			R_TL.get(i).singleMove();
		}
	}
	
	public String creatRandomPath() {
		Random rand = new Random();

		int i = 0;
		int j = 0;
		Junction nextJ;
		Road road1;
		
		road1=roads.get(rand.nextInt(roads.size()-1));
		randomJroute.add(road1.getStart());
		nextJ = road1.getEnd();
		randomJroute.add(nextJ);
		randomPathRoads.add(road1);
		randomPathNames[0] = road1.getName();
		i = 0;
		
		while ((nextJ.getCurrent_rOut_i() > 0) & (i < 3)) {
			
			j = 0;
			while (roads.get(j).getStart().getID() != nextJ.getID()) {
				j++;
				if (j >= roads.size()) {
					break;
				}
			}
			road1 = roads.get(j);
			nextJ = road1.getEnd();
			randomJroute.add(nextJ);
			i++;
			randomPathRoads.add(road1);
			randomPathNames[i] = road1.getName();
			
		}
		return Arrays.toString(randomPathNames);
	}
	
	public String getrandomPathString() {
		return Arrays.toString(randomPathNames);
	}
	
	public ArrayList<Junction> getJunctions() {
		return junctions;
	}
	
	public ArrayList<SequentialTL> getS_TL() {
		return S_TL;
	}
	
	public ArrayList<RandomTL> getR_TL() {
		return R_TL;
	}
	
	public ArrayList<Road> getRoads() {
		return roads;
	}
	
	public ArrayList<Road> getRandomPathRoads() {
		return randomPathRoads;
	}
}
