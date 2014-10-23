package com.mywebsite.pages;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;
import com.mywebsite.HomePage;
import com.mywebsite.entity.MenuListFactory;
import com.mywebsite.panels.PanelRest;

public class FirstPage extends WebPage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private MenuListFactory listFactory = new MenuListFactory();
    public FirstPage(final PageParameters parameters) {
	super(parameters);
	this.setOutputMarkupId(true);
	this.setOutputMarkupPlaceholderTag(true);
	Menu menu = new Menu("menu",listFactory.newMenuItemList());
	add(menu);
	
	PanelRest panel = new PanelRest("panel");
	add(panel);
    }

    
    
}
