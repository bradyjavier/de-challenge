package com.walmart.org.model;


public class ResultadoConvert {
	
	private int ResultadoID;
	private String metascore;
	private int Name;
	private String Console;
	private String UserScore;
	private String Fecha;
	
	@Override
	public String toString() {
		return "ResultadoConvert [ResultadoID=" + ResultadoID + ", metascore=" + metascore + ", Name=" + Name
				+ ", Console=" + Console + ", UserScore=" + UserScore + ", Fecha=" + Fecha + "]";
	}
	
	public ResultadoConvert(int resultadoID, String metascore, int name, String console, String userScore,
			String fecha) {
		super();
		ResultadoID = resultadoID;
		this.metascore = metascore;
		Name = name;
		Console = console;
		UserScore = userScore;
		Fecha = fecha;
	}
	
	public ResultadoConvert() {
		super();
	}
	
	public int getResultadoID() {
		return ResultadoID;
	}
	
	public void setResultadoID(int resultadoID) {
		ResultadoID = resultadoID;
	}
	
	public String getMetascore() {
		return metascore;
	}
	
	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}
	
	public int getName() {
		return Name;
	}
	
	public void setName(int name) {
		Name = name;
	}
	
	public String getConsole() {
		return Console;
	}
	
	public void setConsole(String console) {
		Console = console;
	}
	
	public String getUserScore() {
		return UserScore;
	}
	
	public void setUserScore(String userScore) {
		UserScore = userScore;
	}
	
	public String getFecha() {
		return Fecha;
	}
	
	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	
}
