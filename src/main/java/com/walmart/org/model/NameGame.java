package com.walmart.org.model;

public class NameGame {
	
	private int NameGameID;
	private String NameGame;
	
	public NameGame() {
		super();
		// TODO Auto-generated constructor stub
	}


	public NameGame(int nameGameID, String nameGame) {
		super();
		NameGameID = nameGameID;
		NameGame = nameGame;
	}

	public int getNameGameID() {
		return NameGameID;
	}


	public void setNameGameID(int nameGameID) {
		NameGameID = nameGameID;
	}


	public String getNameGame() {
		return NameGame;
	}


	public void setNameGame(String nameGame) {
		NameGame = nameGame;
	}


	@Override
	public String toString() {
		return "NameGame [NameGameID=" + NameGameID + ", NameGame=" + NameGame + "]";
	}
	
	

}
