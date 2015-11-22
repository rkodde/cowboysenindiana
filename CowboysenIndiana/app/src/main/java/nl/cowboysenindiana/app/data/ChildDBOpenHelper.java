package nl.cowboysenindiana.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * ChildDBOpenHelper class
 * creates 'Child' table on SQLite DB as local storage for testing
 * @author Sasha Antipin
 * @since 22-11-2015
 */
public class ChildDBOpenHelper extends SQLiteOpenHelper{

    private static final String LOGTAG = "COWBOYSENINDIANA";

    private static final String DATABASE_NAME = "cowboysenindiana.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CHILD = "children";
    public static final String COLUMN_ID = "child_id";
    public static final String COLUMN_FIRST = "first_name";
    public static final String COLUMN_LAST = "last_name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CONTACTINFORMATION = "contact_information";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_PARTICULARITY = "particularity";
    public static final String COLUMN_PROVIDERS = "providers";
    public static final String COLUMN_PRIMARYPROVIDER = "primary_provider";
    public static final String COLUMN_CREATED = "date_created";
    public static final String COLUMN_PLACEMENT = "date_placement";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_IS_INSIDE = "is_inside";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CHILD + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST + " TEXT, " +
                    COLUMN_LAST + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_CONTACTINFORMATION + " TEXT, " +
                    COLUMN_BIRTHDAY + " DATE, " +
                    COLUMN_PARTICULARITY + " TEXT, " +
                    COLUMN_PROVIDERS + " TEXT, " +
                    COLUMN_PRIMARYPROVIDER + " TEXT, " +
                    COLUMN_CREATED + " DATE, " +
                    COLUMN_PLACEMENT + " DATE, " +
                    COLUMN_IMAGE + " TEXT, " +
                    COLUMN_IS_INSIDE + " TEXT " +
                    ")";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public ChildDBOpenHelper(Context context) {
        //super(context, name, factory, version);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CHILD);
        onCreate(db);
    }
}
