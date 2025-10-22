package model;

public class Account {
	private int accId;
	private String email;
	private String password;
	private int profileAmount;
	//private Profile[] profiles;

    public Account(String email, String pwd) {
        this.email = email;
        this.password = pwd;
        this.profileAmount = 0;
        //this.profiles = new Profile[5];
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

	public int getProfileAmount() {
		return profileAmount;
	}

	public void setProfileAmount(int profileAmount) {
		this.profileAmount = profileAmount;
	}


	/*public Profile[] getProfiles() {
		return profiles;
	}*/

	/*public void addProfile(Profile p) {
		this.profiles[profileAmount] = p;
		this.profileAmount++;
	}*/

}
