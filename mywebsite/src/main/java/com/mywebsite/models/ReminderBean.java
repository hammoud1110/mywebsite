/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mywebsite.entity.ReminderController;

@Entity 
public class ReminderBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String tfDate;

	private String tfTime;

	private String tfTask;

	private String tfName;

	private String tfAdresse;

	private String ddStadt;

	private String tfEmail;

	private String tfNotiz;

	private Date datePicker;

	private String tfPhone;

	private String tfFaxNb;

	private String tfHandy;

	public void clearBean() {
		// NAME,STADT,AGE,ADDRESS,FAX, HANDY, TELEFON, EMAIL, NOTIZ
		this.setTfName("");
		this.setTfAdresse("");
		this.setDdStadt(null);
		this.setDatePicker(ReminderController.convertStrinfToDate(""));
		this.setTfEmail("");
		this.setTfFaxNb("");
		this.setTfHandy("");
		this.setTfPhone("");
		this.setTfNotiz("");

	}

	@Column(name = "TELEFON")
	public String getTfPhone() {
		return tfPhone;
	}

	public void setTfPhone(String tfPhone) {
		this.tfPhone = tfPhone;
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "DATE")
	public String getTfDate() {
		return tfDate;
	}

	public void setTfDate(String tfDate) {
		this.tfDate = tfDate;
	}

	@Column(name = "TIME")
	public String getTfTime() {
		return tfTime;
	}

	public void setTfTime(String tfTime) {
		this.tfTime = tfTime;
	}

	@Column(name = "TASK")
	public String getTfTask() {
		return tfTask;
	}

	public void setTfTask(String tfTask) {
		this.tfTask = tfTask;
	}

	@Column(name = "FAX")
	public String getTfFaxNb() {
		return tfFaxNb;
	}

	public void setTfFaxNb(String tfFaxNb) {
		this.tfFaxNb = tfFaxNb;
	}

	@Column(name = "NAME")
	public String getTfName() {
		return tfName;
	}

	public void setTfName(String tfName) {
		this.tfName = tfName;
	}

	@Column(name = "ADRESSE")
	public String getTfAdresse() {
		return tfAdresse;
	}

	public void setTfAdresse(String tfAdresse) {
		this.tfAdresse = tfAdresse;
	}

	@Column(name = "EMAIL")
	public String getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(String tfEmail) {
		this.tfEmail = tfEmail;
	}

	@Column(name = "NOTIZ")
	public String getTfNotiz() {
		return tfNotiz;
	}

	public void setTfNotiz(String tfNotiz) {
		this.tfNotiz = tfNotiz;
	}

	@Column(name = "HANDY")
	public String getTfHandy() {
		return tfHandy;
	}

	public void setTfHandy(String tfHandy) {
		this.tfHandy = tfHandy;
	}

	@Column(name = "GEBURTSDATUM")
	public Date getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(Date datePicker) {
		this.datePicker = datePicker;
	}

	@Column(name = "STADT")
	public String getDdStadt() {
		return ddStadt;
	}

	public void setDdStadt(String ddStadt) {
		this.ddStadt = ddStadt;
	}
}
