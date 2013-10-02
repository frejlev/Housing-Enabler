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
	
	// Client table
	public static final String TABLE_CLIENTS = "clients";
	public static final String COLUMN_P_NAME = "p_name";
	public static final String COLUMN_P_A = "p_A";
	public static final String COLUMN_P_B1 = "p_B1";
	public static final String COLUMN_P_B2 = "p_B2";
	public static final String COLUMN_P_C = "p_C";
	public static final String COLUMN_P_D = "p_D";
	public static final String COLUMN_P_E = "p_E";
	public static final String COLUMN_P_F = "p_F";
	public static final String COLUMN_P_G = "p_G";
	public static final String COLUMN_P_H = "p_H";
	public static final String COLUMN_P_I = "p_I";
	public static final String COLUMN_P_J = "p_J";
	public static final String COLUMN_P_K = "p_K";
	public static final String COLUMN_P_L = "p_L";
	public static final String COLUMN_P_M = "p_M";
	

	// Database info
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
