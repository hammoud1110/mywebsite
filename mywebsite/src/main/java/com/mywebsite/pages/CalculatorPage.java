package com.mywebsite.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.mywebsite.entity.MenuListFactory;
import com.mywebsite.panels.PanelCalc;

public class CalculatorPage extends WebPage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private MenuListFactory listFactory = new MenuListFactory();
    public CalculatorPage(final PageParameters parameters) {
	super(parameters);
	this.setOutputMarkupId(true);
	this.setOutputMarkupPlaceholderTag(true);
	Menu menu = new Menu("menu", listFactory.newMenuItemList());
	add(menu);
	
	PanelCalc panel = new PanelCalc("panel");
	add(panel);
    }

    
    
}
