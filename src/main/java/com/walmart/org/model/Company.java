package com.walmart.org.model;

public class Company {
	
	private int Company_id;
	private String NameCompany;
	
	public Company() {
		super();
	}

	public Company(int company_id, String nameCompany) {
		super();
		Company_id = company_id;
		NameCompany = nameCompany;
	}
	
	public int getCompany_id() {
		return Company_id;
	}

	public void setCompany_id(int company_id) {
		Company_id = company_id;
	}

	public String getNameCompany() {
		return NameCompany;
	}

	public void setNameCompany(String nameCompany) {
		NameCompany = nameCompany;
	}

	@Override
	public String toString() {
		return "Company [Company_id=" + Company_id + ", NameCompany=" + NameCompany + "]";
	}
	
	
	

}
