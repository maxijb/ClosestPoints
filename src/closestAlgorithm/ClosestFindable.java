package closestAlgorithm;


public interface ClosestFindable<T extends Object> extends Comparable<T>{
	public abstract double getEuclideanDistance(T b);
	public abstract double getNormalizedX();
	public abstract double getNormalizedY();
	public abstract Integer getId();
}
