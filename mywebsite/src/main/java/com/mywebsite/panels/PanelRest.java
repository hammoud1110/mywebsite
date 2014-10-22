/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.panels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mywebsite.models.Product;
import com.mywebsite.pages.FirstPage;

public class PanelRest extends Panel implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    final String P01 = "Ein Bleistift ist ein Schreibgerät mit einer Mine, die in einen Schaft "
	    + "eingebettet ist. Das bis ins späte 18. Jahrhundert zur Herstellung der Mine "
	    + "verwendete Graphit wurde irrtümlich für das Bleierz Galenit (Bleiglanz) gehalten. "
	    + "Die sich daraus ergebende Bezeichnung „Bleistift“ (umgangssprachlich auch manchmal Bleier) "
	    + "hat bis heute überdauert. Seit dem 19. Jahrhundert wird die Mine aus einem Graphit-Ton-Gemisch "
	    + "gebrannt. Der Schaft wird in der Regel aus Holz gefertigt, häufig aus dem Zedernholz genannten Holz "
	    + "des Virginischen Wacholders. Geläufig sind auch Druckbleistifte und Fallminenstifte mit Kunststoff- oder "
	    + "Metallmantel. (Wikipedia)";

    final String P02 = "Ein Radiergummi (ugs. auch Radierer, Ratzefummel) ist ein besonders zubereiteter Gummi, mit dem "
	    + "mit einem Bleistift oder mit Tinte erstellte Striche von einem Trägermedium (zumeist Papier) entfernt werden. "
	    + "Es wird zwischen Kautschuk- und Kunststoffradierern unterschieden. (Wikipedia)";

    final String P03 = "Der Kugelschreiber ist ein Schreibgerät, das eine Tintenpaste mittels einer Kugel auf Papier "
	    + "überträgt. Er geht auf Patente des 19. Jahrhunderts zurück.[1] Die umgangssprachliche Kurzform Kuli "
	    + "bezeichnete ursprünglich den 1928 von Rotring entwickelten Tintenkuli. Seit dessen Bedeutungsverlust "
	    + "und dem Siegeszug des Kugelschreibers in den 1940er Jahren weltweit wird „Kuli“ im Deutschen allgemein "
	    + "für den Kugelschreiber benutzt. (Wikipedia)";

    public PanelRest(String id) {
	super(id);

	List<Product> catalog = new ArrayList<Product>();

	catalog.add(new Product("p01", "Bleistift", P01, 1.20));
	catalog.add(new Product("p02", "Radiergummi", P02, 2.00));
	catalog.add(new Product("p03", "Kugelschreiber", P03, 3.50));
	ListView<Product> eachProduct = new ListView<Product>("eachProduct",
		catalog) {
	    /**
			 * 
			 */
	    private static final long serialVersionUID = 1L;

	    protected void populateItem(ListItem<Product> item) {
		final Product p = (Product) item.getModelObject();
		item.add(new Label("id", new PropertyModel(p, "id")));
		// item.add(new Label("name", new PropertyModel(p, "name")));
		item.add(new Label("price", new PropertyModel(p, "price")));
		
		Link detailsLink = new Link("detailsLink") {
		    /**
					 * 
					 */
		    private static final long serialVersionUID = 1L;

		    public void onClick() {
			ProductDetails details = new ProductDetails(p);
			// setResponsePage(details);
			findParent(FirstPage.class).setResponsePage(details);
			
		    }
		};
		
		detailsLink
			.add(new Label("name", new PropertyModel(p, "name")));
		item.add(detailsLink);
	    }
	};
	add(eachProduct);
    }

}
