package nl.cowboysenindiana.app.networkTest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 22-11-2015.
 */
public class Person_model_JSON_parser {

    public static List<Person_model> parseFeed(String content) {
        Log.e("Parsefeed", content);
        try {
            JSONObject json = new JSONObject(content);


            List<Person_model> person_model_list = new ArrayList<>();

            Log.e("list to string", person_model_list.toString());
            for (int i = 0; i < json.length(); i++) {
//                Log.e("list to string", person_model_list.toString());
//                JSONObject obj = ar.getJSONObject(i);
                JSONObject obj = json.getJSONObject("persons");
//                Log.e("obj", "still cool");
//
                Person_model person_model = new Person_model();
//                Log.e("person model", "created");
                person_model.setId(obj.getInt("person_id"));
                person_model.setType(obj.getInt("person_type"));
                person_model.setFirstName(obj.getString("first_name"));
                person_model.setLastName(obj.getString("last_name"));
                person_model.setGender(obj.getString("gender"));
                person_model.setCreated_at(obj.getString("created_at"));

                person_model_list.add(person_model);
//                Log.e("person_first_name", person_model.getFirstName());
            }

            return person_model_list;
        } catch (JSONException ex) {
            Log.e("Fout?", "Ja");
            Log.e("Exception", ex.toString());
            ex.printStackTrace();
            return null;
        }
    }
}
