package drone.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class represents a trip made by a drone
 *  
 *  @author MB
 */

public class Trip extends ModelBase {

	private int totalWeight = 0;
	private Drone drone;
	private List<Delivery> deliveries = new ArrayList<Delivery>();
	
	public Trip(Drone aDrone) {
		drone = aDrone;
	}
	
	public int getTotalWeight() {
		return totalWeight;
	}

	public Drone getDrone() {
		return drone;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void addDelivery(Delivery newDel){
		deliveries.add(newDel);
		totalWeight += newDel.getWeight();
	}
	
}
