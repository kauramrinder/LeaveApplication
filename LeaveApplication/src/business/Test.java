package business;

public class Test {

	//declaration of variables
	private String fName, lName,dob, empID, compName,position,password,reEnterPass;

	//constructor
	public Test(String fName, String lName,String  dob, String empID, String compName, String position, String password,String reEnterPass) {
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.empID = empID;
		this.compName = compName;
		this.position = position;
		this.password = password;
		this.reEnterPass = reEnterPass;
	}


	//get methods
	public String getFname() {
		return fName;
	}
	public String getLname() {
		return lName;
	}
	public String getDob() {
		return dob;
	}
	public String getEmpID() {
		return empID;
	}
	public String getCompName() {
		return compName;
	}
	public String getPosition() {
		return position;
	}
	public String getPassword() {
		return password;
	}
	public String getReEnterPass() {
		return reEnterPass;
	}

	//set methods
	public void setFname(String fName) {
		this.fName = fName;
	}
	public void setLname(String lName) {
		this.lName = lName;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setPassword(String password) {
		this.password =password;
	}
	public void setReEnterPass(String reEnterPass) {
		this.reEnterPass = reEnterPass;
	}



}

