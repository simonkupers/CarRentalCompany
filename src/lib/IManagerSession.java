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
	
	public Set<String> getBestClients() throws RemoteException;
	
	public void register(ICarRentalCompany carRentalCompany) throws RemoteException;

	public void unRegister(ICarRentalCompany carRentalCompany) throws RemoteException;
	
	public int getNumberOfReservationsBy(String client) throws RemoteException;
	
	public int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException;
	
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year) throws RemoteException;
	
}
