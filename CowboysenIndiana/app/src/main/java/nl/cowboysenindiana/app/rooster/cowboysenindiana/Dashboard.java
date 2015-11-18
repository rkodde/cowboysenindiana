package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.content.Intent;
import android.widget.Toast;

import nl.cowboysenindiana.app.frame.BaseActivity;

public class Dashboard extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void goNext(){
        Toast.makeText(getApplicationContext(), "Dashboard",
                Toast.LENGTH_LONG).show();
        /** Initialise a gridView for PresenceList and Set ContentAdapter to GridView */
//        GridView gridView = (GridView) findViewById(R.id.gridview);
//        gridView.setAdapter(new ContentAdapter(this));
    }

}
