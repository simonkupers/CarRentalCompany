package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

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
	
	public Set<String> getBestClients(){
		Set<String> clients = new HashSet<String>();
		for(ICarRentalCompany crc:ram.getCarRentalCompanies()){
			clients.addAll(crc.getBestClients());
			
		}
		return clients;
		
	}
	
	public void register(ICarRentalCompany carRentalCompany) {
		ram.addCarRentalCompany(carRentalCompany);
	}

	public void unRegister(ICarRentalCompany carRentalCompany) {
		ram.removeCarRentalCompany(carRentalCompany);
	}
	
	public CarRentalCompany createCarRentalCompany(){
		return null;
	}

	@Override
	public int getNumberOfReservationsBy(String client) {
		int reservations = 0;
		for(ICarRentalCompany crc:ram.getCarRentalCompanies()){
			try {
				reservations += crc.getReservationsByRenter(client).size();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reservations;
	}

	@Override
	public int getNumberOfReservationsForCarType(String carRentalName, String carType) {
		int reservations = 0;
		for(ICarRentalCompany crc:ram.getCarRentalCompanies()){
			
				try {
					if(crc.getName().equals(carRentalName))
						return crc.getNumberOfReservationsForCarType(carType);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return 0;
	}
	
}
