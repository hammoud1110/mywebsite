/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.model.Model;

import wicket.contrib.tinymce.TinyMceBehavior;
import wicket.contrib.tinymce.settings.Button;
import wicket.contrib.tinymce.settings.TinyMCESettings;

import com.googlecode.wicket.jquery.ui.plugins.wysiwyg.WysiwygEditor;
import com.googlecode.wicket.jquery.ui.plugins.wysiwyg.toolbar.IWysiwygToolbar;
import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;
import com.mywebsite.HomePage;
import com.mywebsite.entity.MenuListFactory;
import com.mywebsite.models.Product;
import com.mywebsite.pages.FirstPage;

public class ProductDetails extends WebPage {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private ContextImage bleistiftImage;
    private ContextImage RadiergummiImage;
    private ContextImage kugelschreiberImage;
    private MenuListFactory listFactory = new MenuListFactory();

    public ProductDetails(Product p) {

	Menu menu = new Menu("menu", listFactory.newMenuItemList());
	add(menu);

	add(new Label("title", p.getName()));
	add(new Label("heading", p.getName()));
	add(new Label("desc", p.getDesc()));
	
	
	
	add(creatTextarea());
	// add(new RichTextEditor("textArea")); TinyMceBehavior()

	add(bleistiftImage = new ContextImage("bleistiftImage", "bleistift.PNG"));
	add(RadiergummiImage = new ContextImage("RadiergummiImage",
		"radiergummi.PNG"));
	add(kugelschreiberImage = new ContextImage("kugelschreiberImage",
		"kugelschreiber.PNG"));

	if (p.getName().equals("Bleistift")) {
	    bleistiftImage.setVisible(true);
	    RadiergummiImage.setVisible(false);
	    kugelschreiberImage.setVisible(false);
	}
	if (p.getName().equals("Radiergummi")) {
	    bleistiftImage.setVisible(false);
	    RadiergummiImage.setVisible(true);
	    kugelschreiberImage.setVisible(false);
	}
	if (p.getName().equals("Kugelschreiber")) {
	    bleistiftImage.setVisible(false);
	    RadiergummiImage.setVisible(false);
	    kugelschreiberImage.setVisible(true);
	}

	add(new Label("preis", "Preis: " + p.getPrice() + " €"));

	Form form = new Form("form");
	add(form);
	form.add(new AjaxLink("kaufButton") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
	    }
	});

	form.add(new AjaxLink("einkaufButton") {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void onClick(AjaxRequestTarget target) {
	    }
	});

    }

    private TextArea<String> creatTextarea() {
	TinyMCESettings settings = new TinyMCESettings(
		TinyMCESettings.Theme.advanced);
	settings.add(Button.newdocument, TinyMCESettings.Toolbar.first,
		TinyMCESettings.Position.before);
	settings.add(Button.separator, TinyMCESettings.Toolbar.first,
		TinyMCESettings.Position.before);
	settings.add(Button.fontselect, TinyMCESettings.Toolbar.first,
		TinyMCESettings.Position.after);
	TextArea<String> textArea = new TextArea<String>("textArea",new Model("kyly bata"));
	

	textArea.add(new TinyMceBehavior(settings));
	return textArea;
    }

    public List<IMenuItem> newMenuItemList() {
	List<IMenuItem> list = new ArrayList<IMenuItem>();

	list.add(new MenuItem("Home", KendoIcon.PLUS) {

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

	list.add(new MenuItem("Tools", listFactory.newSubMenuList()));

	return list;
    }

}