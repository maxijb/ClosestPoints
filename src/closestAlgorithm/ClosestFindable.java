package closestAlgorithm;


public interface ClosestFindable<T extends Object> extends Comparable<T>{
	public abstract double getEuclideanDistance(T b);
	public abstract double getXDistance(T b);
	public abstract Integer getId();
}
