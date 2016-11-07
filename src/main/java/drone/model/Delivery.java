package drone.model;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class represents a delivery to be made
 *  
 *  @author MB
 */
public class Delivery extends ModelBase implements Comparable<Delivery> {

	private String location = "";
	private int weight = 0;
	
	public Delivery(String aLocation, int aWeight) {
		location = aLocation;
		weight = aWeight;
	}
	public String getLocation() {
		return location;
	}
	public int getWeight() {
		return weight;
	}
	
	// Sorts deliveries in order of weight (biggest first)
	@Override
	public int compareTo(Delivery o) {
		if (this.weight == o.getWeight()) return 0;
		if (this.weight < o.getWeight()) return 1;
		return -1;
	}
	
}
