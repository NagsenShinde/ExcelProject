package antProject;

import java.io.FileInputStream;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import org.jboss.logging.Logger;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;

public class LogicPart1 implements Job  {
	
  public static final Logger log = Logger.getLogger(LogicPart1.class);
  final static File3 file2 = new File3();
  
  
//    public static void excelToDb() throws IOException
//	{
//		 
//		 try {
//			File3.getConnection();
//		} catch (SQLException e) {
//			 
//			e.printStackTrace();
//		}
//		 try {
//			System.out.println(File3.getConnection());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		prop.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//		prop.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/excel");
//		prop.put("javax.persistence.jdbc.user", "root");
//		prop.put("javax.persistence.jdbc.password", "Nagsen@123");
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/excel");
//		properties.put("javax.persistence.jdbc.user", "root");
//		properties.put("javax.persistence.jdbc.password", "Nagsen@123");
		 
//		System.out.println("------------------------------------------>");
//		ServiceLoader<PersistenceProvider> loader = ServiceLoader.load(PersistenceProvider.class);
//		
//		for(PersistenceProvider prov : loader) {
//			System.out.println(prov.getClass().getName());
//		}
//		PersistenceProvider provider = loader.iterator().next();
//		System.out.println("---------------------------------------------------->");
//		 
//		String persistenceUnitName ="ABC";
//		
//		EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceUnitName, properties);
		//provider.createEntityManagerFactory(persistenceUnitName, properties);
//		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("Nagsen");
//		System.out.println(entitymanagerfactory);
//		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
//		EntityTransaction entitytrasaction = entitymanager.getTransaction();
//		String e = Persistence.PERSISTENCE_PROVIDER;
//		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory(e);
		//EntityManager entitymanager = emf.createEntityManager();
		
		//EntityTransaction entitytrasaction = entitymanager.getTransaction();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 
		try {
		      String file = "C:/Users/nagsen.shinde/Documents/studentInfo.xlsx";
		      FileInputStream fileinputstring = new FileInputStream(file);
		      try (XSSFWorkbook workbook = new XSSFWorkbook(fileinputstring)) {
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				  Iterator<Row>iterator = sheet.iterator();
				  iterator.next();
				  while(iterator.hasNext())
				  {
				      Row row = iterator.next();
					  EntityClass entityclass = new EntityClass();
					  entityclass.setId((int) row.getCell(0).getNumericCellValue());
					  entityclass.setName(row.getCell(1).getStringCellValue());
					  entityclass.setPhoneno(((long) row.getCell(2).getNumericCellValue()));
					  PreparedStatement prepare = File3.getConnection().prepareStatement("Insert into excelbook(Id,Name,phoneNo) values(?,?,?)");
					  PreparedStatement prepare1 = File3.getConnection().prepareStatement("Update excelbook SET Name = ?,phoneNo =? WHERE Id = ?");
					  String select = "Select * from excelbook where Id="+entityclass.getId();					   
					  PreparedStatement prepare3 = File3.getConnection().prepareStatement(select);
					  ResultSet result = prepare3.executeQuery();
					  int i=0;
					  while(result.next()) {
						  i = result.getInt("Id");
						  System.out.println(result.getInt("Id"));
					  }
					 
					  if( i == 0 )
					  {
						  prepare.setInt(1, entityclass.getId());
						  prepare.setString(2, entityclass.getName());
						  prepare.setLong(3, entityclass.getPhoneno());
						  prepare.execute();
					  }
					  else 
					  {
						  prepare1.setString(1, entityclass.getName());
						  prepare1.setLong(2, entityclass.getPhoneno());
						  prepare1.setInt(3, entityclass.getId());
						  prepare1.execute();
						   
					    }
					  
			    	  }
				   
		        }
		        log.info("Data imported sucessfully");
		     
		    }
	      catch(Exception e1)
			{
		  System.out.println("Data not found "+e1);
	  } 
   }
}