package business;

public class TestCall {

	//declaration of variables
	private String reason,shift;

	//constructor
	public TestCall(String reason, String shift) {
		this.reason = reason;
		this.shift = shift;
	}

	//get methods
	public String getReason() {
		return reason;
	}
	public String getShift() {
		return shift;
	}


	//set methods
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}


}

