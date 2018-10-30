package lib;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rental.ICarRentalCompany;

public interface IRentalAgencyManager extends Remote {
	
	public void addCarRentalCompany(ICarRentalCompany crc);
	
	public ICarRentalCompany getCarRentalCompany(String name);
	
	public List<ICarRentalCompany> getCarRentalCompanies();
}
