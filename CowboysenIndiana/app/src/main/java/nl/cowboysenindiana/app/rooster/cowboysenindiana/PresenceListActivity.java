package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.widget.GridView;

import java.util.List;

import nl.cowboysenindiana.app.data.ChildToTest;
import nl.cowboysenindiana.app.data.DataProviderToTest;
import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.presencelist.PresenceListContentAdapter;
import nl.cowboysenindiana.app.utilities.ScreenUtility;

public class PresenceListActivity extends BaseActivity {

    private GridView gridView;
    private boolean isInside;
    private List<ChildToTest> children;

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }


    @Override
    protected void goNext() {

        showToast("ScheduleDaySignedIn GO next");

        /** get data from data provider -------------------------*/
        children = DataProviderToTest.getData();

        refreshDisplay();
    }

    /** refresh content to display ----------------
     * @param
     * */
    public void refreshDisplay(){

        /** Initialise a gridView for PresenceList and Set ContentAdapter to GridView */
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new PresenceListContentAdapter(this, this.children));

        /** depend on screen size set columns number ------------*/
        ScreenUtility utility = new ScreenUtility(this);
        if (utility.isHorizontal()) gridView.setNumColumns(5);
        else gridView.setNumColumns(3);
    }

}
