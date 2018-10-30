package lib;

import java.util.concurrent.atomic.AtomicInteger;

public class Session implements ISession {
	

	protected String clientName;
	
	public Session(String clientName){
		this.clientName = clientName;
	}


}
