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

    TextView output; // type output
    ProgressBar progressBar; // om gebruikers te laten zien dat de app op de achtergrond iets doet
    List<MyTask> myTasks; //Lijst van je threads.

    List<Person_model> person_model_list; // Je model. Is een list als je een lijst wilt weergeven

    @Override
    protected int getContentView() {return R.layout.activity_main;}

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
        /* Deze method is specifiek voor deze activity. */
        int id = item.getItemId();
        if (id == R.id.action_do_task) {
            if (isOnline()) {
                // Dit is stap 1. RequestData wordt aangeroepen. Je stuurt hier in de URL mee
                requestData(urls.TEST); // In mijn geval is dat een testpagina
            } else {
                showToast("Geen netwerk verbinding");
            }
        }
        if (id == R.id.action_empty) {
            clearDisplay();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.network_test_menu, menu);
        return true;
    }

    private void requestData(String uri) {
        //Eerst wordt de Request package geinstantieerd. Hierin worden oa de parameters opgeslagen
        RequestPackage p = new RequestPackage();

        p.setMethod("POST"); // Deze mag je op POST laten staan
        p.setUri(uri);
        p.setParam("person_id", "2"); // Dit zijn je params, je kan er meerdere mee sturen. In dit geval vraag ik person op met ID 2

        Log.e("getParam", p.getEncodedParams()); //logging

        MyTask mytask = new MyTask(); // Dit is wel vereist.
        // Hiermee kan je de thread die met de server connect scheiden van de thread voor de weergave (loader animmatie)
        mytask.execute(p); // en gaan
    }

    //specifiek voor mijn activity, om de display weer leeg te maken. Negeer dit
    private void clearDisplay() {
        output.setText("");
    }

    // De method die er voor zorgt dat de output die je krijgt wordt weergegeven in je layout
    private void updateDisplay() {
        if (person_model_list != null) {
            for (Person_model person_model : person_model_list ) {
                output.append(person_model.getFirstName() + " " + person_model.getLastName());
//                output.append(person_model.getFirstName() + "\n");
            }
        } else {
            Log.e("leeg", "jup");
        }
    }

    // Vereist. Copy->paste, en hier en daar wat aanpassen
    private class MyTask extends AsyncTask<RequestPackage, String, String>
    {
        protected void onPreExecute() {
            // init progressbar. Zorg er voor dat je een progressbar hebt
            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            myTasks.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            //Laat je httpmanager de data versturen en ophalen.
            String content = HttpManagerTest.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result) {
            // Vang je result op, en voer het aan je parser die het omzet naar een java model
            person_model_list = Person_model_JSON_parser.parseFeed(result);

            updateDisplay();

            myTasks.remove(this); // om ervoor te zorgen dat als er geen taak meer is, de progressbar uit te zetten

            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) { // Ja eigenlijk geen idee ._.
//            updateDisplay(values[0]);
        }
    }
}
