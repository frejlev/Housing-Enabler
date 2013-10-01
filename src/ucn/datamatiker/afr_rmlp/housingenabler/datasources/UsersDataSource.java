package ucn.datamatiker.afr_rmlp.housingenabler.datasources;

import java.util.ArrayList;
import java.util.List;

import ucn.datamatiker.afr_rmlp.housingenabler.models.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ucn.datamatiker.afr_rmlp.housingenabler.MySQLiteHelper;

public class UsersDataSource {

	  // Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { 
		MySQLiteHelper.COLUMN_ID,
	    MySQLiteHelper.COLUMN_USERNAME, 
	    MySQLiteHelper.COLUMN_PASSWORD 
    };

	public UsersDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public User createUser(String username) {
		ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_USERNAME, username);
	    long insertId = database.insert(MySQLiteHelper.TABLE_USERS, null,
	        values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
	        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    User newUser = cursorToUser(cursor);
	    cursor.close();
	    return newUser;
	}

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

	  private User cursorToUser(Cursor cursor) {
	    User user = new User();
	    user.setId(cursor.getLong(0));
	    user.setUsername(cursor.getString(1));
	    return user;
	  }
	} 