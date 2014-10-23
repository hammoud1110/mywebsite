/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.mywebsite.entity.ReminderController;
import com.mywebsite.models.ReminderBean;


public class PanelPhoneBook extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReminderBean> rBeanList = new ArrayList<ReminderBean>();
	private ListView<ReminderBean>  eachEntry;
	private final Form form;
	public PanelPhoneBook(String id) {
		super(id);
		form = new Form("form");
		add(form).setOutputMarkupPlaceholderTag(true);
		
		// PhoneBook.getGlobal() .getEntries()
//		rBeanList=ReminderController.load(rBeanList);
		rBeanList=ReminderController.laden(rBeanList);
		eachEntry = new ListView<ReminderBean> ("eachEntry", rBeanList) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "deprecation" })
			protected void populateItem(ListItem<ReminderBean>  item) {
//				item.setModel(new CompoundPropertyModel(item.getModelObject()));
				ReminderBean r = (ReminderBean) item.getModelObject();
				item.add(new AttributeModifier("class", true, new Model(item
						.getIndex() % 2 == 0 ? "even" : "odd")));
				item.add(new Label("name",r.getTfName()));
				item.add(new Label("stadt",r.getDdStadt()));
				item.add(new Label("telNo",r.getTfPhone()));
				item.add(new Label("email",r.getTfEmail()));
				item.add(createDeleteButton(item));
			}

		};
		
		form.add(new AjaxLink("refreshLink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
//			    rBeanList=ReminderController.load(rBeanList);
				rBeanList=ReminderController.laden(rBeanList);
				target.add(form);
				
			}
		});
		
	
		form.add(eachEntry);
	}

	public Form createDeleteButton(final ListItem item) {

		Form deleteForm = new Form("deleteForm");
		deleteForm.add(new AjaxLink("deleteLink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				
				ReminderBean rBean = (ReminderBean) item.getModelObject();
				rBeanList.remove(rBean);
				ReminderController.delete(rBean);
				ReminderController.loeschen(rBean);
				target.add(form);
			}
		});
		return deleteForm;

	}

}