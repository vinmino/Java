package ZodiacMain;

public class Zodiac {

	int year;
	
	
	public Zodiac() {
		System.out.println("You are stupid and forgot to put in your year of birth :(");
	}
	public Zodiac(int year) {
		this.year = year;
	}
	
	public void calculate() {
		switch (year % 12) {
		case 0: System.out.println("You are a monkey");
			break;
		case 1: System.out.println("You are a rooster");
			break;	
		case 2: System.out.println("You are a dog");
			break;
		case 3: System.out.println("You are a pig");
			break;
		case 4: System.out.println("You are a rat");
			break;
		case 5: System.out.println("You are a ox");
			break;
		case 6: System.out.println("You are a tiger");
			break;
		case 7: System.out.println("You are a rabbit");
			break;
		case 8: System.out.println("You are a dragon");
			break;
		case 9: System.out.println("You are a snake");
			break;
		case 10: System.out.println("You are a horse");
			break;
		case 11: System.out.println("You are a sheep");
			break;
		default:
			System.out.println("You successfully broke math...");
		}
	}

}
