package lib;

import java.util.List;
import java.util.StringTokenizer;

import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.ReservationException;
import rental.RentalServer.CrcData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SessionManager implements Remote {
	
	
	public void createReservationSession(String clientName){
		
		ReservationSession reservationSession = new ReservationSession(clientName);
		IReservationSession stub;
		try {
			Registry registry = LocateRegistry.getRegistry();
			stub = (IReservationSession) UnicastRemoteObject.exportObject(reservationSession,0);
			registry.rebind(clientName + "Reservation", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void createManagerSession(String clientName, String rentalName){
		ManagerSession managerSession = new ManagerSession(clientName, rentalName);
		IManagerSession stub;
		try{
			Registry registry = LocateRegistry.getRegistry();
			stub = (IManagerSession) UnicastRemoteObject.exportObject(managerSession, 0);
			registry.rebind(clientName + "Manager", stub);
		}catch(RemoteException e){
			e.printStackTrace();
			
		}
	}
	

	

}
