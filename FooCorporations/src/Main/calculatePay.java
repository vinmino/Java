/*
 * Vincent Lomino
 * F Block
 * This is the class that does most of the heavy lifting for the RUN class.
 * The RUN class has the main function.
 * 
 */

package Main;

public class calculatePay {
	private final double BASE_RATE = 8.0;
	private final double OVERTIME_RATE = 8.0 * 1.5;
	
	private double hours;
	
	//Constructors
	public calculatePay() {
		
	}
	
	public calculatePay(double hours) {
		 this.hours = hours;
	}
	
	//Methods
	public double getHours() {
		return hours;
	}
	
	public double getOvertime() {
		double OT;
		
		if (hours > 40) {
			OT = hours - 40;
			this.hours = 40;
		} else {
			OT = 0;
		}
		
		return OT;
	}
	
	public double getBasePay() {
		double newHours;
		if (hours > 40) {
			newHours = 40;
		} else {
			newHours = hours;
		}
		
		return newHours * BASE_RATE;
	}
	
	public double getOvertimePay() {
		return this.getOvertime() * OVERTIME_RATE;
	}
	
	public double getTotalPay() {
		return this.getBasePay() + this.getOvertimePay();
	}
}
