/* This program will Get userName and password from the credential table in the database qadbtesting and then
 * will login to the https://login.salesforce.com website passing the values from the dabase.
 * 
 * * - After creating the Selenium project,  
- we had defined the JDBC connection and for this, first, we have to download and configure the .JAR file 
          mysql-connector-java-5.1.47.jar download into c:\Selenium-3.11.00 
- Now configure this JAR file for this project "06 Database TestingWith Selenium" by right clicking the project
  and selecting: properties --> Java Build Path --> Add External JARs the selecting the 
   mysql-connector-java-5.1.47.jar and click on "Apply and close" button.
   
 And Now we have to configure Selenium jar file (c:\Selenium-3.11.0\selenium-server-standalone-3.12.0.jar) for 
   this project, "06 Database TestingWith Selenium" by right clicking the project
  and selecting: properties --> Java Build Path --> Add External JARs the selecting the 
   selenium-server-standalone-3.12.0.jar and click on "Apply and close" button.
          */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class O2UsingJDBCConnectionAndSeleniumCode {

	public static void main(String[] args) throws SQLException {
		// *** Getting ready for accessing the database ******************
		//************
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
		ResultSet credentialQueryRestSet = queryStatmt.executeQuery("select * from credential where scenario = 'zerobalancecard'");
		// here ResultSet was related to import 'ResultSet' (java.sql) to import java.sql.ResultSet
	    // the resultSet receives the data in index so we need to use the rs.next() method to be able to
		// access the data. if not next() used the pointer will be to the base result set index which has not data.
		// 
		//now to access the value in each specific column we use the .getString("columnName") or
		//   .getInteger("columnName") methods of the queryResSet.
		//   queryResSet.getString("userName");
		credentialQueryRestSet.next();   //moves from the base index to the first index in the resultSet.
		System.out.println("user name: "+credentialQueryRestSet.getString("userName"));
		// above query returns only one row, so if we try to use another next() it will be empty, because the query
		// executed only returned one row.
	    // 
//--------------------------------------
		// *** Getting ready for the website ******************
		//************
		WebDriver driver;
	//	System.setProperty("webdriver.gecko.driver","C:\\selenium-3.11.0\\geckodriver.exe");
	//  driver = new FirefoxDriver();
	    System.setProperty("webdriver.chrome.driver", "C:\\selenium-3.11.0\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  // Define Implicit wait time to ask Selenium to 
        //    Wait up until 5 seconds for each sentence to complete before raising exception error.
     	String baseUrl = "https://login.salesforce.com/";
     	driver.get(baseUrl);
     	//below the sendkeys is using the column we got from the database (userName and password).
     	driver.findElement(By.cssSelector("#username")).sendKeys(credentialQueryRestSet.getString("userName"));
     	driver.findElement(By.cssSelector("#password")).sendKeys(credentialQueryRestSet.getString("password"));
		 
	}
	
	

}
