package rental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

import lib.IManagerSession;
import lib.IRentalAgencyManager;
import lib.ISessionManager;
import lib.ManagerSession;
import lib.RentalAgencyManager;
import lib.SessionManager;

public class RentalServer {

	public static void main(String[] args) throws ReservationException, NumberFormatException, IOException {
		System.setSecurityManager(null);
		RentalAgencyManager rentalAgencyManager = new RentalAgencyManager();
		IRentalAgencyManager stub = (IRentalAgencyManager) UnicastRemoteObject.exportObject(rentalAgencyManager, 0);

		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("rentalAgencyManager", stub);

		// create a manager session and register Hertz and Docks as car rental companies

<<<<<<< HEAD
		
		SessionManager sessionManager = new SessionManager();
		ISessionManager sessionManagerStub =
				(ISessionManager) UnicastRemoteObject.exportObject(sessionManager, 0);
		registry.rebind("sessionManagerStub", sessionManagerStub);
=======
		SessionManager sessionManager = new SessionManager();
		ISessionManager sessionStub = (ISessionManager) UnicastRemoteObject.exportObject(sessionManager, 0);
		registry.rebind("sessionManagerStub", sessionStub);
>>>>>>> branch 'master' of https://github.com/simonkupers/CarRentalCompany.git

<<<<<<< HEAD
=======
		/*CrcData hertzData = loadData("hertz.csv");
		CarRentalCompany hertz = new CarRentalCompany(hertzData.name, hertzData.regions, hertzData.cars);
		managerSession.register(hertz);
>>>>>>> branch 'master' of https://github.com/simonkupers/CarRentalCompany.git

<<<<<<< HEAD
=======
		CrcData dockxData = loadData("dockx.csv");
		CarRentalCompany dockx = new CarRentalCompany(dockxData.name, dockxData.regions, dockxData.cars);
		managerSession.register(dockx); */
>>>>>>> branch 'master' of https://github.com/simonkupers/CarRentalCompany.git
	}

	public static CrcData loadData(String datafile) throws ReservationException, NumberFormatException, IOException {

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
					CarType type = new CarType(csvReader.nextToken(), Integer.parseInt(csvReader.nextToken()),
							Float.parseFloat(csvReader.nextToken()), Double.parseDouble(csvReader.nextToken()),
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

	public static class CrcData {
		public List<Car> cars = new LinkedList<Car>();
		public String name;
		public List<String> regions = new LinkedList<String>();
	}

}
