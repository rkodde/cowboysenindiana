package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.content.Intent;
import android.widget.Toast;

import nl.cowboysenindiana.app.frame.BaseActivity;

public class Dashboard extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }
    protected int getMenuView() {return R.menu.menu_main;}

    @Override
    protected void goNext(){
        Toast.makeText(getApplicationContext(), "Dashboard",
                Toast.LENGTH_LONG).show();
    }

}
