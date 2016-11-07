package drone.controller;

import java.util.Collections;
import java.util.List;

import drone.model.Delivery;
import drone.model.Drone;
import drone.model.Trip;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class is the main Service
 *  
 *  @author MB
 */
public class DroneDeliveryService {

	private static DroneDeliveryService instance = new DroneDeliveryService( );

	private DroneDeliveryService() { }

	public static DroneDeliveryService getInstance( ) {
	     return instance;
	}
	
	/** 
	 * Plans the deliveries with the drones
	 * @param drones
	 * @param deliveries
	 * @return total number of trips planned
	 * @throws DroneDeliveryServiceException if something goes terribly wrong
	 */
	public int planDeliveryTrips(List<Drone> drones, List<Delivery> deliveries) throws DroneDeliveryServiceException   {
		
//		System.out.println("planDeliveryTrips starting ");

		if (drones==null || drones.size()==0)
			throw new DroneDeliveryServiceException("No drones provided!");

		if (deliveries==null || deliveries.size()==0)
			throw new DroneDeliveryServiceException("No deliveries to be made!");

		// sort all of the drones in order of capacity
		Collections.sort(drones);
		
		// sort all of the deliveries in order of weight
		Collections.sort(deliveries);

		
		// We're always going to use the biggest drone for every trip: 
		// - it costs the same to refuel as the smaller ones
		// - using any of the smaller drones will require more total trips
		// - there are no requirements to use all of the drones, or to minimize the number of trips taken per drone, or to save time
		
		Drone bigDrone = drones.get(0); // the highest capacity drone has been sorted to the front of the list
		
		int maxPackageWeightHandled = bigDrone.getMaxWeight();
	
		// process all of the requested deliveries
		while (deliveries.size() > 0) {
			
			Trip newTrip = new Trip(bigDrone);
			bigDrone.addTrip(newTrip);
			
			// find deliveries that will fit in the drone's available capacity - start with the biggest first
			for (int i = 0; i < deliveries.size(); i++) {
				Delivery currentDelivery = deliveries.get(i);
				
				// calculate the weight allowance left
				int weightLeft = bigDrone.getMaxWeight() - newTrip.getTotalWeight();
				
				if (currentDelivery.getWeight() <= weightLeft) {
					newTrip.addDelivery(currentDelivery);
					deliveries.remove(deliveries.indexOf(currentDelivery));
					i--; // make sure we dont skip an entry
				} else {
					if (currentDelivery.getWeight() > maxPackageWeightHandled) {
						// this delivery is too heavy for any drone to carry
						throw new DroneDeliveryServiceException("I found a package bigger than any drone can handle!");
					} else {
						// this delivery is too heavy for this trip - try the next delivery
					}
				}
					
			} // all deliveries have been processed for this trip
			
		} // all deliveries have been assigned to drones

		
		// now print everything out
		
		System.out.println(" ");
		System.out.println("Drone Name: " + bigDrone.getName());
		System.out.println(" ");
		int tripNum = 1;
		
		for (Trip eachTrip : bigDrone.getTrips()) {
			
			System.out.println("Trip #" + tripNum);
			System.out.println(" ");
			
			StringBuffer locations = new StringBuffer();
			boolean isFirst = true;
			
			for (Delivery eachDelivery : eachTrip.getDeliveries()) {

				if (!isFirst) {
					locations.append(", ");
				}
				locations.append(eachDelivery.getLocation());
				isFirst = false;
				
			}

			System.out.println(locations.toString());
			System.out.println(" ");

			tripNum++;
		}
		
		return bigDrone.getTrips().size();

	}
}
