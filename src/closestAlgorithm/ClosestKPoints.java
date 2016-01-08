package closestAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ClosestKPoints<T extends ClosestFindable<T>> {

	List<T> items = null;
	

	public ClosestKPoints() {
	}
	
	public ClosestKPoints(final List<T> _items) {
		setItems(_items);
	}
	
	public void setItems(final List<T> _items) {
		//clone and sort the received array
		items = new ArrayList<T>(_items);
		Collections.sort(items);
	}
	
	public Map<Integer, List<Distance>> getClosestPoints(final int k) {
		
		//if no items have been set
		if (items == null) return null;
		
		Map<Integer, List<Distance>> results = new HashMap<Integer, List<Distance>>();
		Integer n = items.size();
		
		for (int i = 0; i < n; i++) {
			
			//l and r are the pointers to the left and right of the current hotels
			int l = i - 1;
			int r = i + 1;
						
			//In the heap we store the k minDistances
			PriorityQueue<Distance> minDistances = new PriorityQueue<Distance>(k, Collections.reverseOrder());;
			
			//working variables
			T current = items.get(i);
			T next = null;
			
			double curDistance;
			boolean outOfItems = false;
			
			//repeat, check adjacent items 
			do {
				
				//if r goes out of bounds start over
				if (r >= n) r = 0;
				if (l < 0) l = n - 1;
				
				//if l and r are togheter
				if (l == r || l -1 == r) {
					outOfItems = true;
					next = null;
				} else  {
					next = Math.abs(current.getNormalizedX() - items.get(l).getNormalizedX()) < Math.abs(current.getNormalizedX() - items.get(r).getNormalizedX()) ? items.get(l--) : items.get(r++);
				}
				
				if (next != null) {
					
					//calculate the real distance
					curDistance = current.getEuclideanDistance(next);
					
					//if distance is lower than the maximun in the heap
					if (minDistances.size() < k || curDistance < minDistances.peek().getDistance()) {
						
						//ad this distance to the heap
						minDistances.add(new Distance(next.getId(), curDistance));
						
						//we dont want ot exceed k size
						if (minDistances.size() > k) { minDistances.poll(); }
					}
				}
			
			//exit the loop if the next hotel X's distance is greater the max allowed euclidean distance 
			//AND the heap is complete OR we run out of items 
			} while (!outOfItems && (Math.abs(current.getNormalizedX() - next.getNormalizedX()) < minDistances.peek().getDistance() || minDistances.size() < k));
			
			
			List<Distance> closests = new ArrayList<Distance>();
			results.put(current.getId(), closests);
			
			// Copy the heap results to the final results array
			while(!minDistances.isEmpty()){
				Distance d = minDistances.poll();
				closests.add(d);
				//Reverse to keep the lower result first
				Collections.reverse(closests);
			}
			
			
		}
		

		return results;
	}
}
