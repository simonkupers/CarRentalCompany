package lib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;

import rental.Car;
import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.ReservationConstraints;
import rental.ReservationException;

public class ReservationSession extends Session implements IReservationSession{

	private List<Quote> quotes = new ArrayList<Quote>();
	Registry registry;

	public ReservationSession(String clientName){
		super(clientName);
		try {
			registry = LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createQuote(ReservationConstraints constraint){
		try {
			RentalAgencyManager ram;
			ram = (RentalAgencyManager) registry.lookup("rentalAgencymanager");
			for(ICarRentalCompany crc: ram.getCarRentalCompanies()){
				Quote quote = crc.createQuote(constraint, clientName);
				quotes.add(quote);
				return;
			}
		} catch (RemoteException | NotBoundException | ReservationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<Quote> getCurrentQuotes(){
		return quotes;
	}

	public void confirmQuotes(){
		//TODO: THIs.

	}

	public List<CarType> getAvailableCarTypes(){

		//TODO: THIs.
		return null;
	}

	public CarType getCheapestCarType(Date start, Date end, String region){
		try {
			RentalAgencyManager ram;
			ram = (RentalAgencyManager) registry.lookup("rentalAgencymanager");
			Collection<CarType> carTypes = new ArrayList<CarType>();
			for(ICarRentalCompany crc: ram.getCarRentalCompanies()){
				if (crc.getRegions().contains(region)) {
					carTypes.addAll(crc.getAvailableCarTypes(start, end));
				}
			}
			CarType cheapestType = carTypes
					.stream()
					.min(Comparator.comparing(CarType::getRentalPricePerDay))
					.orElseThrow(NoSuchElementException::new);
			return cheapestType;
			
		} catch (RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public void checkForAvailableCarTypes(Date start, Date end){
		try {
			RentalAgencyManager ram;
			ram = (RentalAgencyManager) registry.lookup("rentalAgencymanager");
			for(ICarRentalCompany crc: ram.getCarRentalCompanies()){
				if (!crc.getAvailableCarTypes(start, end).isEmpty()) {
					System.out.println("There is a car available between " + start.toString() + " and " + end.toString());
				}
			}
			System.out.println("There is no car available between " + start.toString() + " and " + end.toString());
			
		} catch (RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
