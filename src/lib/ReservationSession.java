package lib;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.ReservationConstraints;
import rental.ReservationException;

public class ReservationSession extends Session implements IReservationSession{
	
	private List<Quote> quotes = new ArrayList<Quote>();
	
	public ReservationSession(String clientName){
		super(clientName);
	}
	
	
	
	public void createQuote(ReservationConstraints constraint){
		RentalAgencyManager rcm = RentalAgencyManager.getRentalAgencyManager();
		for(ICarRentalCompany crc: rcm.getCarRentalCompanies()){
			try {
				Quote quote = crc.createQuote(constraint, clientName);
				quotes.add(quote);
				return;
			} catch (RemoteException | ReservationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public CarType getCheapestCarType(){
		
		//TODO: THIs.
		return null;
	}

}
