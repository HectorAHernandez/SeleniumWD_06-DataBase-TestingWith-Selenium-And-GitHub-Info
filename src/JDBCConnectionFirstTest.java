/* - After creating the Selenium project,  
- Define the JDBC connection and for this, first, we have to download and configure the .JAR file 
          mysql-connector-java-5.1.47.jar download into c:\Selenium-3.11.00 
- Now configure this JAR file for this project "06 Database TestingWith Selenium" by right clicking the project
  and selecting: properties --> Java Build Path --> Add External JARs the selecting the 
   mysql-connector-java-5.1.47.jar and click on "Apply and close" button.
   
   Later we will need to configure other jar for this project, like the Selenium one, but for now lets continue:
- Now we can create a class in the src folder for our first test.
          */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnectionFirstTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
    // Below code will only work for MySQL database. Note: for others database we will have to make only one change
		
	//	DriverManager.getConnection(arg0, "dataBase-userId", "dataBase-password") where 
    //		arg0 = DB-URL = "jdbc:mysql://"+host+":"portNumber"+"/databaseName";
		String host = "localhost";
		String port = "3306";  //Default in localhost where we created the database.
	//below create the Connection object "JDBCConnectionHector" 	
		Connection JDBCConnectionHector=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/qadbtesting", "root", "root");
		// here Connection was related to "import Connection (java.sql)" to import java.sql.Connection
		// above defines/establishes the connection for the database "qadbtesting"

		/* Below is to create the statement object to be used to send the queries to CRUD the data in the database, so we can 
		   create the queries using the JDBCConnectionHector. below create the Statement object "queryStatmt" 
		   using the JDBCConnectionHector */
		Statement queryStatmt = JDBCConnectionHector.createStatement();
		// here Statement was related to "import Statement (java.sql)" to import java.sql.Statement
		
		// Now the statement object can be executed with the specific query we want to run
	    //	 queryStatmt.executeQuery("select * from credential where scenario = 'zerobalancecard'");
	    // now to create a ResultSet (queryResSet) object to hold the whole result of the query execution:
		ResultSet queryResSet = queryStatmt.executeQuery("select * from credential where scenario = 'zerobalancecard'");
		// here ResultSet was related to import 'ResultSet' (java.sql) to import java.sql.ResultSet
	    // the resultSet receives the data in index so we need to use the rs.next() method to be able to
		// access the data. if not next() used the pointer will be to the base result set index which has not data.
		// 
		//now to access the value in each specific column we use the .getString("columnName") or
		//   .getInteger("columnName") methods of the queryResSet.
		//   queryResSet.getString("userName");
		queryResSet.next();   //moves from the base index to the first index in the resultSet.
		System.out.println("user name: "+queryResSet.getString("userName"));
		// above query returns only one row, so if we try to use another next() it will be empty, because the query
		// executed only returned one row.
	    // if we want to print them all, in a multiple rows query, we can use below loop:
		ResultSet queryResSetForPassword = queryStatmt.executeQuery("select * from credential where password = 'testPassword'");
		while (queryResSetForPassword.next()) {
			System.out.println("user name scenario: "+queryResSetForPassword.getString("scenario"));
			System.out.println("user name: "+queryResSetForPassword.getString("userName"));
		}

	    // now trying moving the index of the resultSet one at a time
		ResultSet queryResSetForScenario = queryStatmt.executeQuery("select * from credential where password = 'testPassword'");
		queryResSetForScenario.next();
		System.out.println("scenario-1: "+queryResSetForScenario.getString("scenario"));
		queryResSetForScenario.next();
		System.out.println("scenario-2: "+queryResSetForScenario.getString("scenario"));
		queryResSetForScenario.next();
		System.out.println("scenario-3: "+queryResSetForScenario.getString("scenario"));
		queryResSetForScenario.next();
		System.out.println("scenario-4: "+queryResSetForScenario.getString("scenario"));
		queryResSetForScenario.next();
		System.out.println("scenario-5: "+queryResSetForScenario.getString("scenario"));
		queryResSetForScenario.next();
		System.out.println("scenario-6: "+queryResSetForScenario.getString("scenario"));
	/* above line 66 will generate below error because there are only 5 rows in the resulSet:
	 * Exception in thread "main" java.sql.SQLException: After end of result set
	 *  */		
	}
	
	

}
