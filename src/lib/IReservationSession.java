package lib;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

public interface IReservationSession extends ISession{
	
	public void createQuote(ReservationConstraints constraint, String rentalName);
	
	public List<Quote> getCurrentQuotes();
	
	public List<Reservation> confirmQuotes() throws ReservationException;
	
	public List<CarType> getAvailableCarTypes();

	public CarType getCheapestCarType(Date start, Date end, String region);

}
