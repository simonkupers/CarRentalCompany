package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public void addCarRentalCompany(ICarRentalCompany crc){
		crcs.add(crc);
	}
	
	public void removeCarRentalCompany(ICarRentalCompany crc){
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
	
	public List<ICarRentalCompany> getCarRentalCompanies(){
		return crcs;
	}
	
	
	
	
	

}
