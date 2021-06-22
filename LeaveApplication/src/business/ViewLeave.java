package business;

public class ViewLeave {

	//declaration of variables
	private String Applicant,AppId, Status, Cause;

	//constructor
	public ViewLeave(String Applicant, String AppId,String Status, String Cause) {
		this.Applicant = Applicant;
		this.AppId = AppId;
		this.Status=Status;
		this.Cause=Cause;
	}


	//overloading constructor
	public ViewLeave(String Applicant) {
		this.Applicant = Applicant;
	}


	//get methods
	public String getApplicant() {
		return Applicant;
	}
	public String getAppId() {
		return AppId;
	}

	public String getStatus() {
		return Status;
	}

	public String getCause() {
		return Cause;
	}


	//set methods
	public void setApplicant(String Applicant) {
		this.Applicant = Applicant;
	}
	public void setAppId(String AppId) {
		this.AppId = AppId;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	public void setCause(String Cause) {
		this.Cause =Cause;
	}


}

