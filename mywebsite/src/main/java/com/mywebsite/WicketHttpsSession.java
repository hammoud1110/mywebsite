package com.mywebsite;

import org.apache.wicket.Session;
import org.apache.wicket.core.request.ClientInfo;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;


    
    public class WicketHttpsSession extends WebSession {
	 
	    private String authenticatedUsername;
	 
	    public WicketHttpsSession(Request request) {
	        super(request);
	    }
	 
	    public static WicketHttpsSession get() {
	        return (WicketHttpsSession) Session.get();
	    }
	 
	    public String getAuthenticatedUsername() {
	        return authenticatedUsername;
	    }
	 
	    public void setAuthenticatedUsername(String authenticatedUsername) {
	        this.authenticatedUsername = authenticatedUsername;
	    }
}
