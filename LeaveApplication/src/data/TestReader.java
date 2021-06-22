package data;

//importing necessary packages
import java.util.ArrayList;

//creating an interface to make files readable
public interface TestReader {

	//creating abstract methods to implement reading of files
	business.Test getTest(String userName, String password);
	ArrayList<business.Test> getTests();

	business.Test getTest(String userName, String password,String position);

}

