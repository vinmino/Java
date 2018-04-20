package Triangle;

import java.text.DecimalFormat;
import java.util.Scanner;

public class TriangleObject {
	
	//Data
	//Points
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double x3;
	private double y3;
	
	//Sides
	public double a;
	public double b;
	public double c;
	
	//Angles
	public double A;
	public double B;
	public double C;
	
	public double area;
	private Scanner key = new Scanner(System.in);
	
	//Constructors
	public TriangleObject() {
		System.out.print("Input three points of a triangle: ");
		this.x1 = key.nextDouble();
		this.y1 = key.nextDouble();
		this.x2 = key.nextDouble();
		this.y2 = key.nextDouble();
		this.x3 = key.nextDouble();
		this.y3 = key.nextDouble();
	}
	
	//Methods
	
	private void distance() {
		a = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
		b = Math.sqrt(Math.pow((x3-x2),2) + Math.pow((y3-y2),2));
		c = Math.sqrt(Math.pow((x1-x3),2) + Math.pow((y1-y3),2));
	}
	
	private void angles() {
		A = Math.acos((a*a - b*b - c*c)/(-2*b*c));
		B = Math.acos((b*b - a*a - c*c)/(-2*a*c));
		C = Math.acos((c*c - b*b - a*a)/(-2*a*b));
	}
	
	private void area() {
		double s = 0.5 * (a + b + c);
		area = Math.sqrt(s * (s - a)*(s - b)*(s - c));
	}
	
	public void calculate() {
		if (this.check()) {
			this.distance();
			this.angles();
			this.area();
		} else {
			System.out.println("This is not a triangle, try again...");
		}
	}
	
	public String getAngle(double angle) {
		DecimalFormat df = new DecimalFormat("#0.##");
		return df.format(angle * 180/Math.PI);
	}
	
	private boolean check() {
		boolean result;
		if (!(x1 == x2 && y1 == y2) && !(x2 == x3 && y2 == y3) && !(x3 == x1 && y3 == y1)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
}
