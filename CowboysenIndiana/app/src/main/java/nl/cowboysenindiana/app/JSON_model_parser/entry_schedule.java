package nl.cowboysenindiana.app.JSON_model_parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nl.cowboysenindiana.app.model.Entry;

/**
 * Created by Rene on 22-11-2015.
 */
public class entry_schedule{

    public static List<Entry> parseFeed(String content) {
        try {
            JSONObject json = new JSONObject(content);
            List<Entry> entry_model_list = new ArrayList<>();

            JSONArray contacts = json.getJSONArray("entry");

            for (int i = 0; i < contacts.length(); i++) {
//                JSONObject obj = json.getJSONObject("entry");
                JSONObject c = contacts.getJSONObject(i);

                Entry entry_model = new Entry();
                entry_model.setGroup_id(c.getInt("group_id"));
//                java.sql.Date sqlStartDate = new java.sql.Date(c.get("date"));
                entry_model.setDate(java.sql.Date.valueOf(String.valueOf(c.get("date"))));
//                entry_model.setDate();
//                entry_model.setKids_total(obj.getInt("total_kids"));
//                entry_model.settr(obj.getInt("total_teacher"));
//                Log.e("PARS", i+" "+String.valueOf(entry_model.getGroup_id()));
                entry_model_list.add(entry_model);
            }


            return entry_model_list;
        } catch (JSONException ex) {
            Log.e("Fout?", "Ja");
            Log.e("Exception", ex.toString());
            ex.printStackTrace();
            return null;
        }
    }
}
//http://www.androidhive.info/2012/01/android-json-parsing-tutorial/