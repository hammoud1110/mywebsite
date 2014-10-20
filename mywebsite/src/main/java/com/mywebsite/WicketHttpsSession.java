package com.mywebsite;


import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
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
 * A session implementation of this secure form example. This implementation
 * is used to store the username of the authenticated user.
 * @author Petri Kainulainen
 */
public class WicketHttpsSession extends WebSession {

    private String authenticatedUsername;

    private String loginRefererUrl;

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

    public String getLoginRefererUrl() {
        return loginRefererUrl;
    }

    public void setLoginRefererUrl(String loginRefererUrl) {
        this.loginRefererUrl = loginRefererUrl;
    }
}
