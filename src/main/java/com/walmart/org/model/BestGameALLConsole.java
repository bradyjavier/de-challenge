package com.walmart.org.model;

import java.util.Date;

public class BestGameALLConsole {

	private int Metascore;
	private String NameConsole;
	private String NameGame;
	private float Userscore;
	private Date fecha;
	private int RANK;
	private String NameCompany;
	
	public BestGameALLConsole(int metascore, String nameConsole, String nameGame, float userscore, Date fecha, int rANK,
			String nameCompany) {
		super();
		Metascore = metascore;
		NameConsole = nameConsole;
		NameGame = nameGame;
		Userscore = userscore;
		this.fecha = fecha;
		RANK = rANK;
		NameCompany = nameCompany;
	}
	public BestGameALLConsole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNameCompany() {
		return NameCompany;
	}
	public void setNameCompany(String nameCompany) {
		NameCompany = nameCompany;
	}
	public int getRANK() {
		return RANK;
	}
	public void setRANK(int rANK) {
		RANK = rANK;
	}
	public int getMetascore() {
		return Metascore;
	}
	public void setMetascore(int metascore) {
		Metascore = metascore;
	}
	public String getNameConsole() {
		return NameConsole;
	}
	public void setNameConsole(String nameConsole) {
		NameConsole = nameConsole;
	}
	public String getNameGame() {
		return NameGame;
	}
	public void setNameGame(String nameGame) {
		NameGame = nameGame;
	}
	public float getUserscore() {
		return Userscore;
	}
	public void setUserscore(float userscore) {
		Userscore = userscore;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "BestGameALLConsole [Metascore=" + Metascore + ", NameConsole=" + NameConsole + ", NameGame=" + NameGame
				+ ", Userscore=" + Userscore + ", fecha=" + fecha + "]";
	}
	
	

}
