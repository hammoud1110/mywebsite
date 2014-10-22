/**
 * 
 * @author Hammoud
 * @version 1.0
 */
package com.mywebsite.models;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
	private List entries;
	
	public static PhoneBook global = new PhoneBook();
	
	static{
		
		global.addEntry(new PhoneBookEntry(0, "Angela", "Merkel", "111111"));
		global.addEntry(new PhoneBookEntry(1, "Bill", "Gates", "111222"));
		global.addEntry(new PhoneBookEntry(2, "Michael", "Schumacher", "654321"));
		global.addEntry(new PhoneBookEntry(3, "Thomas", "Mueller", "999001"));
		global.addEntry(new PhoneBookEntry(4, "Erich", "Gamma", "554433"));
		global.addEntry(new PhoneBookEntry(5, "Firnando", "Tores", "888777"));
	}

	public static PhoneBook getGlobal() {
		return global;
	}

	public static void setGlobal(PhoneBook global) {
		PhoneBook.global = global;
	}

	public PhoneBook() {
		entries = new ArrayList();
	}

	public void addEntry(PhoneBookEntry entry) {
		entries.add(entry);
	}
	
	
	
	public List getEntries() {
		return entries;
	}
}