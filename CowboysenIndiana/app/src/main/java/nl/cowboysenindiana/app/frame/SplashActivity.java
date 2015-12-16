package nl.cowboysenindiana.app.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ImageView;

import nl.cowboysenindiana.app.login.*;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;

/**
 * Created by Rene on 15-9-2015.
 */
public class SplashActivity extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
    boolean isLoggedIn = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo= (ImageView) findViewById(R.id.kdv_logo);
        logo.setImageResource(R.drawable.kdv_logo);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing activity_splash screen with a timer. This will be useful when you
             * want to show case your app kdv_logo / company
             */

            @Override
            public void run() {
            if (isLoggedIn) {
                Intent i = new Intent(SplashActivity.this, ScheduleActivity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            }
        }, SPLASH_TIME_OUT);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
