/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.panels;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class PanelCalc extends Panel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double result = 0;

	private String txt = "";
	private String toBeUsed = "";
	boolean flag = false;
	private String operation = "";

	String s = "";
	String aktuelleZahl = "";
	int zeichen = 0;
	double gesamt = 0;

	Model<String> strMdl = new Model<String>();// Model.of("Click Button to Calc");
	TextField<String> tfAusgabe = new TextField<String>("tfAusgabe", strMdl);

	public PanelCalc(String id) {
		super(id);

		final Form<?> form = new Form<Object>("form");
		add(form).setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);

		tfAusgabe.setOutputMarkupId(true);
		form.add(tfAusgabe);

		form.add(new AjaxLink<Object>("zero") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "0";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});

		form.add(new AjaxLink<Object>("one") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "1";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});

		form.add(new AjaxLink<Object>("two") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "2";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("three") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "3";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("four") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "4";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("five") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "5";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("six") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "6";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("seven") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "7";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("eight") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "8";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("nine") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + "9";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});

		form.add(new AjaxLink<Object>("plus") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (flag == false) {
//					System.out.println("+");
					toBeUsed = txt;
				}
				txt = "";
				operation = "+";

//				System.out.println("txt: " + txt);
			}
		});
		form.add(new AjaxLink<Object>("minus") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (flag == false) {
//					System.out.println("-");
					toBeUsed = txt;
				}
				txt = "";
				operation = "-";
			}
		});
		form.add(new AjaxLink<Object>("divide") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (flag == false) {
//					System.out.println("/");
					toBeUsed = txt;
				}
				txt = "";
				operation = "/";
			}
		});
		form.add(new AjaxLink<Object>("multiply") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (flag == false) {
//					System.out.println("X");
					toBeUsed = txt;
				}
				txt = "";
				operation = "X";
			}
		});
		form.add(new AjaxLink<Object>("comma") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				txt = txt + ".";
				strMdl.setObject(txt);
//				System.out.println("txt is " + txt);
				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("equal") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {

				if (operation.equals("/")) {
//					System.out.println("toBeUsed " + toBeUsed + " txt " + txt);
					result = Double.parseDouble(toBeUsed)
							/ Double.parseDouble(txt);
				} else if (operation.equals("+")) {
//					System.out.println("toBeUsed " + toBeUsed + " txt " + txt);
					result = Double.parseDouble(toBeUsed)
							+ Double.parseDouble(txt);
				} else if (operation.equals("X")) {
//					System.out.println("toBeUsed " + toBeUsed + " txt " + txt);
					result = Double.parseDouble(toBeUsed)
							* Double.parseDouble(txt);
				} else {
//					System.out.println("toBeUsed " + toBeUsed + " txt " + txt);
					result = Double.parseDouble(toBeUsed)
							- Double.parseDouble(txt);
				}
				toBeUsed = String.valueOf(result);
//				System.out.println("toBeUsed " + toBeUsed);
				strMdl.setObject(toBeUsed);
				flag = true;

				target.add(form);
			}
		});
		form.add(new AjaxLink<Object>("delete") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				//DELETE ONE CHAR
				if(txt.length() != 0){
					int laenge = txt.length() - 1;
					txt = txt.substring(0, laenge);
				}
				//METHODE BERECHNE NEU
				strMdl.setObject(txt);
				
				target.add(form);
			}
		});

		form.add(new AjaxLink<Object>("deleteAll") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {

				result = 0.0;
				strMdl.setObject("");
				txt = "";
				toBeUsed = "";
				flag = false;
				target.add(form);
			}

		});

	}

}
