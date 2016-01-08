package closestAlgorithm;

public class Distance implements Comparable<Distance> {
	int id;
	double distance;
	
	public Distance(int _id, double _dis) {
		this.id = _id;
		this.distance = _dis;
	}
	
	@Override
	public int compareTo(Distance other) {
		return Double.compare(distance, other.getDistance());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
}
