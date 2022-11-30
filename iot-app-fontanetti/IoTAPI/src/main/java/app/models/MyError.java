package app.models;

public class MyError {
	
	String errorName;
	String statusCode;
	
	public MyError() {}
	
	public String getName() {
		return errorName;
	}
	
	public void setName(String name) {
		this.errorName = name;
	}
	
	public String getStatus() {
		return statusCode;
	}
	
	public void setStatus(String status) {
		this.statusCode = status;
	}
}
