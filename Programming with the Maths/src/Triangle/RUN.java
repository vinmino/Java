package Triangle;

public class RUN {

	public static void main(String[] args) {
		while (true) {
			TriangleObject T = new TriangleObject();
			T.calculate();
			System.out.println("The angles are: " + T.getAngle(T.A) + ", " + T.getAngle(T.B) + ", and " + T.getAngle(T.C) + " degrees.");
			System.out.println("The sides are : " + T.a + ", " + T.b + ", and " + T.c + " units.");
			System.out.println("The are of the triangle is: " + T.area + " units squared.\n");
		}
		
	}

}
