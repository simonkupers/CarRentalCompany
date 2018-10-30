package lib;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rental.ICarRentalCompany;

public class RentalAgencyManager {
	
	private static RentalAgencyManager ram = null;
	
	private RentalAgencyManager(){};
	
	public static RentalAgencyManager getRentalAgencyManager(){
		if(ram == null){
			ram = new RentalAgencyManager();
		}
		return ram;
	}
	
	private List<ICarRentalCompany> crcs = new ArrayList<ICarRentalCompany>();
	
	
	public void addCarRentalCompany(ICarRentalCompany crc){
		crcs.add(crc);
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
