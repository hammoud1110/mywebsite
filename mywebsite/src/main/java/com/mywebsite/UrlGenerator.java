package com.mywebsite;

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


import javax.servlet.http.HttpServletRequest;

/**
 * @author Petri Kainulainen
 */
public class UrlGenerator {
    private static final char CONTEXT_PATH_SEPARATOR = '/';

    public static String generateAbsoluteLoginUrl(String protocol, Integer port, HttpServletRequest request) {
        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append(protocol);
        urlBuilder.append("://");
        urlBuilder.append(request.getServerName());
        if (port != null) {
            urlBuilder.append(":");
            urlBuilder.append(port);
        }

        String contextPath = request.getContextPath();

        if (contextPath != null) {
            urlBuilder.append(contextPath);
            if (!contextPath.isEmpty() && contextPath.charAt(contextPath.length()-1) != CONTEXT_PATH_SEPARATOR) {
                urlBuilder.append(CONTEXT_PATH_SEPARATOR);
            }
        }

        urlBuilder.append(WicketApplication.LOGIN_HANDLER_PATH);

        return urlBuilder.toString();
    }
}
