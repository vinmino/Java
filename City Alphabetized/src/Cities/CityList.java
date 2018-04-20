package Cities;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class CityList {
	/*
	 * Data
	 */
	int cityLength;
	ArrayList<String> cityList = new ArrayList<String>();
	ArrayList<String> alphabetized = new ArrayList<String>();
	Scanner key = new Scanner(System.in);
	
	/*
	 * Constructor
	 */
	public CityList() {
		System.out.print("Input the number of cities: ");
		this.cityLength = key.nextInt();
		System.out.println("Input the names of the cities: ");
		for (int i = 0; i <= cityLength; i++) {
			cityList.add(key.nextLine());
		}
		cityList.remove(0);
		
	}
	
	/*
	 * Methods
	 */
	private void trim() {
		for (int i = 0; i < cityLength; i++) {
			cityList.set(i, cityList.get(i).trim());
		}
	}
	
	public void alphEZ() {
		Collections.sort(cityList);
		for (int i = 0; i < cityList.size(); i++) {
			if (i != cityList.size() - 1) {
				System.out.print(cityList.get(i) + ", ");
			} else {
				System.out.print(cityList.get(i));
			}
			
		}
	}

}
