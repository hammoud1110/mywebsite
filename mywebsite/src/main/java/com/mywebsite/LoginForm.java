package com.mywebsite;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class LoginForm extends StatelessForm<LoginDTO> {

    private static final String MESSAGE_KEY_LOGIN_FAILED = "error.login.failed";

    private static final String TAG_ATTRIBUTE_ACTION_NAME = "action";
    private static final String PROTOCOL_HTTPS_PREFIX = "https";
    private static final String PROTOCOL_HTTP_PREFIX = "http";
    private static final String WICKET_ID_FEEDBACK = "feedback";
    private static final String WICKET_ID_USERNAME = "username";
    private static final String WICKET_ID_PASSWORD = "password";

    public LoginForm(String id) {
	super(id, new CompoundPropertyModel(new LoginDTO()));
	init();
    }

    public LoginForm(String id, IModel iModel) {
	super(id, iModel);
	init();
    }

    private void init() {
	add(new FeedbackPanel(WICKET_ID_FEEDBACK));
	add(new TextField(WICKET_ID_USERNAME).setRequired(true));
	add(new PasswordTextField(WICKET_ID_PASSWORD).setRequired(true));
    }

    @Override
    protected void onSubmit() {
	LoginDTO credentials = this.getModelObject();

	String username = credentials.getUsername();
	String password = credentials.getPassword();

	if (isValidUser(username, password)) {
	    processSuccessfulLogin(username);
	} else {
	    processFailedLogin();
	}
    }

    // A dummy way to test, if the username and password are correct. In real
    // life,
    // this method would naturally look quite different.
    private boolean isValidUser(String username, String password) {
	if (username.equals("foo") && (password.equals("bar"))) {
	    return true;
	}
	return false;
    }

    private void processSuccessfulLogin(String username) {
	WicketHttpsSession currentSession = WicketHttpsSession.get();
	// Invalidates the current session and creates a new secure session.
	// The created secure session cannot be accessed when using http
	// protocol. This option should be used when only https protocol is
	// used after the user has logged in.
	// currentSession.replaceSession();
	currentSession.setAuthenticatedUsername(username);
	forwardUserToHomePage();
    }

    private void forwardUserToHomePage() {
	this.setResponsePage(HomePage.class);
    }

    private void processFailedLogin() {
	error(getString(MESSAGE_KEY_LOGIN_FAILED));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
	super.onComponentTag(tag);

	// HttpServletRequest request = ((HttpServletRequest)
	// this.getWebRequest()).getHttpServletRequest();
	HttpServletRequest request = (HttpServletRequest) getRequest()
		.getContainerRequest();

	// Create an absolute url, which points to a page used to process login
	// information
	String securedAbsoluteActionUrl = UrlGenerator
		.generateAbsoluteLoginUrl(PROTOCOL_HTTPS_PREFIX,
			WicketApplication.HTTPS_PORT, request);

//	tag.put(TAG_ATTRIBUTE_ACTION_NAME, securedAbsoluteActionUrl);
    }
}
