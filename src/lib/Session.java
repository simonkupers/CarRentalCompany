package lib;

import java.util.concurrent.atomic.AtomicInteger;

public class Session {
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	private final int Id;
	protected String clientName;
	
	public Session(String clientName){
		this.clientName = clientName;
		Id = count.incrementAndGet();
	}

	public int getId(){
		return Id;
	}
}
