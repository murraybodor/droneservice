package drone.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import drone.controller.DroneDeliveryService;
import drone.controller.DroneDeliveryServiceException;
import drone.model.Delivery;
import drone.model.Drone;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class is to test the service
 *  
 *  @author MB
 */
public class DroneDeliveryServiceTest {
	
	// Exception test - no drones 
    @Test(expected=DroneDeliveryServiceException.class)
    public void testNoDrones() throws Exception {
    	
        List<Drone> drones = null;
        
        List<Delivery> deliveries = new ArrayList<Delivery>();
        
        deliveries.add(new Delivery("Pluto", 100));
        deliveries.add(new Delivery("Pluto", 400));
    	
		DroneDeliveryService droneService = DroneDeliveryService.getInstance();
		droneService.planDeliveryTrips(drones, deliveries);
    }

	// Exception test - no deliveries
    @Test(expected=DroneDeliveryServiceException.class)
    public void testNoDeliveries() throws Exception {
    	
        List<Drone> drones = new ArrayList<Drone>();

        drones.add(new Drone("A", 100));
        drones.add(new Drone("B", 400));

        List<Delivery> deliveries = new ArrayList<Delivery>();
        
        DroneDeliveryService droneService = DroneDeliveryService.getInstance();
		droneService.planDeliveryTrips(drones, deliveries);
    }

	// Exception test - delivery too big to handle
    @Test(expected=DroneDeliveryServiceException.class)
    public void testDeliveryException() throws Exception {
    	
        List<Drone> drones = new ArrayList<Drone>();
        
        drones.add(new Drone("A", 100));
        drones.add(new Drone("B", 400));
        
        List<Delivery> deliveries = new ArrayList<Delivery>();
        
        deliveries.add(new Delivery("Pluto", 300));
        deliveries.add(new Delivery("Pluto", 2500));
    	
		DroneDeliveryService droneService = DroneDeliveryService.getInstance();
		droneService.planDeliveryTrips(drones, deliveries);
    }

	// Normal conditions
    @Test
    public void testNormalDeliveries() throws Exception {
    	
        List<Drone> drones = new ArrayList<Drone>();
        
        drones.add(new Drone("A", 100));
        drones.add(new Drone("B", 400));
        drones.add(new Drone("C", 220));
        drones.add(new Drone("D", 800));
        drones.add(new Drone("E", 10));
        drones.add(new Drone("F", 40));
        
        List<Delivery> deliveries = new ArrayList<Delivery>();
        
        deliveries.add(new Delivery("Jupiter", 40));
        deliveries.add(new Delivery("Jupiter", 80));
        deliveries.add(new Delivery("Jupiter", 160));
        deliveries.add(new Delivery("Mars", 250));
        deliveries.add(new Delivery("Mars", 500));
        deliveries.add(new Delivery("Venus", 20));
        deliveries.add(new Delivery("Pluto", 300));
        deliveries.add(new Delivery("Pluto", 170));
        deliveries.add(new Delivery("Neptune", 80));
        deliveries.add(new Delivery("Neptune", 10));
        deliveries.add(new Delivery("Jupiter", 120));
        deliveries.add(new Delivery("Mars", 40));
    	
		DroneDeliveryService droneService = DroneDeliveryService.getInstance();
		int numTrips = droneService.planDeliveryTrips(drones, deliveries);
    
		assertEquals(3, numTrips);
		
    }
    
}
