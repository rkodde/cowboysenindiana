package nl.cowboysenindiana.app.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;

/**
 * Created by thijs on 18-11-15.
 */
public class ViewChildProfileHistory extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (LinearLayout) inflater.inflate(R.layout.fragment_view_child_profile_history, container, false);
    }
}
