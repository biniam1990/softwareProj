package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.exceptions.DatabaseException;
import middleware.externalinterfaces.DataAccessSubsystem;
import middleware.externalinterfaces.DbClass;
import java.util.*;

public class DbClassCatalog implements DbClass {
	enum Type {READ_ALL, INSERT, DELETE};
	Type queryType;
	String readAllQuery = "SELECT * FROM catalogtype";
	String insertQuery = "INSERT INTO catalogtype (catalogname)"+"VALUES(?)";
	String deletQuery = "DELETE from catalogtype WHERE catalogid =?";
	
	private static final String DB_URL_PROD = "jdbc:mysql:///ProductsDb";
	Object[] readParams =null;
	Object[] insertParams = null;
	int[] readParamTypes = null;
	int[] insertParamTypes = null;
	Object[] deleteParams = null;
	int[] deleteParamTypes = null;
	
	List<Catalog> catalogs= new ArrayList<Catalog>();
	
	
	
	public DbClassCatalog(){}
	
	public List<Catalog> getAllCatalogs() throws DatabaseException {
		//implement
 System.out.println("method enterde");
		queryType= Type.READ_ALL;	
		readParams= new Object[]{};
        readParamTypes = new int[]{};
		System.out.println(queryType);
		DataAccessSubsystemFacade ds = new DataAccessSubsystemFacade();
		ds.establishConnection(this);
		ds.read();
		ds.releaseConnection();
		return catalogs;
	}
	
	public int insertNewCatalog(String catalogName) throws DatabaseException {
		//implement
		queryType= Type.INSERT;
		insertParams= new Object[]{catalogName};
		insertParamTypes = new int[]{Types.VARCHAR};
		DataAccessSubsystemFacade ds = new DataAccessSubsystemFacade();
		int idval=ds.insertWithinTransaction(this);
		
		return idval;  //new catalogid
	}

	public int deleteCatalog(int id) throws DatabaseException {
		//implement
		System.out.println("testing delete");
		
		queryType=Type.DELETE;
		deleteParams= new Object[]{id };
		deleteParamTypes= new int[]{Types.INTEGER};
		DataAccessSubsystemFacade ds = new DataAccessSubsystemFacade();
		int numrows= ds.deleteWithinTransaction(this);
		return numrows; //num rows updated
	}
	
	///interface implementations
	@Override
	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		catalogs = new ArrayList<Catalog>();
		try{
			int id = 0;
			String name= null;
			
			while(resultSet.next()){
				id= resultSet.getInt("catalogid");
				name= resultSet.getString("catalogname").trim();
				catalogs.add(new Catalog(id,name));
			}
			
		}
		catch(SQLException e){
			throw new DatabaseException(e);
		}
	}

	@Override
	public String getDbUrl() {
	
		return DB_URL_PROD;
	}

	@Override
	public String getQuery() {
		
		switch(queryType){
		case READ_ALL:
		    return readAllQuery;
		case INSERT:
			return insertQuery;
		case DELETE:
			return deletQuery;
		default:
		return null;
		}
	}

	@Override
	public Object[] getQueryParams() {
	    switch(queryType){
	    case READ_ALL:
	    	return readParams;
	    case INSERT:
	    	return insertParams;
	    case DELETE:
	    	return deleteParams;
	    default:
	    	return null;
	    }
	}

	@Override
	public int[] getParamTypes() {
	    switch(queryType){
	    case READ_ALL:
	    	return readParamTypes;
	    case INSERT:
	    	return insertParamTypes;
	    case DELETE:
	    	return deleteParamTypes;
	    default:
	    	return null;
	    }
	}

}
