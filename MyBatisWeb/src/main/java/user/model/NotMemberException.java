package user.model;

//사용자 정의 예외
public class NotMemberException extends Exception {
	
	public NotMemberException() {
		super("NotMemberException");
	}
	public NotMemberException(String msg) {
		super(msg);
	}
}/////////////////////////////////
