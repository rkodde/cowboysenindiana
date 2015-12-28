package nl.cowboysenindiana.app.frame;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.ArrayList;
import nl.cowboysenindiana.app.profile.ViewChildProfile;
import nl.cowboysenindiana.app.networkTest.MainActivity;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.Dashboard;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.R;
import nl.cowboysenindiana.app.rooster.cowboysenindiana.PresenceListActivity;
import nl.cowboysenindiana.app.schedule.ScheduleActivity;
import nl.cowboysenindiana.app.network.URL;

/**
 * Created by Rene on 12-11-2015.
 */
public abstract class BaseActivity extends Activity {

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public URL urls;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        initMenu();
        goNext();
    }





//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_group_presence_list) {
//            Intent i = new Intent(this, PresenceListActivity.class);
//            startActivity(i);
//            finish();
//            return true;
//        }
//
//        if(id == R.id.action_schedule_main){
//            Intent i = new Intent(this, ScheduleActivity.class);
//            startActivity(i);
//            finish();
//            return true;
//
//        }
//
//        if(id == R.id.action_view_child_profile){
//            Intent i = new Intent(this, ViewChildProfile.class);
//            startActivity(i);
//            finish();
//            return true;
//        }
//        return false;
//    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo    = cm.getActiveNetworkInfo();
        urls                   = new URL();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected abstract int getContentView();

    protected abstract void goNext();
//    protected abstract Boolean setItemsSelected();

    private void initMenu(){
        RelativeLayout mainContent = (RelativeLayout)findViewById(R.id.mainContent);
        View child = getLayoutInflater().inflate(getContentView(), null);
        mainContent.addView(child);

        mNavItems.add(new NavItem("Dashboard", "Meetup destination", R.drawable.ic_add_white_48dp, ScheduleActivity.class));
//        mNavItems.add(new NavItem("Schedule", "Change your preferences", R.drawable.ic_add_white_48dp, ScheduleActivity.class));
        mNavItems.add(new NavItem("Signed in", "Get to know about us", R.drawable.ic_add_white_48dp, PresenceListActivity.class));
        mNavItems.add(new NavItem("Child Profile", "Get to know the child", R.drawable.ic_add_white_48dp, ViewChildProfile.class));
        mNavItems.add(new NavItem("Network Test", "To test the network files", R.drawable.ic_add_white_48dp, MainActivity.class));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
                showToast("" + id);
            }
        });
    }
    /*
* Called when a particular item from the navigation drawer
* is selected.
* */
    private boolean selectItemFromDrawer(int position) {
        Intent i = new Intent(this, mNavItems.get(position).mNewActivity);
        startActivity(i);
        finish();
        return true;
    }

}
