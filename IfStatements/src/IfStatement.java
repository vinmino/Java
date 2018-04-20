
public class IfStatement {

	public static void test(int x) {
		if(x > 5) {
			System.out.println(x + " is > 5");
		} else if (x == 5) {
			System.out.println(x + " equals 5");
		} else {
			System.out.println(x + " is < 5");
		}
	}
	
	
	
	public static void main(String[] args) {
		
		for(int tick = 1; tick < 21; tick++) {
			test(tick);
		}
	}

}
