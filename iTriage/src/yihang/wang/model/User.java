package yihang.wang.model;

public class User {
	private String userFirstName;
	private String userId;
	private String userLastName;
	private String userName;
	private String userOrganization;
	private String userPassword;
	private String userPosition;

	public User() {
		userFirstName = "";
		userId = "";
		userLastName = "";
		userName = "";
		userOrganization = "";
		userPassword = "";
		userPosition = "";
	}

	public User(String newUserFirstName, String newUserId,
			String newUserLasttName, String newUserName,
			String newUserOrganization, String newUserPassword,
			String newUserPosition) {
		this.userFirstName = newUserFirstName;
		this.userId = newUserId;
		this.userLastName = newUserLasttName;
		this.userName = newUserName;
		this.userOrganization = newUserOrganization;
		this.userPassword = newUserPassword;
		this.userPosition = newUserPosition;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserOrganization() {
		return userOrganization;
	}

	public void setUserOrganization(String userOrganization) {
		this.userOrganization = userOrganization;
	}
}
