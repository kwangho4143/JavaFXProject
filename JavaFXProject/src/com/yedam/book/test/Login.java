package com.yedam.book.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Login {
	private SimpleStringProperty id;
	private SimpleStringProperty password;
	
	public Login(String id, String password) {
		this.id = new SimpleStringProperty(id);
		this.password = new SimpleStringProperty(password);
	}
	public String getId() {
		return this.id.get();
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public String getPassword() {
		return this.password.get();
	}
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	
	
	
	
	
}
