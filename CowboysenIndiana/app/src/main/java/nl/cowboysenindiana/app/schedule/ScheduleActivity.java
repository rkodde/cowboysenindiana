package nl.cowboysenindiana.app.schedule;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nl.cowboysenindiana.app.JSON_model_parser.entry_schedule;
import nl.cowboysenindiana.app.database.GroupDBHandler;
import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.model.Entry;
import nl.cowboysenindiana.app.model.Group;
import nl.cowboysenindiana.app.networkTest.HttpManagerTest;
import nl.cowboysenindiana.app.networkTest.RequestPackage;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.PresenceListActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;


public class ScheduleActivity extends BaseActivity{

    public Date currentDate;
    GridView output; // type output
    ProgressBar progressBar; // om gebruikers te laten zien dat de app op de achtergrond iets doet
    List<MyTask> myTasks; //Lijst van je threads.

    List<Entry> entry_model_list;

    @Override
    protected void goNext() {

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);

        myTasks = new ArrayList<>();

        String cActionbarTheme = "Dashboard";

        if (isOnline()) {
            requestData(urls.Schedule);
        } else {
            showToast("Geen netwerk verbinding");
        }


        // Get new DV
        GroupDBHandler db = new GroupDBHandler(this);

//         ADD Group To DB
      db.addGroup(new Group("Group 1", "#997733"));
//      db.addGroup(new Group("Group 2", "#857453"));
//      db.addGroup(new Group("Group 3", "#998634"));


        // Get All Groups from DB
        List<Group> groups = db.getAllGroups();

        // Get The LinearLayout
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scheduleLinearLayout);

        // Loop all Groups
        if(groups.size() != 0) {



            for (Group cn : groups) {


                String leadSigned = "Monica, Lowinskie";

                Date today = new Date();

                // Set The Data of DB to Abject Day and Gen.
                ScheduleObjectDay[] ObjectItemData = new ScheduleObjectDay[31];
                for (int i = 0; i < ObjectItemData.length; i++) {

                    if (i > 0) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(today);
                        cal.add(Calendar.DATE, 1);

                        today = cal.getTime();
                    }
                    ObjectItemData[i] = new ScheduleObjectDay(today, leadSigned, (25 + i));
                }


                // Log Get all DB groups back
                String log = "Id: " + cn.getGroupID() + " , " + cn.getGroupName() + " , " + cn.getColor();
                Log.d("Name: ", log);

                // Create new Adapter for Schedule_grid_layout
                ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.schedule_grid_layout, ObjectItemData);

                // Areate a new ListView, set the adapter and item click listener
                GridView gridView = new GridView(this);
                gridView.setAdapter(adapter);
                adapter.setGroupColor(cn.getColor());
                gridView.setBackgroundResource(R.drawable.schedule_border);
                gridView.setNumColumns(7);
                gridView.setClipChildren(false);
                gridView.setPadding(5, 5, 0, 50);
                gridView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(gridView);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectItemFromDrawer(position);
                        showToast("" + id);
                    }
                });


                SimpleDateFormat sdfYear = new SimpleDateFormat("MMMM yyyy");
                String dayDateItemFormat = sdfYear.format(today);

                cActionbarTheme = dayDateItemFormat;
            }
        }else{
            TextView msg = new TextView(this);
            msg.setText("No data available");
            msg.setPadding(10, 10, 10, 10);
            msg.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            linearLayout.addView(msg);
        }

        // Set actionBarTheme
        this.setActionBarTheme(cActionbarTheme);

    }




    private void requestData(String uri) {
        Date today = new Date();
        //Eerst wordt de Request package geinstantieerd. Hierin worden oa de parameters opgeslagen
        RequestPackage p = new RequestPackage();

        p.setMethod("POST"); // Deze mag je op POST laten staan
        p.setUri(uri);
        p.setParam("group_id", "1"); // Dit zijn je params, je kan er meerdere mee sturen. In dit geval vraag ik person op met ID 2
        //p.setParam("date", "2015-12-10"); // Dit zijn je params, je kan er meerdere mee sturen. In dit geval vraag ik person op met ID 2

        Log.e("getParam", p.getEncodedParams()); //logging

        MyTask mytask = new MyTask(); // Dit is wel vereist.
        // Hiermee kan je de thread die met de server connect scheiden van de thread voor de weergave (loader animmatie)
        mytask.execute(p); // en gaan
    }

    public void setActionBarTheme(String cActionbarTheme){


        // Set first char Capital! Bam Bam Bam
        cActionbarTheme = cActionbarTheme.substring(0,1).toUpperCase() + cActionbarTheme.substring(1);

        this.setTitle(cActionbarTheme);

        // Change to setActionBarBG(getMontPic())
        Resources res = getResources();
        Bitmap bMap = BitmapFactory.decodeResource(res, R.drawable.action_christmas);
        BitmapDrawable actionBarBackground = new BitmapDrawable(res, bMap);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(actionBarBackground);
    }

    @Override
    protected int getContentView() {
        return R.layout.schedule_main;
    }

    private boolean selectItemFromDrawer(int position) {
        Intent i = new Intent(this, PresenceListActivity.class);
        startActivity(i);
        return true;
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

        // De method die er voor zorgt dat de output die je krijgt wordt weergegeven in je layout
        private void updateDisplay() {

            if (entry_model_list != null) {
                for(Entry entry_model : entry_model_list ) {

                    Log.e("AAP2", entry_model.getGroup_id()+" "+entry_model.getDate());
//                output.append(person_model.getFirstName() + "\n");

                }

            } else {
                Log.e("leeg", "jup");
            }



        }

        protected void onPostExecute(String result) {
            // Vang je result op, en voer het aan je parser die het omzet naar een java model
            entry_model_list = entry_schedule.parseFeed(result);
            Log.e("AAP@", result);

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
