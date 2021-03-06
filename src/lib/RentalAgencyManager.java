package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rental.CarRentalCompany;
import rental.ICarRentalCompany;

public class RentalAgencyManager implements IRentalAgencyManager{
	
	Registry registry;
	
	public RentalAgencyManager(){
		try {
			registry = LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	private List<ICarRentalCompany> crcs = new ArrayList<ICarRentalCompany>();
	
	
	public synchronized void addCarRentalCompany(ICarRentalCompany crc){
		crcs.add(crc);
	}
	
	public synchronized void removeCarRentalCompany(ICarRentalCompany crc){
		crcs.remove(crc);
	}
	
	public ICarRentalCompany getCarRentalCompany(String name){
		for(ICarRentalCompany crc:crcs){
			try {
				if(crc.getName().equals(name))
					return crc;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public Set<String> getBetsClients(){
		Set<String> clients = new HashSet<String>();
		for(ICarRentalCompany crc:crcs){
			try {
				clients.addAll(crc.getBestClients());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clients;
	}
	
	public List<ICarRentalCompany> getCarRentalCompanies(){
		return crcs;
	}
	
	
	
	
	

}
