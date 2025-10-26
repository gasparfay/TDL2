package model;

import java.util.*;

public class Profile {
    private int profileId;
    private Account account;  // Nueva referencia a Account
    private String name;
    private int accountId;
    private List<Review> profileReviews;

    public Profile(String name, Account account) {  // Constructor modificado
        this.name = name;
        this.account = account;
    }

    public int getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< Updated upstream
	public int  getProfileId() {
		this.accountId=0;
		return profileId;
	}
=======
    public Account getAccount() {  // Nuevo getter
        return account;
    }
>>>>>>> Stashed changes

    public void setAccount(Account account) {  // Nuevo setter
        this.account = account;
    }

<<<<<<< Updated upstream
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
=======
    public List<Review> getProfileReviews() {
>>>>>>> Stashed changes
        return profileReviews;
    }
}
