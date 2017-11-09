package drone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import drone.model.Delivery;
import drone.model.Drone;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class is a command line bootstrap for the service
 *  It expects the following input lines:
 *  
 *  Line 1: Drone #1 name, Drone #1 max weight, Drone #2 name, Drone #2 max weight, etc
 *  Line 2: Location #1 name, Location #1 package weight
 *  Line 3: Location #2 name, Location #2 package weight
 *  Line 4: Location #3 name, Location #3 package weight
 *  etc.
 *  Last Line: (no entry, press return)
 *  
 *  @author MB
 */

public class DroneDeliveryServiceRunner {

	public static void main(String[] args) {
	    System.out.println("starting");

	    /*
	     *  Process the input (assuming command line)
	     */
	    
	    String line;
        Scanner stdin = new Scanner(System.in);
        
        // get the list of drones from the first input line
        List<Drone> drones = new ArrayList<Drone>();
        
        if (stdin.hasNextLine() && !( line = stdin.nextLine() ).equals( "" )) {
            String[] droneTokens = line.split(",");
            for (int i = 0; i < droneTokens.length-1; i = i+2) {
                System.out.println("name=" + droneTokens[i] + " maxweight=" + droneTokens[i+1]);
                drones.add(new Drone(droneTokens[i], Integer.parseInt(droneTokens[i+1])));
			}
        }
        
        
        // now get each line of deliveries required
        List<Delivery> deliveries = new ArrayList<Delivery>();
        
        while(stdin.hasNextLine() && !( line = stdin.nextLine() ).equals( "" ))
        {
            String[] deliveryTokens = line.split(",");
            for (int i = 0; i < deliveryTokens.length-1; i = i+2) {
                System.out.println("location=" + deliveryTokens[i] + " weight=" + deliveryTokens[i+1]);
                deliveries.add(new Delivery(deliveryTokens[i], Integer.parseInt(deliveryTokens[i+1])));
            }
        
        }
        
        stdin.close();	    
	    
        for (Delivery delivery : deliveries) {
            System.out.println("Delivery Loc=" + delivery.getLocation() + " Weight=" + delivery.getWeight());
        }
        
	    /*
	     *  Invoke the service
	     */
        try {
    		DroneDeliveryService droneService = DroneDeliveryService.getInstance();
    		droneService.planDeliveryTrips(drones, deliveries);
        } catch (DroneDeliveryServiceException ex) {
        	ex.printStackTrace();
        }
		
	}

}
