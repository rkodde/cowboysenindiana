package nl.cowboysenindiana.app.frame;

import android.app.Activity;
import android.os.Bundle;

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

    protected abstract void goNext();
    protected abstract int getLayoutResourceId();
}
