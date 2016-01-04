package nl.cowboysenindiana.app.rooster.cowboysenindiana;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.cowboysenindiana.app.frame.BaseActivity;
import nl.cowboysenindiana.app.model.Child;
import nl.cowboysenindiana.app.network.HttpManager;
import nl.cowboysenindiana.app.networkTest.RequestPackage;
import nl.cowboysenindiana.app.presencelist.ChildJSONparser;
import nl.cowboysenindiana.app.presencelist.PresenceListContextMenuFragment;
import nl.cowboysenindiana.app.utilities.ScreenUtility;
import nl.cowboysenindiana.app.utilities.UIHelper;

/**
 * PresenceListActivity class
 * responsible for presentation of Presence list
 * @uses inner class PresenceListContentAdapter
 * @see BaseActivity, GridView, SquareImageView, BaseAdapter, UIHelper, ColorMatrix, ActionMode.Callback
 *      PresenceListMenuAdapter, PresenceListContextMenuFragment
 * @author Sasha Antipin
 * @since 16-11-2015
 * @version 0.3
 */

public class PresenceListActivity extends BaseActivity {

    private GridView gridView;
    private List<Child> children;

    private Child child;
    private String childName;
    private boolean isInside;

    private ProgressBar progressBar;
    List<MyRequestTask> myTasks;

    public static final String CHILD_BUNDLE = "CHILD_BUNDLE";
    private static final int REQUEST_CODE = 1001;

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void goNext() {

        showToast("get data for PresenceList");

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);

        myTasks = new ArrayList<>();

        /** get data from server ---------------- */
        if (isOnline()) {
            // Dit is stap 1. RequestData wordt aangeroepen. Je stuurt hier in de URL mee
            requestData(urls.Kids); // In mijn geval is dat een Kids
        } else {
            showToast("Geen netwerk verbinding");
        }

        refreshDisplay();
    }

    /** refresh content to display ----------------
     * @param
     * */
    public void refreshDisplay(){

        /** Initialise a gridView for PresenceList and Set ContentAdapter to GridView */
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(this.new PresenceListContentAdapter(this));

        /** depend on screen size set columns number ------------*/
        ScreenUtility utility = new ScreenUtility(this);
        if (utility.isHorizontal()) gridView.setNumColumns(5);
        else gridView.setNumColumns(3);
    }


    /** inner class PresenceListContentAdapter ====================================================
     *  to initialise GridView with content
     *  @see BaseAdapter, SquareImageView, UIHelper, ColorMatrix, ActionMode.Callback
     */
    private final class PresenceListContentAdapter extends BaseAdapter {

        private final LayoutInflater inflater;
        private Context context;

        public PresenceListContentAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() { return children.size(); }

        @Override
        public Object getItem(int position) { return children.get(position); }

        @Override
        public long getItemId(int position) {
            return children.get(position).getId();      // getId() for Child
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = inflater.inflate(R.layout.presencelist_grid_item, viewGroup, false);
                view.setTag(R.id.picture, view.findViewById(R.id.picture));
                view.setTag(R.id.text, view.findViewById(R.id.text));
            }

            child = (Child) getItem(position);
            childName = child.getFirstName() + " " + child.getLastName();
            isInside = child.isInside();

            ImageView picture = (ImageView) view.getTag(R.id.picture);
            String imageId = "drawable/child_smile" + (1 + (int)(Math.random() * 8));
            final int currentImage = this.context.getResources().getIdentifier(imageId, null, context.getPackageName());
            picture.setImageResource(currentImage);
            if (isInside != true) setColor(picture);

            UIHelper.displayText(view, R.id.text, childName + " " + isInside);

            view.isClickable();


            /** set setOnClickListener on item --------------------------------*/
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isInside != true) child.setInside(true);
                    else child.setInside(false);

                    PresenceListActivity.this.refreshDisplay();
                    Toast.makeText(context, "Status geweizigd", Toast.LENGTH_SHORT).show();
                }
            });// ---------------------------------------- closing the setOnClickListener method


            /** set setOnLongClickListener on item -----------------------------*/
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Bundle bundle = child.toBundle();

                    PresenceListContextMenuFragment dialog = new PresenceListContextMenuFragment();
                    //dialog.setArguments(bundle);
                    dialog.show(getFragmentManager(), "my dialog");

                    return true;
                }
            }); // ------------------------------------ closing the setOnLongClickListener method

            return view;
        }

        private void setColor(ImageView picture) {
            /** Converting color image to grayscale if isInside equal false --------------- */
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            picture.setColorFilter(filter);
        }
    }



    /**
     * Methode starts MyRequestTask to get data from the server
     * @param uri
     */
    private void requestData(String uri) {
        //Eerst wordt de Request package geinstantieerd. Hierin worden oa de parameters opgeslagen
        RequestPackage p = new RequestPackage();

        p.setMethod("POST"); // Deze mag je op POST laten staan
        p.setUri(uri);
       // p.setParam("person_id", "2"); // Dit zijn je params, je kan er meerdere mee sturen. In dit geval vraag ik person op met ID 2

        Log.e("getParam", p.getEncodedParams()); //logging

        MyRequestTask mytask = new MyRequestTask(); // Dit is wel vereist.
        // Hiermee kan je de thread die met de server connect scheiden van de thread voor de weergave (loader animmatie)
        mytask.execute(p); // en gaan
    }

    /**
     * MyRequestTask inner class
     * responsible for paralell
     */
    private class MyRequestTask extends AsyncTask<RequestPackage, String, String>
    {
        protected void onPreExecute() {
            // init progressbar. Zorg er voor dat je een progressbar hebt
            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            myTasks.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            //Laat je httpmanager de data versturen en ophalen.
            String content = HttpManager.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result) {
            // Vang je result op, en voer het aan je parser die het omzet naar een java model
            children = ChildJSONparser.parseFeed(result);

            refreshDisplay();

            myTasks.remove(this); // om ervoor te zorgen dat als er geen taak meer is, de progressbar uit te zetten

            if (myTasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) { // Ja eigenlijk geen idee ._.
//            updateDisplay(values[0]);
        }
    }

}
