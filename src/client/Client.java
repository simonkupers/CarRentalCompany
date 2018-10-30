package client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import rental.CarRentalCompany;
import lib.ISessionManager;
import lib.ManagerSession;
import lib.ReservationSession;
import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.RentalServer.CrcData;
import rental.RentalServer;

public class Client extends AbstractTestManagement {
	
	/********
	 * MAIN *
	 ********/
	
	ICarRentalCompany crc;
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(null);
				
		String carRentalCompanyName = "Hertz";
		
		// An example reservation scenario on car rental company 'Hertz' would be...
		Client client = new Client("simpleTrips", carRentalCompanyName);
		
		
		// An example making car rental companies with a ManagerSession
		ManagerSession managerSession1 = (ManagerSession) client.getNewManagerSession("managerSession1", "Hertz");
		CrcData hertzData = RentalServer.loadData("hertz.csv");
		CarRentalCompany hertz = new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars);
		managerSession1.register(hertz);

		ManagerSession managerSession2 = (ManagerSession) client.getNewManagerSession("managerSession2", "Dockx");
		CrcData dockxData = RentalServer.loadData("dockx.csv");
		CarRentalCompany dockx = new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars);
		managerSession2.register(dockx);
		
		client.run();
	}
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	public Client(String scriptFile, String carRentalCompanyName) {
		super(scriptFile);
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			crc = (ICarRentalCompany) registry.lookup("crc");
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	protected Set<String> getBestClients(Object ms) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCheapestCarType(Object session, Date start, Date end, String region) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CarType getMostPopularCarTypeIn(Object ms, String carRentalCompanyName, int year) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getNewReservationSession(String name) throws Exception {
		Registry registry = null;
		registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManagerStub");
		sessionManager.createReservationSession(name);
		return registry.lookup(name + "Reservation");
	}

	@Override
	protected Object getNewManagerSession(String name, String carRentalName) throws Exception {
		Registry registry = null;
		registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManagerStub");
		sessionManager.createManagerSession(name, carRentalName);
		
		return registry.lookup(name + "Manager");
	}

	@Override
	protected void checkForAvailableCarTypes(Object session, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addQuoteToSession(Object session, String name, Date start, Date end, String carType, String region)
			throws Exception {
		ReservationSession reservationSession = (ReservationSession) session;
		ReservationConstraints constraints = new ReservationConstraints(start,end, carType,region);
		reservationSession.createQuote(constraints);
		
	}

	@Override
	protected List confirmQuotes(Object session, String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getNumberOfReservationsBy(Object ms, String clientName) throws Exception {
		ManagerSession managerSession = (ManagerSession) ms;
		return managerSession.getNumberOfReservationsBy(clientName);
		
	}

	@Override
	protected int getNumberOfReservationsForCarType(Object ms, String carRentalName, String carType) throws Exception {
		ManagerSession managerSession = (ManagerSession) ms;
		managerSession.getNumberOfReservationsForCarType(carRentalName, carType);
		return 0;
	}



}