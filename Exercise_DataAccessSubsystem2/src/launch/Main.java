package launch;

import middleware.exceptions.DatabaseException;
import business.DbClassCatalog;

public class Main {

	public static void main(String[] args) {
		DbClassCatalog dbclass = new DbClassCatalog();
		 try{
		System.out.println(dbclass.getAllCatalogs());
		//	dbclass.getAllCatalogs().forEach(System.out::println);
			
			/*int newId = dbclass.insertNewCatalog("TestCat");
			System.out.println("New catalog id for TestCat is " + newId); 
			int numRowsDeleted = dbclass.deleteCatalog(newId);
			System.out.println("Num rows deleted = " + numRowsDeleted);*/
			
		}
		 catch(DatabaseException e) {
				e.printStackTrace();
			}
	}
	
	

}
