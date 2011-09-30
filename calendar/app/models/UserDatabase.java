package models;

import java.util.ArrayList;

public class UserDatabase {
	
	private ArrayList<User> users;
	
	public UserDatabase() {
		this.users = new ArrayList<User>();
	}

	@ForTestingOnly
	public ArrayList<User> getUsers() {
		return this.users;
	}

}