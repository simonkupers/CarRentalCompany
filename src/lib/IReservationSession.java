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
	
	public void createQuote(ReservationConstraints constraint, String rentalName) throws RemoteException, ReservationException;
	
	public List<Quote> getCurrentQuotes() throws RemoteException;
	
	public List<Reservation> confirmQuotes() throws ReservationException, RemoteException;
	
	public List<CarType> getAvailableCarTypes()throws RemoteException;

	public CarType getCheapestCarType(Date start, Date end, String region)throws RemoteException;
	
	public void checkForAvailableCarTypes(Date start, Date end) throws RemoteException;

}
