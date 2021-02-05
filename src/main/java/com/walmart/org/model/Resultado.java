package com.walmart.org.model;

import java.util.Date;

public class Resultado {
	
	private int ResultadoID;
	private String metascore;
	private String Name;
	private String Console_id;
	private String UserScore;
	private String Fecha;
	private String valor;
	
	private int metascore_convert;
	private float UserScore_convert;
	private Date Fecha_convert;
	
	public Resultado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resultado(int resultadoID, String metascore, String name, String console_id, String userScore,
			String fecha) {
		super();
		ResultadoID = resultadoID;
		this.metascore = metascore;
		Name = name;
		Console_id = console_id;
		UserScore = userScore;
		Fecha = fecha;
	}
	
	public Resultado(int resultadoID, String name, String console_id, int metascore_convert, float userScore_convert,
			Date fecha_convert, String  valor ) {
		super();
		ResultadoID = resultadoID;
		Name = name;
		Console_id = console_id;
		this.metascore_convert = metascore_convert;
		UserScore_convert = userScore_convert;
		Fecha_convert = fecha_convert;
		valor = valor;
	}
	
	
	@Override
	public String toString() {
		return "Resultado [ResultadoID=" + ResultadoID + ", metascore=" + metascore_convert + ", Name=" + Name + ", Console_id="
				+ Console_id + ", UserScore=" + UserScore_convert + ", Fecha=" + Fecha_convert + "]";
	}


	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getConsole_id() {
		return Console_id;
	}
	public void setConsole_id(String console_id) {
		Console_id = console_id;
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
	public int getMetascore_convert() {
		return metascore_convert;
	}
	public void setMetascore_convert(int metascore_convert) {
		this.metascore_convert = metascore_convert;
	}
	public float getUserScore_convert() {
		return UserScore_convert;
	}
	public void setUserScore_convert(float userScore_convert) {
		UserScore_convert = userScore_convert;
	}
	public Date getFecha_convert() {
		return Fecha_convert;
	}
	public void setFecha_convert(Date fecha_convert) {
		Fecha_convert = fecha_convert;
	}
	
	
	
	
	

}
