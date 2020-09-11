package com.yedam.book.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleStringProperty bookname;
	private SimpleStringProperty bookuser;
	private SimpleStringProperty company;
	private SimpleIntegerProperty price;

	public Book(String bookname, String bookuser, String company, int price) {
		this.bookname = new SimpleStringProperty(bookname);
		this.bookuser = new SimpleStringProperty(bookuser);
		this.company = new SimpleStringProperty(company);
		this.price = new SimpleIntegerProperty(price);
	}
	public String getBookname() {
		return this.bookname.get();
	}
	public void setBookname(String bookname) {
		this.bookname.set(bookname);
	}
	public String getBookuser() {
		return this.bookuser.get();
	}
	public void setBookuser(String bookuser) {
		this.bookuser.set(bookuser);
	}
	public String getCompany() {
		return this.company.get();
	}
	public void setCompany(String company) {
		this.company.set(company);
	}
	public int getPrice() {
		return this.price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}












}

