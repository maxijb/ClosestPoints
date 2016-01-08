package closestPoints;

import closestAlgorithm.ClosestFindable;

public class Hotel implements ClosestFindable<Hotel>{ 
	private Integer id;
	private double x;
	private double y;

	
	public Hotel(Integer _id, double _x, double _y) {
		this.x = _x;
		this.y = _y;
		this.id = _id;
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public double getNormalizedX() {
		return x;
	}
	
	@Override
	public double getNormalizedY() {
		return y;
	}

	@Override
	public double getEuclideanDistance(Hotel b) {
		return Math.sqrt(Math.pow(x  - b.getX() , 2) + Math.pow(y - b.getY(), 2));
	}
	
	@Override
	public int compareTo(Hotel other) {
		return this.getX() - other.getX() > 0 ? 1 : -1;
	}
}
