package nl.cowboysenindiana.app.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.Dashboard;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by Rene on 12-11-2015.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        goNext();
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

//    @Override
//    protected void onBackPressed() {
//        goBack(getPreviousActivity());
//    }
//
//    public void goBack(Intent intent){
//        startActivity(intent);
//        finish();
//    }

    protected abstract void goNext();
    protected abstract int getLayoutResourceId();
//    protected abstract Intent getPreviousActivity();

}
