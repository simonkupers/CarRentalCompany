package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rental.CarRentalCompany;
import rental.ICarRentalCompany;

public interface IManagerSession extends ISession {
	
	public void getBestClient();
	
	public void register(CarRentalCompany carRentalCompany);

	public void unRegister(CarRentalCompany carRentalCompany);
	
}
