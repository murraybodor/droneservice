package drone.controller;

/**
 *  Programming Exercise for Agile Recruiting/SigFig
 *  
 *  This class is an exception to handle when bad things happen
 *  
 *  @author MB
 */
public class DroneDeliveryServiceException extends Exception {

	private static final long serialVersionUID = -3061814516950736560L;

	public DroneDeliveryServiceException(String string) {
		super(string);
	}

}
