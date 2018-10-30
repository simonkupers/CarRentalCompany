package lib;

import java.util.List;
import java.rmi.Remote;
import java.util.ArrayList;

public interface ISessionManager extends Remote {
	
	public void addSession(Session session);
	
	public void removeSession(Session session);
	
}
