[1mdiff --git a/CowboysenIndiana/app/src/main/java/nl/cowboysenindiana/app/frame/SplashActivity.java b/CowboysenIndiana/app/src/main/java/nl/cowboysenindiana/app/frame/SplashActivity.java[m
[1mindex 996fe24..e448079 100644[m
[1m--- a/CowboysenIndiana/app/src/main/java/nl/cowboysenindiana/app/frame/SplashActivity.java[m
[1m+++ b/CowboysenIndiana/app/src/main/java/nl/cowboysenindiana/app/frame/SplashActivity.java[m
[36m@@ -4,8 +4,10 @@[m [mimport android.app.Activity;[m
 import android.content.Intent;[m
 import android.os.Bundle;[m
 import android.os.Handler;[m
[32m+[m[32mimport android.view.Menu;[m
 import android.widget.ImageView;[m
 [m
[32m+[m[32mimport nl.cowboysenindiana.app.login.*;[m
 import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;[m
 import nl.cowboysenindiana.app.schedule.ScheduleActivity;[m
 [m
[36m@@ -15,6 +17,7 @@[m [mimport nl.cowboysenindiana.app.schedule.ScheduleActivity;[m
 public class SplashActivity extends Activity {[m
     // Splash screen timer[m
     private static int SPLASH_TIME_OUT = 1000;[m
[32m+[m[32m    boolean isLoggedIn = false;[m
 [m
     protected void onCreate(Bundle savedInstanceState) {[m
         super.onCreate(savedInstanceState);[m
[36m@@ -32,13 +35,22 @@[m [mpublic class SplashActivity extends Activity {[m
 [m
             @Override[m
             public void run() {[m
[32m+[m[32m            if (isLoggedIn) {[m
                 Intent i = new Intent(SplashActivity.this, ScheduleActivity.class);[m
                 startActivity(i);[m
                 finish();[m
[32m+[m[32m            } else {[m
[32m+[m[32m                Intent i = new Intent(SplashActivity.this, LoginActivity.class);[m
[32m+[m[32m                startActivity(i);[m
[32m+[m[32m                finish();[m
[32m+[m[32m            }[m
             }[m
         }, SPLASH_TIME_OUT);[m
     }[m
 [m
[31m-[m
[32m+[m[32m    public boolean onCreateOptionsMenu(Menu menu) {[m
[32m+[m[32m        getMenuInflater().inflate(R.menu.menu_main, menu);[m
[32m+[m[32m        return true;[m
[32m+[m[32m    }[m
 [m
 }[m
