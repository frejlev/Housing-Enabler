package ucn.datamatiker.afr_rmlp.housingenabler.datasources;

import java.util.ArrayList;
import java.util.List;

import ucn.datamatiker.afr_rmlp.housingenabler.helpers.CalculationHelper;
import ucn.datamatiker.afr_rmlp.housingenabler.helpers.MySQLiteHelper;
import ucn.datamatiker.afr_rmlp.housingenabler.models.Result;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ResultDataSource {

	  // Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private CalculationHelper calcHelp;
	
	private ArrayList<String> allColumns;
	private String[] caseResultColumns = new String[] {
			MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_CASE_NAME,
			MySQLiteHelper.COLUMN_CASE_DATE,
			MySQLiteHelper.COLUMN_CASE_ADDRESS,
			MySQLiteHelper.COLUMN_CASE_CITY,
			MySQLiteHelper.COLUMN_CASE_ZIPCODE
	};

	public ResultDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
		calcHelp = new CalculationHelper(null);
		
		allColumns = new ArrayList<String>();
		
		addAllColumns();
	}
	
	private void addAllColumns() {
		
		for (String s : calcHelp.getQuestionKeys()) {
			allColumns.add(s);
		}
		
		allColumns.add(MySQLiteHelper.COLUMN_ID);
		
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public ArrayList<Integer> createResult() {
		ContentValues values = new ContentValues();
	    values.put(allColumns.get(2), 0);
	    
	    ArrayList<Integer> newResult = new ArrayList<Integer>();
	    
	    try {
	    	long insertId = database.insert(MySQLiteHelper.TABLE_RESULTS, null,
	        values);
	    	
	    	String[] coluArray = new String[allColumns.size()];
		    
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_RESULTS,
		        allColumns.toArray(coluArray), MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    
		    cursor.moveToFirst();
		    
		    newResult = cursorToResult(cursor);
		    
		    cursor.close();
	    	
	    } catch(Exception e) {
	    	Log.d("ResultDataSource", e.getMessage());
	    }
	    
	    return newResult;
	}
	
	public long createCaseInfo(long idResult, String caseName, String date, String address, String city, String zipcode) {
		
		ContentValues values = new ContentValues();
	    values.put(caseResultColumns[0], idResult);
	    values.put(caseResultColumns[1], caseName);
	    values.put(caseResultColumns[2], date);
	    values.put(caseResultColumns[3], address);
	    values.put(caseResultColumns[4], city);
	    values.put(caseResultColumns[5], zipcode);
	    
	    long id = database.insert(MySQLiteHelper.TABLE_CASEINFO, null,
		        values);
		
		return id;
	}
	
	public ArrayList<Integer> updateResult(int id, String column, int theValue) {

	    ArrayList<Integer> newResult = new ArrayList<Integer>();
	    
	    try {
	    	
	    	ContentValues values = new ContentValues();
	    	
    		values.put(column, theValue);

	        database.update(MySQLiteHelper.TABLE_RESULTS, values, "_id = " + id, null);
	    	
	    	String[] coluArray = new String[allColumns.size()];
		    
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_RESULTS,
		        allColumns.toArray(coluArray), MySQLiteHelper.COLUMN_ID + " = " + id, null,
		        null, null, null);
		    
		    cursor.moveToFirst();
		    
		    newResult = cursorToResult(cursor);
		    
		    cursor.close();
	    	
	    } catch(Exception e) {
	    	Log.d("ResultDataSource", e.getMessage());
	    }
	    
	    Log.d("Data just updated...", newResult.toString());
	    
	    return newResult;
	}
	
	public ArrayList<Integer> getResult(int id) {
		
		ArrayList<Integer> newResult = new ArrayList<Integer>();
		
		String[] coluArray = new String[allColumns.size()];
	    
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_RESULTS,
	        allColumns.toArray(coluArray), MySQLiteHelper.COLUMN_ID + " = " + id, null,
	        null, null, null);
	    
	    cursor.moveToFirst();
	    
	    newResult = cursorToResult(cursor);
	    
	    cursor.close();
		
		return newResult;
	}
	
	public List<Result> searchCaseInfo(String casename) {
		
		List<Result> newResult = new ArrayList<Result>();	
	    
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_CASEINFO,
	        caseResultColumns, MySQLiteHelper.COLUMN_CASE_NAME + " LIKE " + "'%" + casename + "%'", null,
	        null, null, null);
	    
	    cursor.moveToFirst();
	    
	    while (!cursor.isAfterLast()) {
		      Result result = cursorToResultObject(cursor);
		      newResult.add(result);
		      cursor.moveToNext();
	    }
	    
	    cursor.close();
		
		return newResult;
	}


	  private ArrayList<Integer> cursorToResult(Cursor cursor) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    
	    int sizeOfArray = calcHelp.getQuestionKeys().size();
	    
	    int counter = 0;
	    while (sizeOfArray >= counter) {
	    	result.add(cursor.getInt(counter));
	    	counter++;
	    }

	    return result;
	  }
	  
	  	private Result cursorToResultObject(Cursor cursor) {

	  		Result result = new Result(
	  				cursor.getInt(0),
	  				cursor.getString(1),
	  				cursor.getString(2),
	  				cursor.getString(3),
	  				cursor.getString(4),
	  				cursor.getString(5)
	  		);
	  		
		    return result;
		    
	  	}
	  
	} 