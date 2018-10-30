package lib;

import java.util.List;
import java.util.ArrayList;

public class SessionManager {
	
	private List<Session> sessions = new ArrayList<Session>();
	
	public void addSession(Session session){
		sessions.add(session);
	}
	
	public void removeSession(Session session){
		sessions.remove(session);
	}
	
	
	

}
