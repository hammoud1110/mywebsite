/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.googlecode.wicket.kendo.ui.KendoIcon;
import com.googlecode.wicket.kendo.ui.widget.menu.Menu;
import com.googlecode.wicket.kendo.ui.widget.menu.item.IMenuItem;
import com.googlecode.wicket.kendo.ui.widget.menu.item.MenuItem;
import com.mywebsite.HomePage;
import com.mywebsite.models.Product;
import com.mywebsite.pages.CalculatorPage;
import com.mywebsite.pages.FirstPage;

public class ProductDetails extends WebPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ContextImage bleistiftImage;
	ContextImage RadiergummiImage;
	ContextImage kugelschreiberImage;
	
	
	public ProductDetails(Product p) {
	    
	    Menu menu = new Menu("menu", newMenuItemList());
		add(menu);
		
		
		add(new Label("title", p.getName()));
		add(new Label("heading", p.getName()));
		add(new Label("desc",p.getDesc()));
		add(bleistiftImage = new ContextImage("bleistiftImage","bleistift.PNG"));
		add(RadiergummiImage = new ContextImage("RadiergummiImage","radiergummi.PNG"));
		add(kugelschreiberImage = new ContextImage("kugelschreiberImage","kugelschreiber.PNG"));
		
			if(p.getName().equals("Bleistift")){
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
		
			add(new Label("preis", "Preis: "+p.getPrice()+ " €"));
			
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