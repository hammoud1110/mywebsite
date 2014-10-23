package com.mywebsite;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;
import com.mywebsite.entity.MenuListFactory;
import com.mywebsite.pages.CalculatorPage;
import com.mywebsite.pages.FirstPage;

public class HomePage extends WebPage {
    /**
     * 
     */
    private static final long serialVersionUID = 6087575290659178187L;
    
    private MenuListFactory listFactory = new MenuListFactory();

    public HomePage(final PageParameters parameters) {
	super(parameters);

	Menu menu = new Menu("menu", listFactory.newMenuItemList());
	add(menu);
	
	
//	add(new PanelHomePage("panel"));
	

    }

   }
