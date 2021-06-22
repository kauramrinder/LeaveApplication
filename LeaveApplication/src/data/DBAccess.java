package data;

//importing necessary files
import java.sql.*;
import java.util.UUID;

import business.*;

//class for database handling
public class DBAccess {

	//creating objects
	protected Connection conn=null;
	protected ResultSet rs=null;
	protected Statement stm=null;
	private String noOfCall = null;

	//constructor
	public DBAccess() throws SQLException,ClassNotFoundException{
		this.connect();
	}

	//connect method to create a database connection
	public void connect() throws SQLException,ClassNotFoundException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url ="jdbc:oracle:thin:@CALVIN.HUMBER.CA:1521:GROK";
		String username = "N01328766";
		String password = "oracle";
		conn = DriverManager.getConnection(url,username,password);
		String sql = "Select * From RegisterTable";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);

	}

	//method to disconnect from databse
	public void disconnect() throws SQLException{
		if (!rs.isClosed()) {
			rs.close();
			conn.close();
		}
	}

	//refreshing methods to refresh each table separately
	public void refresh() throws SQLException{
		String sql = "Select * From RegisterTable";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);
		rs.first();
	}

	public void refresh1() throws SQLException{
		String sql = "Select * From CalloutTable";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);
		rs.first();
	}
	public void refresh2() throws SQLException{
		String sql = "Select * From LeaveTable";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);
		rs.first();
	}


	//registering new employee 
	//adding the employee details to the register table
	public int addEmployee(Test test) throws SQLException{
		int count=0;
		String query = "Insert into RegisterTable (fName, lName, DOB , empID, compName, empPosition, pass, reEnterPass) values (?, ?, ?, ?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, test.getFname());
		preparedStmt.setString(2, test.getLname());
		preparedStmt.setString(3, test.getDob());
		preparedStmt.setString(4, test.getEmpID());
		preparedStmt.setString(5, test.getCompName());
		preparedStmt.setString(6, test.getPosition());
		preparedStmt.setString(7, test.getPassword());
		preparedStmt.setString(8, test.getReEnterPass());
		preparedStmt.execute();
		this.refresh();
		return count;
	}

	//applying callout by an employee who is logged in
	//adding data to callout table
	public int addCallout(TestCall testCall) throws SQLException{
		int count=0;
		int totalCall= Integer.parseInt(noOfCall);

		//generating unique callID
		String ts = String.valueOf(System.currentTimeMillis()); ///14 dig
		String rand = UUID.randomUUID().toString(); // alpha 16
		String new_word = ts.substring(ts.length() - 3);  
		String new_word1 = rand.substring(rand.length() - 3);

		String callID = new_word + new_word1;

		String sql = "Insert into CalloutTable (callid, Reason, Shift, numOfCallouts, datee, empID) values (?, ?, ?, ?,Sysdate, ?)"; 
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, callID);
		pst.setString(2, testCall.getReason());
		pst.setString(3, testCall.getShift());
		pst.setInt(4, totalCall-1);
		pst.setString(5, Global.employeeID);
		pst.execute();
		this.refresh1();
		return count;
	}


	// finding number of callouts of an employee who is logged in
	//contains filtering of data from the table to retrieve desired data
	public String findNoOfCallout() throws SQLException{

		String query = "select t.numOfCallouts from CalloutTable t inner join ( select empID, max(datee) as MaxDate from CalloutTable group by empID) tm on t.empID = tm.empID and t.datee = tm.MaxDate where t.empID='" +Global.employeeID+ "'";

		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(query);

		if(rs.next()) {   
			noOfCall =rs.getString(1);   
		}

		return noOfCall;

	}

	//this method is required when an employee leaves the company, manager then deletes all of the employee's data making him/her unauthorized to log in again
	//deleting employee and its related data from all of the tables
	public int delEmployee(ViewLeave vleave) throws SQLException{
		int count=0;
		String sql = "delete from RegisterTable where empID= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, vleave.getApplicant());
		pst.execute();
		this.refresh();
		this.refresh1();
		this.refresh2();
		return count;
	}

	//manager is able to allocate total number of callouts a new employee can make in a year
	public int allocateCallout(ViewLeave vLeave) throws Exception{
		int count=0;

		//generating unique callID
		String ts = String.valueOf(System.currentTimeMillis()); ///14 dig
		String rand = UUID.randomUUID().toString(); // alpha 16
		String new_word = ts.substring(ts.length() - 3); 
		String new_word1 = rand.substring(rand.length() - 3);

		String callID = new_word + new_word1;

		String query = "Insert into CalloutTable (callid, Reason, Shift, numOfCallouts, datee, empID) values (? ,'New Employee','Morning', 10, sysdate, ?) ";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, callID);
		preparedStmt.setString(2, vLeave.getApplicant());
		preparedStmt.execute();
		this.refresh1();
		return count;

	}





	//applying leave by an employee
	public int addLeave(TestLeave testLeave) throws Exception{
		int count=0;
		//generating unique appID
		String ts = String.valueOf(System.currentTimeMillis()); ///14 dig
		String rand = UUID.randomUUID().toString(); // alpha 16
		String new_word = ts.substring(ts.length() - 3); 
		String new_word1 = rand.substring(rand.length() - 3);

		String appID = new_word + new_word1;
		String query = "Insert into LeaveTable(appID, Reason, FromDate, ToDate, appStatus, cause, datee, empID) values (?, ?, ?, ?,'NA','NA',sysdate, ?) ";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, appID);
		preparedStmt.setString(2, testLeave.getReason());
		preparedStmt.setString(3,testLeave.getFrom());
		preparedStmt.setString(4, testLeave.getTo());
		preparedStmt.setString(5, Global.employeeID);
		preparedStmt.execute();
		this.refresh2();
		return count;

	}



	//retrieving the details of leave applied by an employee
	public ViewLeave leaveAccess( String FromDate, String ToDate) throws Exception{

		String query = "select * from LeaveTable where empID=? and FromDate=? and ToDate=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, Global.employeeID);
		preparedStmt.setString(2,  FromDate);
		preparedStmt.setString(3,  ToDate);
		rs=preparedStmt.executeQuery();

		String applicant = null;
		String appid =  null;
		String status =  null;
		String cause =  null;

		if(rs.next()) {    
			applicant = rs.getString(8);
			appid = rs.getString(1);
			status = rs.getString(5);
			cause = rs.getString(6);
		}

		ViewLeave ll = new ViewLeave(applicant, appid, status,cause);
		return ll ;
	}


	//retrieving details of all callouts made on the current day
	//this functionality is only available to manager
	public ResultSet viewEmpCallouts() throws Exception{

		String query = "SELECT * FROM CalloutTable where trunc(datee)=trunc(sysdate)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		rs=preparedStmt.executeQuery(); 
		return rs ;

	}

	//retrieving details of all leaves made during the current week
	//this functionality is only available to manager
	public ResultSet viewEmpLeaves() throws Exception{

		String query = "SELECT * FROM LeaveTable where trunc(datee) between (sysdate-6) and sysdate";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		rs=preparedStmt.executeQuery(); 
		return rs ;

	}

	//accepting the leave applied by an employee
	//this functionality is only available to manager
	public int acceptLeave(Leave leave) throws Exception{
		int count=0;
		String query = "update LeaveTable set appStatus='Accepted',cause=? where appID=? and empID=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1,leave.getCause());
		preparedStmt.setString(2, leave.getApplicationId());
		preparedStmt.setString(3,leave.getApplicant());
		count=preparedStmt.executeUpdate();
		this.refresh2();
		return count;


	}

	//rejecting the leave applied by an employee
	//this functionality is only available to manager
	public int declineLeave(Leave leave) throws Exception{
		int count=0;
		String query = "update LeaveTable set appStatus='Declined',cause=? where appID=? and empID=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1,leave.getCause());
		preparedStmt.setString(2, leave.getApplicationId());
		preparedStmt.setString(3,leave.getApplicant());
		preparedStmt.execute();
		this.refresh2();
		return count++;


	}


}




