package lib;

import java.util.List;
import java.util.StringTokenizer;

import rental.Car;
import rental.CarRentalCompany;
import rental.CarType;
import rental.ReservationException;
import rental.RentalServer.CrcData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SessionManager implements Remote {
	
	
	public void createReservationSession(String clientName){
		
		ReservationSession reservationSession = new ReservationSession(clientName);
		IReservationSession stub;
		try {
			Registry registry = LocateRegistry.getRegistry();
			stub = (IReservationSession) UnicastRemoteObject.exportObject(reservationSession,0);
			registry.rebind(clientName + "Reservation", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CrcData hertzData;
		try {
			hertzData = loadData("hertz.csv");
			
		CarRentalCompany hertz = new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars);
		managerSession.register(hertz, "hertz");
		
		CrcData dockxData  = loadData("dockx.csv");
		CarRentalCompany dockx = new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars);
		managerSession.register(dockx, "dockx");
		} catch (NumberFormatException | ReservationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createManagerSession(String clientName, String rentalName){
		ManagerSession managerSession = new ManagerSession(clientName, rentalName);
		IManagerSession stub;
		try{
			Registry registry = LocateRegistry.getRegistry();
			stub = (IManagerSession) UnicastRemoteObject.exportObject(managerSession, 0);
			registry.rebind(clientName + "Manager", stub);
		}catch(RemoteException e){
			e.printStackTrace();
			
		}
		CrcData hertzData;
		try {
			hertzData = loadData("hertz.csv");
			
		CarRentalCompany hertz = new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars);
		managerSession.register(hertz, "hertz");
		
		CrcData dockxData  = loadData("dockx.csv");
		CarRentalCompany dockx = new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars);
		managerSession.register(dockx, "dockx");
		} catch (NumberFormatException | ReservationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static CrcData loadData(String datafile)
			throws ReservationException, NumberFormatException, IOException {

		CrcData out = new CrcData();
		int nextuid = 0;

		// open file
		BufferedReader in = new BufferedReader(new FileReader(datafile));
		StringTokenizer csvReader;
		
		try {
			// while next line exists
			while (in.ready()) {
				String line = in.readLine();
				
				if (line.startsWith("#")) {
					// comment -> skip					
				} else if (line.startsWith("-")) {
					csvReader = new StringTokenizer(line.substring(1), ",");
					out.name = csvReader.nextToken();
					out.regions = Arrays.asList(csvReader.nextToken().split(":"));
				} else {
					// tokenize on ,
					csvReader = new StringTokenizer(line, ",");
					// create new car type from first 5 fields
					CarType type = new CarType(csvReader.nextToken(),
							Integer.parseInt(csvReader.nextToken()),
							Float.parseFloat(csvReader.nextToken()),
							Double.parseDouble(csvReader.nextToken()),
							Boolean.parseBoolean(csvReader.nextToken()));
					System.out.println(type);
					// create N new cars with given type, where N is the 5th field
					for (int i = Integer.parseInt(csvReader.nextToken()); i > 0; i--) {
						out.cars.add(new Car(nextuid++, type));
					}
				}
			}
		} finally {
			in.close();
		}

		return out;
	}
	
	static class CrcData {
		public List<Car> cars = new LinkedList<Car>();
		public String name;
		public List<String> regions =  new LinkedList<String>();
	}
	
	
	

}
