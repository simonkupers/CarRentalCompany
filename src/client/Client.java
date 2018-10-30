package client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import rental.CarRentalCompany;
import lib.IManagerSession;
import lib.IReservationSession;
import lib.ISessionManager;
import lib.ManagerSession;
import lib.ReservationSession;
import lib.SessionManager;
import rental.CarType;
import rental.ICarRentalCompany;
import rental.Quote;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.RentalServer.CrcData;
import rental.RentalServer;

public class Client extends AbstractTestManagement<IReservationSession, IManagerSession> {
	
	/********
	 * MAIN *
	 ********/
	
	ICarRentalCompany crc;
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(null);
		String carRentalCompanyName = "Hertz";
		
		// An example reservation scenario on car rental company 'Hertz' would be...
		Client client = new Client("trips", carRentalCompanyName);
		
		
		// An example making car rental companies with a ManagerSession
		IManagerSession managerSession1 = (IManagerSession) client.getNewManagerSession("managerSession1", "Hertz");
		CrcData hertzData = RentalServer.loadData("hertz.csv");
		CarRentalCompany hertz = new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars);
		ICarRentalCompany stubHerz = (ICarRentalCompany) UnicastRemoteObject.exportObject(hertz, 0);
		managerSession1.register(stubHerz);

		IManagerSession managerSession2 = (IManagerSession) client.getNewManagerSession("managerSession2", "Dockx");
		CrcData dockxData = RentalServer.loadData("dockx.csv");
		
		CarRentalCompany dockx = new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars);
		ICarRentalCompany stubDockx = (ICarRentalCompany) UnicastRemoteObject.exportObject(dockx, 0);
		managerSession2.register(stubDockx); 
		
		client.run();
	}
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	public Client(String scriptFile, String carRentalCompanyName) {
		super(scriptFile);
		
		
		
		
	}

	@Override
	protected Set<String> getBestClients(IManagerSession ms) throws Exception {
		IManagerSession managerSession = (IManagerSession) ms;
		return managerSession.getBestClients();
	}

	@Override
	protected String getCheapestCarType(IReservationSession session, Date start, Date end, String region) throws Exception {
		IReservationSession reservationSession = (IReservationSession)session;
		return reservationSession.getCheapestCarType(start, end, region).getName();
	}

	@Override
	protected CarType getMostPopularCarTypeIn(IManagerSession ms, String carRentalCompanyName, int year) throws Exception {
		IManagerSession managerSession = (IManagerSession) ms;
		return managerSession.getMostPopularCarTypeIn(carRentalCompanyName, year);
		
	}

	@Override
	protected IReservationSession getNewReservationSession(String name) throws Exception {
		
		Registry registry = null;
		registry = LocateRegistry.getRegistry("127.0.0.1",1099);
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManagerStub");
		sessionManager.createReservationSession(name);
		return (IReservationSession) registry.lookup(name + "Reservation");
	}

	@Override
	protected IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		Registry registry = null;
		registry = LocateRegistry.getRegistry("127.0.0.1",1099);
		ISessionManager sessionManager = (ISessionManager) registry.lookup("sessionManagerStub");
		sessionManager.createManagerSession(name, carRentalName);
		return (IManagerSession) registry.lookup(name + "Manager");
	}

	@Override
	protected void checkForAvailableCarTypes(IReservationSession session, Date start, Date end) throws Exception {
		IReservationSession reservationSession = (IReservationSession)session;
		reservationSession.checkForAvailableCarTypes(start, end);
	}

	@Override
	protected void addQuoteToSession(IReservationSession session, String name, Date start, Date end, String carType, String region)
			throws Exception {
		
		IReservationSession reservationSession = (IReservationSession) session;
		ReservationConstraints constraints = new ReservationConstraints(start,end, carType,region);
		reservationSession.createQuote(constraints, name);
	}

	@Override
	protected List<Reservation> confirmQuotes(IReservationSession session, String name) throws Exception {
		IReservationSession reservationSession = (IReservationSession) session;
		return reservationSession.confirmQuotes();
	}

	@Override
	protected int getNumberOfReservationsBy(IManagerSession ms, String clientName) throws Exception {
		IManagerSession managerSession = (IManagerSession) ms;
		return managerSession.getNumberOfReservationsBy(clientName);
		
	}

	@Override
	protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType) throws Exception {
		IManagerSession managerSession = (IManagerSession) ms;
		return managerSession.getNumberOfReservationsForCarType(carRentalName, carType);
		
	}





}