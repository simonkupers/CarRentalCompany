package lib;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISessionManager extends Remote {
	
	public void createReservationSession(String clientName) throws RemoteException;
	
	public void createManagerSession(String clientName, String rentalName) throws RemoteException;
	
}
