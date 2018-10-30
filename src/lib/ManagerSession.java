package lib;

public class ManagerSession extends Session {
	
	public String carRentalName = null;
	RentalAgencyManager ram = RentalAgencyManager.getRentalAgencyManager();
	
	public ManagerSession(String clientName, String carRentalName){
		super(clientName);
		this.carRentalName = carRentalName;
	}
	
	public void getBestClient(){
		
		
	}

}
