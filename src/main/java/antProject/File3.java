package antProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class File3 {
	
	public 	static final Logger log = Logger.getLogger(File3.class); 
	public static Connection getConnection() throws SQLException, IOException
	{
		 final String file1 = "config.properties";
		 final  Properties prop = new Properties();
		 FileInputStream fileInputStream = new FileInputStream(file1);
		 prop.load(fileInputStream);
		 String url = prop.getProperty("jdbc.url");
		 String username = prop.getProperty("jdbc.username");
		 String password = prop.getProperty("jdbc.password");
		 String driver = prop.getProperty("jdbc.driver");
		 try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
		    log.fatal("Exception in Dtabase conncetion please check your DB connections" +e);
			e.printStackTrace();
		}
		 return(DriverManager.getConnection(url, username, password));
		 
  }


}
