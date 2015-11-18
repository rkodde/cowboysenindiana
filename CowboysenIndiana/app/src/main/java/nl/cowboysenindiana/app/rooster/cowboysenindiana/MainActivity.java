package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void goNext() {
        TextView texttexttext = (TextView) findViewById(R.id.texttexttext);
        Toast.makeText(getApplicationContext(), texttexttext.getText(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

}
