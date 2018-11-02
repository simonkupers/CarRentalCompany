package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ICarRentalCompany extends Remote {
	
	public boolean isAvailable(String carTypeName, Date start, Date end) throws RemoteException;
	
	public Quote createQuote(ReservationConstraints constraints, String client)
			throws RemoteException, ReservationException;
	
	public Reservation confirmQuote(Quote quote) throws RemoteException, ReservationException;
	
	public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
	
	public List<Reservation> getReservationsByRenter(String clientName) throws RemoteException;
	
	public int getNumberOfReservationsForCarType(String carType) throws RemoteException;
	
	public String getName() throws RemoteException;
	
	public Collection<CarType> getAllCarTypes() throws RemoteException;
	
	public List<String> getRegions() throws RemoteException ;
	
	public List<String> getBestClients() throws RemoteException;
	
	public CarType getMostPopularCarTypeIn(int year) throws RemoteException;
	
	public List<Reservation> getReservations() throws RemoteException;
	
}
