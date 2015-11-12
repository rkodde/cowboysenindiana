package nl.cowboysenindiana.app.frame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.Dashboard;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Rene on 15-9-2015.
 */
public class SplashActivity extends BaseActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//    }

    @Override
    protected void goNext(){
        ImageView logo= (ImageView) findViewById(R.id.kdv_logo);
        logo.setImageResource(R.drawable.kdv_logo);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing activity_splash screen with a timer. This will be useful when you
             * want to show case your app kdv_logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, Dashboard.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }
}
