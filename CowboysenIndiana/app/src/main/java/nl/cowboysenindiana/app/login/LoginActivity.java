package nl.cowboysenindiana.app.login;

import nl.cowboysenindiana.app.JSON_model_parser.login_parser;
import nl.cowboysenindiana.app.login.LocalDb.LocalDbOpenHelper;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.cowboysenindiana.app.model.User;
import nl.cowboysenindiana.app.networkTest.HttpManager;
import nl.cowboysenindiana.app.networkTest.RequestPackage;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.network.URL;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;

public class LoginActivity extends Activity {

    public URL urls;
    List<MyTask> myTasks;
    ProgressBar progressBar;
    User gebruiker;
//    private LocalDbOpenHelper DbOpenHelper;
//    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);
        myTasks = new ArrayList<>();

//        DbOpenHelper = new LocalDbOpenHelper(this);
//        database     = DbOpenHelper.getWritableDatabase();
    }

    public void login (Boolean login) {
        if (login) {
            Intent i = new Intent(LoginActivity.this, ScheduleActivity.class);
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

        String username = username_view.getText().toString();
        String password = password_view.getText().toString();

        p.setParam("username", username);
        p.setParam("password", password);

        Log.e("username", username);
        Log.e("password", password);

        MyTask mytask = new MyTask();
        mytask.execute(p);
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
            String content = HttpManager.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result) {
            Log.e("Result: ", result);
            gebruiker = login_parser.parseFeed(result);
            login(gebruiker.isAthenticated());

            myTasks.remove(this); // om ervoor te zorgen dat als er geen taak meer is, de progressbar uit te zetten

            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) { // Ja eigenlijk geen idee ._.
        }
    }
}
