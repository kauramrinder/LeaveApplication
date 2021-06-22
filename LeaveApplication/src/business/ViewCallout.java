package business;

public class ViewCallout {

	//declaration of variables
	private String callId, reason, shift , noOfCallouts , date, employeeId ;

	//constructor
	public ViewCallout(String callId, String reason, String shift , String noOfCallouts , String date,String  employeeId) {
		this.callId = callId;
		this.reason = reason;
		this.shift = shift;
		this.noOfCallouts = noOfCallouts;
		this.date = date;
		this.employeeId = employeeId;

	}


	//get method
	public String getCallId() {
		return callId;
	}

	public String getReason() {
		return reason;
	}
	public String getShift() {
		return shift;
	}

	public String getNoOfCallouts() {
		return noOfCallouts;
	}

	public String getDate() {
		return date;
	}

	public String getEmpId() {
		return employeeId;
	}


	//set methods
	public void setCallId(String callId) {
		this.callId = callId;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setNoOfCallouts(String noOfCallouts) {
		this.noOfCallouts = noOfCallouts;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setEmpId(String employeeId) {
		this.employeeId = employeeId;
	}

}
