package com.mywebsite.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;

import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;
import com.mywebsite.pages.CalculatorPage;
import com.mywebsite.pages.FirstPage;
import com.mywebsite.pages.HomePage;
import com.mywebsite.pages.TestPage;

public class MenuListFactory extends WebPage {

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

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
	list.add(new MenuItem("TestPage"){
	    @Override
	    public void onClick(AjaxRequestTarget target) {
		 setResponsePage(TestPage.class);
	    }
	});
	list.add(new MenuItem("..."));

	return list;
    }
    

   

}
