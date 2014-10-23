/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.panels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;



import com.mywebsite.HomePage;
import com.mywebsite.entity.ReminderController;
import com.mywebsite.entity.Stadt;
import com.mywebsite.models.ReminderBean;

public class PanelHomePage extends Panel implements Serializable {

    
    public boolean connect = false;
	// private static int counter=0;
	public List<ReminderBean> rBeanList = new ArrayList<ReminderBean>();
	private final Form form;
	ReminderBean rBean = new ReminderBean();

    public PanelHomePage(String id) {
	super(id);
	ReminderController.connect();
	form = new Form("form");
	add(form).setOutputMarkupPlaceholderTag(true).setOutputMarkupId(true);

	ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(
			form);

	FeedbackPanel feedback = new FeedbackPanel("feedback", filter);
	// feedback.setEscapeModelStrings(false);
	form.add(feedback);

	final TextField<String> tfName = new TextField<String>("tfName",
			new PropertyModel<String>(rBean, "tfName"));

	tfName.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});

	final TextField<String> tfAdresse = new TextField<String>("tfAdresse",
			new PropertyModel<String>(rBean, "tfAdresse"));
	tfAdresse.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});

	// TextField<String> tfStadt = new TextField<String>("tfStadt",
	// new PropertyModel<String>(rBean, "tfStadt"));

	final TextField<String> tfFaxNb = new TextField<String>("tfFaxNb",
			new PropertyModel<String>(rBean, "tfFaxNb"));

	tfFaxNb.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});

	final TextField<String> tfPhone = new TextField<String>("tfPhone",
			new PropertyModel<String>(rBean, "tfPhone"));

	tfPhone.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});
	final TextField<String> tfHandy = new TextField<String>("tfHandy",
			new PropertyModel<String>(rBean, "tfHandy"));
	tfHandy.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});
	final TextField<String> tfEmail = new TextField<String>("tfEmail",
			new PropertyModel<String>(rBean, "tfEmail"));

	tfEmail.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});
	final DropDownChoice tfStadt = new DropDownChoice("tfStadt",
			new PropertyModel<String>(rBean, "ddStadt"),
			Stadt.getStringValues());

	tfStadt.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});

	final TextArea<String> tfNotiz = new TextArea<String>("tfNotiz",
			new PropertyModel<String>(rBean, "tfNotiz"));
	tfNotiz.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});

	final DateTextField dateTextField = new DateTextField("dateTextField",
			new PropertyModel<Date>(rBean, "datePicker"), "dd.MM.yyyy");
	DatePicker datePicker = new DatePicker();
	datePicker.setShowOnFieldClick(true);
	datePicker.setAutoHide(true);
	dateTextField.add(datePicker);
	dateTextField.add(new OnChangeAjaxBehavior() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void onUpdate(AjaxRequestTarget target) {

		}
	});

	form.add(tfName, tfAdresse, tfStadt, tfFaxNb, tfPhone, tfHandy, tfEmail);
	form.add(tfNotiz);
	form.add(dateTextField);
	form.add(new AjaxLink("saveButton") {

		private static final long serialVersionUID = 1L;

		@Override
		public void onClick(AjaxRequestTarget target) {

//			ReminderController.save(rBean);
			 ReminderController.speichern(rBean);

			form.info("Der Eintrag wurde gespeichert");
			findParent(HomePage.class).getForm().info("Der Eintrag wurde gespeichert");
			target.add(findParent(HomePage.class));
		}
	});

	form.add(new AjaxLink("neuerEintrag") {

		private static final long serialVersionUID = 1L;

		@Override
		public void onClick(AjaxRequestTarget target) {
			rBean.clearBean();
			// target.add(tfName, tfAdresse, tfStadt, tfFaxNb, tfPhone,
			// tfHandy, tfEmail, tfNotiz, dateTextField);
			target.add(findParent(HomePage.class));
		}
	});
    }
    
}

	