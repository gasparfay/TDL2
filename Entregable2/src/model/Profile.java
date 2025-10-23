package model;

import java.util.*;

public class Profile {

    private int profileId;
    private String name;
    private int accountId;
    private List<Review> profileReviews;

	public Profile(String name) {
        this.name = name;
    }

	public int  getProfileId() {
		this.accountId=0;
		return profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public List<Review> getProfileReviews() {
        return profileReviews;
    }
}
