package model;

import java.util.*;

public class Profile {

    private int profileId;
    private String name;
    private List<Review> profileReviews;

	public Profile(String name) {
	    
        this.name = name;
    }

	public int  getProfileId() {
		return profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getProfileReviews() {
        return profileReviews;
    }
}
