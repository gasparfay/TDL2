package model;

import java.util.*;

public class Account {
	private int accId;
	private String email;
	private String password;
	private List<Review> accountReviews;

    public Account(String email, String pwd) {
        this.email = email;
        this.password = pwd;
		accountReviews = new ArrayList<>();
    }

	
	public int getAccId() {
		return accId;
	}


	public void setAccId(int accId) {
		this.accId = accId;
	}


	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public List<Review> getAccountReviews() {
		return accountReviews;
	}
	
	public void setAccountReviews(List<Review> accountReviews) {
		this.accountReviews = accountReviews;
	}

}
