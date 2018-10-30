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

public interface IReservationSession extends ISession{
	
	public void createQuote(ReservationConstraints constraint);
	
	public List<Quote> getCurrentQuotes();
	
	public void confirmQuotes();
	
	public List<CarType> getAvailableCarTypes();

	public CarType getCheapestCarType();

}
