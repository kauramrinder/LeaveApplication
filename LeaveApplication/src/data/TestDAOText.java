package data;

//importing necessary packages
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import business.Test;

//implementing TestDAO interface
public class TestDAOText implements TestDAO {

	//creating an object of type File
	private File testFile = null;

	//constructor
	public TestDAOText() {
		testFile = new File(TestConstants.FILENAME_TEXT);
	}

	//checks the existence of file or creates it, if not present
	private void checkFile() throws IOException {
		if (!testFile.exists()) {
			testFile.createNewFile();
		}
	}

	//save data in file
	private boolean saveTest(ArrayList<Test> tests) {
		PrintWriter out = null;
		try {
			this.checkFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(testFile)));
			for (Test t : tests) {
				out.print(t.getFname() + FIELD_SEP);
				out.print(t.getLname() + FIELD_SEP);
				out.print(t.getDob() + FIELD_SEP);
				out.print(t.getEmpID() + FIELD_SEP);
				out.print(t.getCompName()+ FIELD_SEP);
				out.print(t.getPosition() + FIELD_SEP);
				out.print(t.getPassword() + FIELD_SEP);
				out.println(t.getReEnterPass());

			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			this.close(out);
		}
		return true;
	}

	//closing the flow to data stream
	private void close(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}


	//overriding abstract methods
	@Override
	public Test getTest(String empID, String password) {
		ArrayList<Test> tests = this.getTests();
		for (Test t1 : tests) {
			if (t1.getEmpID().equalsIgnoreCase(empID) && t1.getPassword().equals(password)) {
				return t1;
			}
		}
		return null;
	}

	//overriding abstract methods
	@Override
	public Test getTest(String empID, String password,String position) {
		ArrayList<Test> tests = this.getTests();
		for (Test t2 : tests) {
			if (t2.getEmpID().equalsIgnoreCase(empID) && t2.getPassword().equals(password) && t2.getPosition().equals(position)  ) {
				return t2;
			}
		}
		return null;
	}


	//Overriding method to read file
	@Override
	public ArrayList<Test> getTests() {
		BufferedReader in = null;
		ArrayList<Test> tests2 = new ArrayList<Test>();
		try {
			in = new BufferedReader(new FileReader(testFile));
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				String fName = columns[0];
				String lName = columns[1];
				String dob =columns[2];
				String empID =columns[3];
				String compName =columns[4];
				String position =columns[5];
				String password =columns[6];
				String reEnterPass =columns[7];
				Test t = new Test(fName,lName,dob,empID,compName,position,password,reEnterPass );
				tests2.add(t);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return tests2;
	}


	//Overriding method to write in  file
	@Override
	public boolean addTest(Test test) {
		ArrayList<Test> tests = this.getTests();
		tests.add(test);
		return this.saveTest(tests);
	}



}

