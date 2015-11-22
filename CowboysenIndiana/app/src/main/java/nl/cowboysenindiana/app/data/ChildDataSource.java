package nl.cowboysenindiana.app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import nl.cowboysenindiana.app.model.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * ChildDataSource class
 * to communicate with DataSource
 * testing on SQLite DB local storage
 * @author Sasha Antipin
 * @since 22-11-2015
 */
public class ChildDataSource {

    public static final String LOGTAG="COWBOYSENINDIANA";

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {    // contain one string for some columns in the table
            ChildDBOpenHelper.COLUMN_ID,
            ChildDBOpenHelper.COLUMN_FIRST,
            ChildDBOpenHelper.COLUMN_LAST,
            ChildDBOpenHelper.COLUMN_TYPE,
            ChildDBOpenHelper.COLUMN_GENDER,
            ChildDBOpenHelper.COLUMN_CONTACTINFORMATION,
            ChildDBOpenHelper.COLUMN_BIRTHDAY,
            ChildDBOpenHelper.COLUMN_PARTICULARITY,
            ChildDBOpenHelper.COLUMN_PROVIDERS,
            ChildDBOpenHelper.COLUMN_PRIMARYPROVIDER,
            ChildDBOpenHelper.COLUMN_CREATED,
            ChildDBOpenHelper.COLUMN_PLACEMENT,
            ChildDBOpenHelper.COLUMN_IS_INSIDE,
            ChildDBOpenHelper.COLUMN_IMAGE
    };
    public ChildDataSource(Context context) {
        dbhelper =  new ChildDBOpenHelper(context);
    }

    public void open() {
        Log.i(LOGTAG, "Database opened");
        database = dbhelper.getWritableDatabase();		// get reference to the connection to the database

    }

    public void close() {
        Log.i(LOGTAG, "Database closed");
        dbhelper.close();
    }

    /**
     * methode to create Child object
     * in order to pass it into the database
     * @param child object
     * @return Child object
     */
    public Child create(Child child) {
        ContentValues values = new ContentValues();
        // put items into the map where the key is the name of the column
        // and the value is the value we want to insert
        values.put(ChildDBOpenHelper.COLUMN_FIRST, child.getFirstName());
        values.put(ChildDBOpenHelper.COLUMN_LAST, child.getLastName());
        values.put(ChildDBOpenHelper.COLUMN_TYPE, child.getPersonType());
        values.put(ChildDBOpenHelper.COLUMN_GENDER, child.getGender());
        values.put(ChildDBOpenHelper.COLUMN_CONTACTINFORMATION, child.getContactInformation());
        values.put(ChildDBOpenHelper.COLUMN_BIRTHDAY, String.valueOf(child.getBirthDay()));
        values.put(ChildDBOpenHelper.COLUMN_PARTICULARITY, child.getParticularities());
        values.put(ChildDBOpenHelper.COLUMN_PROVIDERS, child.getProviders());
        values.put(ChildDBOpenHelper.COLUMN_PRIMARYPROVIDER, child.getPrimaryChildProvider().toString());
        values.put(ChildDBOpenHelper.COLUMN_CREATED, String.valueOf(child.getDateCreated()));
        values.put(ChildDBOpenHelper.COLUMN_PLACEMENT, String.valueOf(child.getDateOfPlacement()));
        values.put(ChildDBOpenHelper.COLUMN_IS_INSIDE, child.isInside());
        values.put(ChildDBOpenHelper.COLUMN_IMAGE, child.getImage());


        /** insert a row into the database table ------------
         * receive back automatically assigned primary key value */
        long insertId = database.insert(ChildDBOpenHelper.TABLE_CHILD,
                null,      // name of the column
                values );          //content values object
        child.setId(insertId);
        return child;
    }

    /** method to retrive all rows and all columns ot the same table
     *  @return a list of Child objects
     */
    public List<Child> findAll() {

        /**  querying the single table
         return a reference to the data that's returned from the query */
        Cursor cursor = database.query(
                ChildDBOpenHelper.TABLE_CHILD, // table
                allColumns,                     // columns
                null,                           // WHERE selection
                null,                           // selectionArgs
                null,                           // groupBy
                null,                           // having
                null                            // orderBy
        );
        Log.i(LOGTAG, "returnded " + cursor.getCount() + " rows");

        List<Child> children = cursorToList(cursor);
        return children;
    }

    /** method to retrive all rows and all columns
     * uses filter arguments
     * @param selection, orderBy
     *  @return a list of tour objects
     */
    public List<Child> findFiltered(String selection, String orderBy) {

        /**  querying the single table
         return a reference to the data that's returned from the query */
        Cursor cursor = database.query(
                ChildDBOpenHelper.TABLE_CHILD, // table
                allColumns,                     // columns
                selection,                           // WHERE selection
                null,                           // selectionArgs
                null,                           // groupBy
                null,                           // having
                orderBy                            // orderBy
        );
        Log.i(LOGTAG, "returnded " + cursor.getCount() + " rows");

        List<Child> children = cursorToList(cursor);
        return children;
    }

    private List<Child> cursorToList(Cursor cursor) {
        List<Child> children = new ArrayList<Child>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Child child = new Child();
                child.setId(
                        cursor.getLong(
                                cursor.getColumnIndex(
                                        ChildDBOpenHelper.COLUMN_ID)));
                child.setFirstName(
                        cursor.getString(
                                cursor.getColumnIndex(
                                        ChildDBOpenHelper.COLUMN_FIRST)));
                child.setLastName(
                        cursor.getString(
                                cursor.getColumnIndex(
                                        ChildDBOpenHelper.COLUMN_LAST)));
                child.setImage(
                        cursor.getString(
                                cursor.getColumnIndex(
                                        ChildDBOpenHelper.COLUMN_IMAGE)));
                child.setInside(
                        cursor.getString(
                                cursor.getColumnIndex(
                                        ChildDBOpenHelper.COLUMN_IS_INSIDE)));
                children.add(child);
            }
        }
        return children;
    }
}
