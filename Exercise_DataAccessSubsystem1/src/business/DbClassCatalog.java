package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.exceptions.DatabaseException;
import middleware.externalinterfaces.DataAccessSubsystem;
import middleware.externalinterfaces.DbClass;
import middleware.externalinterfaces.DbConfigKey;

import java.util.*;
import java.util.logging.Logger;

public class DbClassCatalog implements DbClass {
	enum Type {READ_ALL, INSERT, DELETE};
	List<Catalog> list = null;
	DataAccessSubsystem dataaccess = new DataAccessSubsystemFacade();
	private static final Logger LOG 
	  = Logger.getLogger(DataAccessSubsystemFacade.class.getPackage().getName());
	private String query="";
	private Object[] queryParams={};
	private int[] paramTypes = {};
	public List<Catalog> getAllCatalogs() {
		//implement
		try {
			this.query ="select * from catalogtype";
			dataaccess.atomicRead(this);
		} catch (DatabaseException e) {
			LOG.warning("DB Exception: "+e.getMessage()+getDbUrl());
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int insertNewCatalog(String catalogName) {
		//implement
		this.query ="insert into catalogtype  values(0,'"+catalogName+"')";
		Object[] queryParams={};
		this.queryParams = queryParams;
		int catalogId=-1;
		try {
			catalogId = dataaccess.insert();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return catalogId;  //new catalogid
	}
	
	public int deleteCatalog(int id) {
		//implement
		this.query ="delete from catalogtype where catalogid=?";
		Object[] queryParams={id};
		this.queryParams = queryParams;
		int[] paramTypes = {Types.INTEGER};
		this.paramTypes = paramTypes;
		int rowsUpdated=-1;
		try {
			rowsUpdated = dataaccess.delete();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return rowsUpdated;  //num rows updated
	}
	
	///interface implementations
	@Override
	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		
		try {
			list = new ArrayList<Catalog>();
			while(resultSet.next()){
				int catalogId = resultSet.getInt("catalogid");
				list.add(new Catalog(catalogId,resultSet.getString("catalogname")));
			}
		} catch (SQLException e) {
			LOG.warning("SQL Exception in populateEntity: "+e.getMessage());
			//e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public String getDbUrl() {
		DbConfigProperties props = new DbConfigProperties();	
    	return props.getProperty(DbConfigKey.PRODUCT_DB_URL.getVal());
        
		
		//DbConfigKey is not returning correct values; that is why i am hard coding this row
		//return "jdbc:mysql://localhost:3306/ProductsDb";
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public Object[] getQueryParams() {
		return queryParams;
	}

	@Override
	public int[] getParamTypes() {
		return paramTypes;
	}

}
