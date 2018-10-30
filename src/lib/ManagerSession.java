package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rental.CarRentalCompany;
import rental.ICarRentalCompany;

public class ManagerSession extends Session implements IManagerSession {
	
	public String carRentalName = null;
	RentalAgencyManager ram;
	Registry registry;
	
	public ManagerSession(String clientName, String carRentalName){
		super(clientName);
		try {
			registry = LocateRegistry.getRegistry();
			ram = (RentalAgencyManager) registry.lookup("rentalAgencyManager");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.carRentalName = carRentalName;
	}
	
	public void getBestClient(){
		//TODO
		
	}
	
	public void register(CarRentalCompany carRentalCompany) {
		ram.addCarRentalCompany(carRentalCompany);
	}

	public void unRegister(CarRentalCompany carRentalCompany) {
		ram.removeCarRentalCompany(carRentalCompany);
	}
	
	public CarRentalCompany createCarRentalCompany(){
		return null;
	}
	
}
