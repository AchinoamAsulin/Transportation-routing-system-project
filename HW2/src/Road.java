
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

public class Road {
	private String name;
	private double length;
	private Junction J_Start;
	private Junction J_End;
	private int TLJunctionID = 0;
	
	public Road(Junction J1, Junction J2) {
		
		setStart(J1);
		if ((J1.getX() == J2.getX()) & (J1.getY() == J2.getY())) {
			Junction J3 = new Junction();
			setEnd(J3);
			System.out.println("Road can not connect a junction to itself, the end junction has been replaced with Junction " + J3);
			}
		else {
			setEnd(J2);
		}
		setName(J_Start, J_End);
		setLength(J_Start, J_End);
		J_End.setEnteringRoads(J_Start, J_End);
		J_Start.setExitingRoads(J_Start, J_End);
		System.out.print("Creating " + getName() + ", length: ");
		System.out.printf("%.2f\n",getLength());
	}
	
	public void setName(Junction J1, Junction J2) {
		name = "Road from Junction " + J1 + " to Junction " + J2;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLength(Junction J1, Junction J2) {
		length = J1.calcDistance(J2);
	}
	
	public double getLength() {
		return length;
	}
	
	private void setStart(Junction J) {
		J_Start = J;
	}
	
	private void setEnd(Junction J) {
		J_End = J;
	}
	
	public Junction getStart() {
		return J_Start;
	}
	
	public Junction getEnd() {
		return J_End;
	}
	
	public void setTLJunctionID (int junctionID) {
		TLJunctionID = junctionID;
	}
	
	public int getTLJunctionID () {
		return TLJunctionID;
	}
	
	@Override
	public String toString() {
		return getName();
		}
}

