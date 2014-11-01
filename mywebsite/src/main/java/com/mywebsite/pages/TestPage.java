package com.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;
import com.googlecode.wicket.jquery.ui.widget.accordion.AccordionPanel;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.mywebsite.entity.MenuListFactory;
import com.mywebsite.panels.PanelCalc;
import com.mywebsite.panels.PanelHomePage;
import com.mywebsite.panels.PanelPhoneBook;
import com.mywebsite.panels.PanelTaskOverview;

public class TestPage extends WebPage {
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private MenuListFactory listFactory = new MenuListFactory();
    private Form form ;
    public TestPage(final PageParameters parameters) {
	super(parameters);
	this.setOutputMarkupId(true);
	this.setOutputMarkupPlaceholderTag(true);
	Menu menu = new Menu("menu",listFactory.newMenuItemList());
	add(menu);
	
JQueryFeedbackPanel feedback = new JQueryFeedbackPanel("feedback");
	
	form = new Form("form");
	form.add(feedback);
	add(form);
	
	
	
	final List<ITab> tabs = new ArrayList<ITab>();
	tabs.add(new AbstractTab(new Model<String>("Persönliche Daten"))
        {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(String panelId)
            {
                return new PanelHomePage(panelId);
            }

        });
	
	tabs.add(new AbstractTab(new Model<String>("Task Overview"))
        {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(String panelId)
            {
                return new PanelTaskOverview(panelId);
            }

        });
	
	tabs.add(new AbstractTab(new Model<String>("Phone Book"))
        {
            private static final long serialVersionUID = 1L;

            @Override
            public Panel getPanel(String panelId)
            {
                return new PanelPhoneBook(panelId);
            }

        });
	
	add(new AccordionPanel("ac", tabs));

    }
    
    public Form getForm() {
        return form;
    }
    
    
    

}
