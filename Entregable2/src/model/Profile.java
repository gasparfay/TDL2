package model;

import java.util.*;

public class Profile {

    private int profileId;
    private String name;
    private List<Review> profileReviews;
	private Account account;

	public Profile(String name) {
        this.name = name;
    }

	public int  getProfileId() {
		return profileId;
	}

	public String getName() {
		return name;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getProfileReviews() {
        return profileReviews;
    }

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}
}
