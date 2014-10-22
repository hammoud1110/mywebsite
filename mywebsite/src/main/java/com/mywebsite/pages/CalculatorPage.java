package com.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;
import com.mywebsite.HomePage;
import com.mywebsite.panels.PanelCalc;

public class CalculatorPage extends WebPage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CalculatorPage(final PageParameters parameters) {
	super(parameters);
	this.setOutputMarkupId(true);
	this.setOutputMarkupPlaceholderTag(true);
	Menu menu = new Menu("menu", newMenuItemList());
	add(menu);
	
	PanelCalc panel = new PanelCalc("panel");
	add(panel);
    }

    
    public List<IMenuItem> newMenuItemList() {
	List<IMenuItem> list = new ArrayList<IMenuItem>();

	list.add(new MenuItem("Home",KendoIcon.PLUS) {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
		setResponsePage(HomePage.class);

	    }
	});
	list.add(new MenuItem("eShop") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
		setResponsePage(FirstPage.class);

	    }
	});

	list.add(new MenuItem("Tools", newSubMenuList()));

	return list;
    }

    public List<IMenuItem> newSubMenuList() {
	List<IMenuItem> list = new ArrayList<IMenuItem>();

	list.add(new MenuItem("Calculator"){
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
	       
	        setResponsePage(CalculatorPage.class);
	    }
	});
	list.add(new MenuItem("..."));
	list.add(new MenuItem("..."));

	return list;
    }
}
