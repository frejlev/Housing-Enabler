package ucn.datamatiker.afr_rmlp.housingenabler.datasources;

import java.util.ArrayList;
import java.util.List;

import ucn.datamatiker.afr_rmlp.housingenabler.helpers.CalculationHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ucn.datamatiker.afr_rmlp.housingenabler.MySQLiteHelper;

public class ResultDataSource {

	  // Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	private CalculationHelper calcHelp;
	
	private ArrayList<String> allColumns;

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
	
	public ArrayList<Integer> updateResult(int id, String column, int theValue) {

	    ArrayList<Integer> newResult = new ArrayList<Integer>();
	    
	    int tableValue = 0;
	    
	    /**try {
	    	
	    	Cursor cursor = database.query(MySQLiteHelper.TABLE_RESULTS, new String[] { column }, "_id =" + id, null, null, null, null);
	    	if (cursor != null) {
	    		cursor.moveToFirst();
	    	}
	    	
	    	tableValue = cursor.getInt(0);
	    	
	    } catch (Exception e) {
	    	Log.d("ResultDataSource", e.getMessage());
	    }*/
	    
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

	/**
	public void deleteUser(User user) {
	    long id = user.getId();
	    System.out.println("User deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_USERS, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	}
	  
	public Boolean checkLogin(User user) {
		
		Log.d("UserDataSource", "Got into the check method...");
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, 
				  		allColumns, 
				  		MySQLiteHelper.COLUMN_USERNAME + " = ? AND " + MySQLiteHelper.COLUMN_PASSWORD + " = ?", 
				  		new String[] { user.getUsername(), user.getPassword() }, 
		null, null, null);
		
		if (cursor != null && cursor.getCount() > 0) return true;
		
		return false;
	}

	
	  public List<User> getAllUsers() {
	    List<User> users = new ArrayList<User>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      User user = cursorToUser(cursor);
	      users.add(user);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return users;
	  }
	  */

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
	} 