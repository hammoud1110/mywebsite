package com.mywebsite.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Stadt {
	
	BERLIN("Berlin"),
	HAMBURG("Hamburg"), 
	MUENCHEN("München"), 
	FRANKFURT("Frankfurt"), 
	KOELN("Köln"), 
	LEINZIG("Leipzig");


	private String stadt;

	private Stadt(String s) {
		stadt = s;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getStadt() {
		return stadt;
	}

	public static List<String> getStringValues() {
		List<String> stringValues = new ArrayList<String>();
		for (Stadt stadt : Arrays.asList(Stadt.values())) {
			stringValues.add(stadt.getStadt());
			
		}

		return stringValues;
	}

}
