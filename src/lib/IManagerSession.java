package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rental.CarRentalCompany;
import rental.ICarRentalCompany;

public interface IManagerSession extends ISession {
	
	public void getBestClient();
	
	public void register(ICarRentalCompany carRentalCompany);

	public void unRegister(ICarRentalCompany carRentalCompany);
	
	public int getNumberOfReservationsBy(String client);
	
	public int getNumberOfReservationsForCarType(String carRentalName, String carType);
	
}
