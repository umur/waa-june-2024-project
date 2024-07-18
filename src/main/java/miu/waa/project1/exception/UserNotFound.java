package miu.waa.project1.exception;

public class UserNotFound extends RuntimeException {
	String msg;

	public UserNotFound() {
		this.msg = "User not found!";
	}

	public UserNotFound(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
