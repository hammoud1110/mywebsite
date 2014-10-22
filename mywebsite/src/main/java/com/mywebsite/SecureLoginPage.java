package com.mywebsite;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SecureLoginPage extends WebPage {
    private static final long serialVersionUID = 1L;
    private static final String WICKET_ID_HOMEPAGE_LINK = "homepageLink";
    private static final String WICKET_ID_LOGINFORM = "loginForm";

    public SecureLoginPage(final PageParameters parameters) {
	super(parameters);

	init();
    }

    protected void init() {
//	BookmarkablePageLink bookmarkablePageLink = new BookmarkablePageLink(WICKET_ID_HOMEPAGE_LINK, HomePage.class);
//	
//	add(bookmarkablePageLink);
	add(new LoginForm(WICKET_ID_LOGINFORM));
    }

}