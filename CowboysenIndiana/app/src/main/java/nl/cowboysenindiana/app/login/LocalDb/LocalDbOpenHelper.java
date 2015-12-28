package nl.cowboysenindiana.app.login.LocalDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rene on 30-11-2015.
 */
public class LocalDbOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login_credentials.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_LOGIN = "user_table";
    public static final String USER_ID     = "id";
    public static final String USER_NAME   = "user_name";
    public static final String USER_PASSW  = "user_passw";

    public static final String[] ALL_COLUMNS =
            {USER_ID, USER_NAME, USER_PASSW};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE "    + TABLE_LOGIN + " (" +
                    USER_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_NAME  + " TEXT, " +
                    USER_PASSW + " TEXT " +
                    ")";


    public LocalDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.d("database", "database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
