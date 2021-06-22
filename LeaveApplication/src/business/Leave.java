package business;

public class Leave {
	
	//declaration of variables
	private String Applicant, ApplicationId, Cause;

	//constructor
	public Leave(String Applicant, String ApplicationId,String Cause) {
		this.Applicant = Applicant;
		this.ApplicationId = ApplicationId;
		this.Cause=Cause;

	}


	//get methods
	public String getApplicant() {
		return Applicant;
	}
	public String getApplicationId() {
		return ApplicationId;
	}

	public String getCause() {
		return Cause;
	}


	//set methods
	public void setApplicant(String Applicant) {
		this.Applicant = Applicant;
	}
	public void setApplicationId(String ApplicationId) {
		this.ApplicationId = ApplicationId;
	}

	public void setCause(String Cause) {
		this.Cause =Cause;
	}



}
