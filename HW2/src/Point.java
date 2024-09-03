
/**
 * Achinoam Asulin i.d 325560076
 * Ora Medina i.d 021919014
 */

import java.util.Random;

public class Point {
	double x;
	double y;
	private final double minX = 0;
	private final int maxX = 800;
	private final double minY = 0;
	private final int maxY = 600;
	private double[] coordinates = new double[2];
	public static int id_global = 0;
	int id = 0;
	
	public Point() {
		coordinates[0]=x;
		coordinates[1]=y;
		coordinates = createP(coordinates);
		x=coordinates[0];
		y=coordinates[1];
	}
	
	public Point(double new_x, double new_y) {
		createSpecificP(new_x, new_y);
		}

	public void createSpecificP (double new_x, double new_y) {
		Random rand = new Random();
		
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
		System.out.printf("Creating Point (%.2f, %.2f)\n",getX(), getY());
		
	}
	
	public double[] createP (double[] coordinates) {
		Random rand = new Random();
		coordinates[0] = rand.nextInt(maxX) + rand.nextDouble();
		coordinates[1] = rand.nextInt(maxY) + rand.nextDouble();
		System.out.printf("Creating Point (%.2f, %.2f)\n",coordinates[0], coordinates[1]);
		return coordinates;
	}
	
	public void isPointValidX (double x) throws Exception{
			
		if (x > maxX | x < minX) {
			throw new Exception (" is illegal value for x and has been replaced with ");
		}
	}
	
	public void isPointValidY (double y) throws Exception{
		
		if (y > maxY | y < minY) {
			throw new Exception (" is illegal value for y and has been replaced with ");
		}
	}
	
	public double calcDistance (Point other) {
		return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));	
	}
	
	public void setX(double new_x) {
		x = new_x;	
	}
	
	public void setY(double new_y) {
		y = new_y;	
	}
	
	public double getX () {
		return x;	
	}

	public double getY () {
		return y;	
	}
}

	