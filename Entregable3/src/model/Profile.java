package model;

public class Profile {

    private int profileId;
    private String name;
	private Account account;
	private boolean neverLogIn;

	public Profile(String name) {
		neverLogIn = true;
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

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}
	
	public boolean getNeverLogIn() {
		return neverLogIn;
	}
	public void setNeverLogIn(boolean neverLogIn) {
		this.neverLogIn = neverLogIn;
	}
}
