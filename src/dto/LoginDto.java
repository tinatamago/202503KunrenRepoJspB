package dto;

public class LoginDto {
	private String user_id;
	private String user_pass;

	public LoginDto() {

	}

	public LoginDto(String user_id, String user_pass) {
		this.user_id = user_id;
		this.user_pass = user_pass;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public void setUserPass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getUserId() {
		return this.user_id;
	}

	public String getUserPass() {
		return this.user_pass;
	}

}
