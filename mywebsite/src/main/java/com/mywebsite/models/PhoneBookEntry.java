/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.models;

import java.io.Serializable;

public class PhoneBookEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String telNo;

	public PhoneBookEntry(int id, String firstName, String lastName,
			String telNo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telNo = telNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTelNo() {
		return telNo;
	}
}