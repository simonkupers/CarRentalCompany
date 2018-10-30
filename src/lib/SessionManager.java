package lib;

import java.util.List;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SessionManager implements ISessionManager {

	@Override
	public void createReservationSession(String clientName) {
		ReservationSession reservationSession = new ReservationSession(clientName);
		try{
			Registry registry = LocateRegistry.getRegistry();
			IReservationSession stub = (IReservationSession) UnicastRemoteObject.exportObject(reservationSession,0);
			registry.rebind(clientName + "Reservation", stub);
			
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void createManagerSession(String clientName, String rentalName) {
		ManagerSession managerSession = new ManagerSession(clientName, rentalName);
		try{
			Registry registry = LocateRegistry.getRegistry();
			IManagerSession stub = (IManagerSession) UnicastRemoteObject.exportObject(managerSession,0);
			registry.rebind(clientName + "Manager", stub);
				
			}catch(RemoteException e){
				e.printStackTrace();
			
		}
		
	}
	
	
	
	
	

}
