package closestPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import closestAlgorithm.ClosestKPoints;
import closestAlgorithm.Distance;

public class Bootstrap {
	
	public static void main(String[] args) {
		
		//Config
		int k = 5;
		int n = 10;
		int maxLat = 90;
		int maxLong = 180;
		Random rand = new Random();
		
		//Create data
		List<Hotel> hotels = new ArrayList<Hotel>();;
		for (int i = 0; i < n; i++) {
			double lat = maxLat  * rand.nextDouble() * (rand.nextDouble() >= 0.5 ? 1 : -1);
			double lon = maxLong  * rand.nextDouble() * (rand.nextDouble() >= 0.5 ? 1 : -1);
			hotels.add(new Hotel(i, lon, lat));
		}
		
		//Apply the algorithm
		ClosestKPoints<Hotel> worker = new ClosestKPoints<Hotel>(hotels);
		Map<Integer, List<Distance>> result = worker.getClosestPoints(k);
		
		//Print out results
		System.out.println(result);
		
		
	}
		
}
