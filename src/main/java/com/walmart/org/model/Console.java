package com.walmart.org.model;

public class Console {
	
	private int IdConsola;
	private String NameConsole;
	private String Company;
	public Console() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Console(int idConsola, String nameConsole, String company) {
		super();
		IdConsola = idConsola;
		NameConsole = nameConsole;
		Company = company;
	}
	public int getIdConsola() {
		return IdConsola;
	}
	public void setIdConsola(int idConsola) {
		IdConsola = idConsola;
	}
	public String getNameConsole() {
		return NameConsole;
	}
	public void setNameConsole(String nameConsole) {
		NameConsole = nameConsole;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	@Override
	public String toString() {
		return "Console [IdConsola=" + IdConsola + ", NameConsole=" + NameConsole + ", Company=" + Company + "]";
	}

	

	
	
	
	
}
