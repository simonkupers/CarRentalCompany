package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

import rental.CarRentalCompany;
import rental.CarType;
import rental.ICarRentalCompany;

public interface IManagerSession extends ISession {
	
	public Set<String> getBestClients();
	
	public void register(ICarRentalCompany carRentalCompany);

	public void unRegister(ICarRentalCompany carRentalCompany);
	
	public int getNumberOfReservationsBy(String client);
	
	public int getNumberOfReservationsForCarType(String carRentalName, String carType);
	
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year);
	
}
