package com.mywebsite;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;

public class HomePage extends WebPage {
    /**
     * 
     */
    private static final long serialVersionUID = 6087575290659178187L;
    private FirstPanel panel;
    private TabPanel1 tab1;
    private TabPanel2 tab2;
    private TabPanel3 tab3;

    public List<IMenuItem> newMenuItemList() {
	List<IMenuItem> list = new ArrayList<IMenuItem>();

	list.add(new MenuItem("Item with icon", KendoIcon.CLOCK));
	list.add(new MenuItem("Change the title") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
		this.setTitle(Model.of("Title changed!"));

	    }
	});
	list.add(new MenuItem("Tab1") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
		//
		tab1.setVisible(true);
		tab2.setVisible(false);
		tab3.setVisible(false);
		panel.setVisible(false);
		target.add(tab1, tab2, tab3, panel);
	    }
	});
	list.add(new MenuItem("Tab2") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
		tab1.setVisible(false);
		tab2.setVisible(true);
		tab3.setVisible(false);
		panel.setVisible(false);
		target.add(tab1, tab2, tab3, panel);
	    }
	});
	list.add(new MenuItem("Tab3") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
		tab1.setVisible(false);
		tab2.setVisible(false);
		tab3.setVisible(true);
		panel.setVisible(false);
		target.add(tab1, tab2, tab3, panel);
	    }
	});
	list.add(new MenuItem("Menu item, with sub-menu", newSubMenuList()));
	list.add(new MenuItem("Desactivate me") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {

		tab1.setVisible(false);
		tab2.setVisible(false);
		tab3.setVisible(false);
		panel.setVisible(true);
		target.add(tab1, tab2, tab3, panel);

	    }
	});

	return list;
    }

    public List<IMenuItem> newSubMenuList() {
	List<IMenuItem> list = new ArrayList<IMenuItem>();

	list.add(new MenuItem("Sub-menu #1"));
	list.add(new MenuItem("Sub-menu #2"));
	list.add(new MenuItem("Sub-menu #3"));

	return list;
    }

    public HomePage(final PageParameters parameters) {
	super(parameters);
	Label pageTitle = new Label("pageTitle", "Login Page");
	this.add(pageTitle);

	Menu menu = new Menu("menu", newMenuItemList());
	add(menu);

	panel = new FirstPanel("panel");
	panel.setOutputMarkupId(true).setVisible(false)
		.setOutputMarkupPlaceholderTag(true);
	add(panel);

	tab1 = new TabPanel1("tab1");
	tab1.setOutputMarkupId(true).setVisible(false)
		.setOutputMarkupPlaceholderTag(true);
	add(tab1);

	tab2 = new TabPanel2("tab2");
	tab2.setOutputMarkupId(true).setVisible(false)
		.setOutputMarkupPlaceholderTag(true);
	add(tab2);

	tab3 = new TabPanel3("tab3");
	tab3.setOutputMarkupId(true).setVisible(false)
		.setOutputMarkupPlaceholderTag(true);
	add(tab3);

    }

    private static class TabPanel1 extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 */
	public TabPanel1(String id) {
	    super(id);
	}

    }

    private static class TabPanel2 extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 */
	public TabPanel2(String id) {
	    super(id);
	}

    }

    private static class TabPanel3 extends Panel {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 */
	public TabPanel3(String id) {
	    super(id);
	}

    }

}
