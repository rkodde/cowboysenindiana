package nl.cowboysenindiana.app.login.LocalDb;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class UserProvider extends ContentProvider {

    private static final String AUTHORITY   = "nl.cowboysenindiana.app.login.localdb.userprovider";
    private static final String BASE_PATH   = "users";
    public static final  Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int USERS    = 1;
    private static final int USERS_ID = 2;

    private static final UriMatcher urimatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        urimatcher.addURI(AUTHORITY, BASE_PATH, USERS);
        urimatcher.addURI(AUTHORITY, BASE_PATH + "/#", USERS_ID);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        LocalDbOpenHelper DbOpenHelper = new LocalDbOpenHelper(getContext());
        db = DbOpenHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(
                LocalDbOpenHelper.TABLE_LOGIN,
                LocalDbOpenHelper.ALL_COLUMNS,
                selection,
                null,
                null,
                null,
                LocalDbOpenHelper.USER_CREATED
        );
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(
                LocalDbOpenHelper.TABLE_LOGIN,
                null,
                values
        );
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(LocalDbOpenHelper.TABLE_LOGIN, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(LocalDbOpenHelper.TABLE_LOGIN, values, selection, selectionArgs);
    }
}
