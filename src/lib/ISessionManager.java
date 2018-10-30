package lib;

import java.util.List;
import java.rmi.Remote;
import java.util.ArrayList;

public interface ISessionManager extends Remote {
	
	public void createReservationSession(String clientName);
	
	public void createManagerSession(String clientName, String rentalName);
	
}
