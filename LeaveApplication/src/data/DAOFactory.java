package data;

public class DAOFactory {

	//static method to be available to different classes
	public static TestDAO getTestDAO() {
		TestDAO tDAO = new TestDAOText();
		return tDAO;
	}


}
