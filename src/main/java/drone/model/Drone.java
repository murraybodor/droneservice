package drone.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class represents a drone 
 *  
 *  @author MB
 */
public class Drone extends ModelBase implements Comparable<Drone> {

	private String name = "";
	private int maxWeight = 0;
	private List<Trip> trips = new ArrayList<Trip>(); 
	
	public Drone(String aName, int aMaxWeight) {
		name = aName;
		maxWeight = aMaxWeight;
	}
	
	public String getName() {
		return name;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	// Sorts drones in order of capacity (biggest first)
	@Override
	public int compareTo(Drone o) {
		if (this.maxWeight == o.getMaxWeight()) return 0;
		if (this.maxWeight < o.getMaxWeight()) return 1;
		return -1;
	}


}
