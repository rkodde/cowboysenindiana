package nl.cowboysenindiana.app.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.PresenceListActivity;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;

/**
 * Created by Rene on 12-11-2015.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        initMenu();
        goNext();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, PresenceListActivity.class);
            startActivity(i);

            return true;
        }

        if(id == R.id.action_schedule_main){
            Intent i = new Intent(this, ScheduleActivity.class);
            startActivity(i);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected abstract int getContentView();
    protected abstract void goNext();

    private void initMenu(){
        RelativeLayout mainContent = (RelativeLayout)findViewById(R.id.mainContent);
        View child = getLayoutInflater().inflate(getContentView(), null);
        mainContent.addView(child);
    }

}
