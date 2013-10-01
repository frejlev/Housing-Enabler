package ucn.datamatiker.afr_rmlp.housingenabler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	// Reuse columns
	public static final String COLUMN_ID = "_id";
	
	// User table
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";

	private static final String DATABASE_NAME = "housingenabler.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement	
	private static final String CREATE_TABLE_USERS = 
			"create table " + TABLE_USERS
	      + "(" + COLUMN_ID
	      + " integer primary key autoincrement, "
	      + COLUMN_USERNAME + " text not null, " 
	      + COLUMN_PASSWORD + " text not null, " 
	      + "UNIQUE(" + COLUMN_USERNAME + ") ON CONFLICT REPLACE);";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_TABLE_USERS);
			db.execSQL("INSERT INTO " + TABLE_USERS + "(" + COLUMN_USERNAME + ", " + COLUMN_PASSWORD + ") VALUES('afr', '123456')");
		} catch(Exception e) {
			System.out.println("Creating of db tables: " + e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		onCreate(db);
	}
	
}
