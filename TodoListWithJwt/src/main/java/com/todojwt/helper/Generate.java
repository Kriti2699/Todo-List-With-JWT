package com.todojwt.helper;


public class Generate {
	
	public String generateId() {
		
		 long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
         String id = String.format("%10d", number);
		return id;
		
	}

}
