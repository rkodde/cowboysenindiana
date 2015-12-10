package nl.cowboysenindiana.app.networkTest;

import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

public class MainActivity extends BaseActivity {

    TextView output;
    ProgressBar progressBar;
    List<MyTask> myTasks;

    List<Person_model> person_model_list;

    @Override
    protected void goNext() {
        output = (TextView) findViewById(R.id.texttexttext);
        output.setMovementMethod(new ScrollingMovementMethod());

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);

        myTasks = new ArrayList<>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_do_task) {
            if (isOnline()) {
                requestData(urls.TEST);
            } else {
                showToast("Geen netwerk verbinding");
            }
        }
        if (id == R.id.action_empty) {
            clearDisplay();
        }
        return false;
    }

    private void requestData(String uri) {

        RequestPackage p = new RequestPackage();

        p.setMethod("POST"); // Deze mag je op POST laten staan
        p.setUri(uri);
        p.setParam("person_id", "2"); // Dit zijn je params, je kan er meerdere mee sturen.

        Log.e("getParam", p.getEncodedParams());

        MyTask mytask = new MyTask();
        mytask.execute(p);
    }

    private void clearDisplay() {
        output.setText("");
    }

    @Override
    protected int getContentView() {return R.layout.activity_main;}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.network_test_menu, menu);
        return true;
    }

    private void updateDisplay(){
        if (person_model_list != null) {
            for (Person_model person_model : person_model_list ) {
                output.append(person_model.getFirstName() + " " + person_model.getLastName());
//                output.append(person_model.getFirstName() + "\n");
            }
        } else {
            Log.e("leeg", "jup");
        }
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
            Log.e("getParam", params[0].getEncodedParams());
            String content = HttpManager.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result) {
            Log.e("result",result);
            person_model_list = Person_model_JSON_parser.parseFeed(result);

            updateDisplay();

            myTasks.remove(this);

            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
//            updateDisplay(values[0]);
        }
    }
}
