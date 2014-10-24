package com.mywebsite;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.RequireHttps;

import com.mywebsite.pages.HomePage;

/*
 * Copyright 2011 Petri Kainulainen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * @author Petri Kainulainen
 */
@RequireHttps
public class LoginHandlerPage extends WebPage {

    private final String MESSAGE_KEY_LOGIN_FAILED = "error.login.failed";
    private final String MESSAGE_KEY_PASSWORD_EMPTY = "loginForm.password.Required";
    private final String MESSAGE_KEY_USERNAME_EMPTY = "loginForm.username.Required";

    private final String PARAM_USERNAME = "username";
    private final String PARAM_PASSWORD = "password";

    private final String USERNAME = "foo";
    private final String PASSWORD = "bar";

    public LoginHandlerPage(final PageParameters parameters) {
        String username = parameters.getString(PARAM_USERNAME);
        String password = parameters.getString(PARAM_PASSWORD);

        boolean requiredInformationEntered = isRequiredInformationEntered(username, password);

        if (requiredInformationEntered) {
            if (isLoginValid(username, password)) {
                storeUserInfoToSession(username);
                redirectToTargetPage(HomePage.class);
            }
            else {
                //Sets a feedback message to session. This is necessary,
                //because the feedback message must exists across requests.
                getSession().error(getString(MESSAGE_KEY_LOGIN_FAILED));
                redirectToTargetPage(SecureLoginPage.class);
            }
        }
        else {
            redirectToTargetPage(SecureLoginPage.class);
        }
    }

    /**
     * Wicket built-in validation is not done (I guess the reason is the generation
     * of absolute url, which bypasses some of Wicket Form functions). This method
     * validates that the username and password are entered.
     * @param username
     * @param password
     * @return  true if both username and password are entered and false otherwise.
     */
    private boolean isRequiredInformationEntered(String username, String password) {
        boolean isValidLogin = true;

        if (username.isEmpty()) {
            getSession().error(getString(MESSAGE_KEY_USERNAME_EMPTY));
            isValidLogin = false;
        }

        if (password.isEmpty()) {
            getSession().error(getString(MESSAGE_KEY_PASSWORD_EMPTY));
            isValidLogin = false;
        }

        return isValidLogin;
    }

    private void storeUserInfoToSession(String username) {
        WicketHttpsSession session = WicketHttpsSession.get();
        //Invalidates the current session and creates a new secure session.
        //The created secure session cannot be accessed when using http
        //protocol. This option should be used when only https protocol is
        //used after the user has logged in.
        //session.replaceSession();
        session.setAuthenticatedUsername(username);
    }

    private boolean isLoginValid(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return true;
        }
        return false;
    }

    private void redirectToTargetPage(Class pageClass) {
        setResponsePage(pageClass);
    }
}

