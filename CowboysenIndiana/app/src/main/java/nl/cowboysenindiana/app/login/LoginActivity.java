package nl.cowboysenindiana.app.login;

import nl.cowboysenindiana.app.JSON_model_parser.login_parser;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.cowboysenindiana.app.login.LocalDb.LocalDbOpenHelper;
import nl.cowboysenindiana.app.login.LocalDb.UserProvider;
import nl.cowboysenindiana.app.model.User;
import nl.cowboysenindiana.app.networkTest.HttpManagerTest;
import nl.cowboysenindiana.app.networkTest.RequestPackage;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;

public class LoginActivity extends Activity {

    private List<MyTask> myTasks;
    private ProgressBar progressBar;
    private User gebruiker;
    private SQLiteDatabase db;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(isUserAvailable()){
            login(true);
        } else {
            progressBar = (ProgressBar) findViewById(R.id.progressBar1);
            progressBar.setVisibility(View.INVISIBLE);
            myTasks = new ArrayList<>();
        }
    }

    private void saveUser(User user) {
        ContentValues values = new ContentValues();
        values.put(LocalDbOpenHelper.USER_NAME, user.getUsername());
        values.put(LocalDbOpenHelper.USER_PASSW, password);
        Uri userUri = getContentResolver().insert(UserProvider.CONTENT_URI, values);
        Log.d("LoginActivity inserted", userUri.getLastPathSegment());
    }

    public void login (Boolean login) {
        if (login) {
            User gebruiker = getUser();
            Intent i = new Intent();
            i.putExtra("user", gebruiker);
            i.setClass(LoginActivity.this, ScheduleActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show();
        }
    }

    public void attemptLogin (View view) {

        RequestPackage p = new RequestPackage();

        p.setMethod("POST");
        p.setUri("http://tqm.cowboysenindiana.nl/login.php");
        EditText username_view = (EditText) findViewById(R.id.input_username);
        EditText password_view = (EditText) findViewById(R.id.input_password);

        username = username_view.getText().toString();
        password = password_view.getText().toString();

        p.setParam("username", username);
        p.setParam("password", password);

        Log.d("LoginActivity username", username);
        Log.d("LoginActivitypassword", password);

        MyTask mytask = new MyTask();
        mytask.execute(p);
    }

    public boolean isUserAvailable() {
        Cursor cursor;
        cursor = getContentResolver().query(UserProvider.CONTENT_URI, LocalDbOpenHelper.ALL_COLUMNS, null, null, null, null);
        Log.d("LoginActivity count", " " + cursor.getCount());

        if (cursor.getCount() > 0) {
            Log.e("LoginActivity", "Wel user");
            cursor.close();
            return true;
        }
        Log.d("LoginActivity", "Geen user");
        cursor.close();
        return false;
    }

    public User getUser() {
        LocalDbOpenHelper DbOpenHelper = new LocalDbOpenHelper(this);
        db = DbOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(LocalDbOpenHelper.TABLE_LOGIN, LocalDbOpenHelper.ALL_COLUMNS, null, null, null, null, null);
        cursor.moveToLast();
        Log.d("getUser id", " " + cursor.getString(0));
        Log.d("getUser username", " " + cursor.getString(1));
        Log.d("getUser password", " " + cursor.getString(2));
        User gebruiker = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        db.close();
        cursor.close();
        return gebruiker;
    }

    private class MyTask extends AsyncTask<RequestPackage, String, String>
    {
        protected void onPreExecute() {
            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            myTasks.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManagerTest.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result) {
            gebruiker = login_parser.parseFeed(result);

            boolean authenticated = gebruiker.isAuthenticated();
            if (authenticated) {
                saveUser(gebruiker);
            }

            login(authenticated);

            myTasks.remove(this);

            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
}
