package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import rental.CarRentalCompany;
import rental.CarType;
import rental.ICarRentalCompany;
import rental.Reservation;

public class ManagerSession extends Session implements IManagerSession {
	
	public String carRentalName = null;
	IRentalAgencyManager ram;
	Registry registry;
	
	public ManagerSession(String clientName, String carRentalName){
		super(clientName);
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1",1099);
			ram = (IRentalAgencyManager) registry.lookup("rentalAgencyManager");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.carRentalName = carRentalName;
	}
	
	public Set<String> getBestClients(){
		Set<String> clients = new HashSet<String>();
		Map<String, Integer> reservaties = new HashMap<String,Integer>();
		try {
			for(ICarRentalCompany crc:ram.getCarRentalCompanies()){
				for(Reservation reservation:crc.getReservations()){
					
					if(!reservaties.containsKey(reservation.getCarRenter()))
						reservaties.put(reservation.getCarRenter(),1);
					else
						reservaties.put(reservation.getCarRenter(), reservaties.get(reservation.getCarRenter())+1);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int maxValueInMap=(Collections.max(reservaties.values()));  // This will return max value in the Hashmap
        for (Entry<String, Integer> entry : reservaties.entrySet()) {  // Iterate through hashmap
            if (entry.getValue()==maxValueInMap) {
            	clients.add(entry.getKey());
            }
        }
        return clients;
	}
	
	public void register(ICarRentalCompany carRentalCompany) {
		try {
			ram.addCarRentalCompany(carRentalCompany);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unRegister(ICarRentalCompany carRentalCompany) {
		try {
			ram.removeCarRentalCompany(carRentalCompany);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getNumberOfReservationsBy(String client) {
		int reservations = 0;
		try {
			for(ICarRentalCompany crc:ram.getCarRentalCompanies()){
				reservations += crc.getReservationsByRenter(client).size();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservations;
	}

	@Override
	public int getNumberOfReservationsForCarType(String carRentalName, String carType) {
		int reservations = 0;
		try {
			for(ICarRentalCompany crc:ram.getCarRentalCompanies()){
				if(crc.getName().equals(carRentalName)){
					return crc.getNumberOfReservationsForCarType(carType);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year) {
		ICarRentalCompany crc = null;
		try {
			crc = ram.getCarRentalCompany(carRentalCompanyName);
			return crc.getMostPopularCarTypeIn(year);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
