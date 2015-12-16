package nl.cowboysenindiana.app.login.LocalDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rene on 30-11-2015.
 */

public class backupLocalDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "User.db";

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME      = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Creating the table

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COLUMN_USERNAME +
                    COLUMN_PASSWORD +
                    ");";

    public backupLocalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.d("database","database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
