package nl.cowboysenindiana.app.JSON_model_parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import nl.cowboysenindiana.app.model.Child;

import nl.cowboysenindiana.app.model.Entry;
import nl.cowboysenindiana.app.networkTest.Person_model;

/**
 * Created by Rene on 22-11-2015.
 */
public class child_overview_group {

    public static List<Child> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Child> child_list = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);

                Child child_model = new Child();

                child_model.setId(obj.getInt("person_id"));
                child_model.setFirstName(obj.getString("first_name"));
                child_model.setImage(obj.getString("image"));
                child_model.setLastName(obj.getString("last_name"));


                Entry entry_model = new Entry();

                entry_model.setConfirm(obj.getInt("confirm"));
                entry_model.setGroup_id(obj.getInt("group_id"));
                entry_model.setPerson(child_model);

                child_list.add(child_model);

            }

            return child_list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
