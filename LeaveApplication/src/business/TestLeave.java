package business;

public class TestLeave {

	//declaration of variables
	private String reason,to,from;

	//constructor
	public TestLeave(String reason, String from, String to) {
		this.reason = reason;
		this.from = from;
		this.to = to;
	}


	//get methods
	public String getReason() {
		return reason;
	}
	public String getTo() {
		return to;
	}
	public String getFrom() {
		return from;
	}


	//set methods
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setFrom(String from) {
		this.from = from;
	}


}

