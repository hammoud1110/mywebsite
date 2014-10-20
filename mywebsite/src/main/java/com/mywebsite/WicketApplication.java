package com.mywebsite;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication{

    public static final String LOGIN_HANDLER_PATH = "loginhandler";
    public static final int HTTP_PORT = 8080;
    public static final int HTTPS_PORT = 8443;

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<SecureLoginPage> getHomePage() {
	// return SecureLoginPage.class;
	return SecureLoginPage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
	super.init();
	
	setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080,8443)));
//	setRootRequestMapper(new CryptoMapper(getRootRequestMapper(), this));
//        mountPage("/Home", HomePage.class);
	mountPage(LOGIN_HANDLER_PATH, LoginHandlerPage.class);
    }

    
    
 
//    protected IRequestCycleProcessor newRequestCycleProcessor() {
//	HttpsConfig config = new HttpsConfig();
//	// Port 8080 is used for HTTP
//	config.setHttpPort(8080);
//	// Port 8443 is used for HTTPS
//	config.setHttpsPort(8443);
//
//	return new HttpsRequestCycleProcessor(config);
//    }

    @Override
    public Session newSession(Request request, Response response) {
	return new WicketHttpsSession(request);
    }

}
