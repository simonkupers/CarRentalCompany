package lib;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rental.CarRentalCompany;
import rental.ICarRentalCompany;

public interface IRentalAgencyManager extends Remote {
	
	public void addCarRentalCompany(ICarRentalCompany crc) throws RemoteException;
	
	public void removeCarRentalCompany(ICarRentalCompany crc)throws RemoteException;
	
	public ICarRentalCompany getCarRentalCompany(String name)throws RemoteException;
	
	public List<ICarRentalCompany> getCarRentalCompanies()throws RemoteException;
}
