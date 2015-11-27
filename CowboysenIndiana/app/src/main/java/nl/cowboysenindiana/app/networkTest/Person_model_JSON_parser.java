package nl.cowboysenindiana.app.networkTest;

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

        try {
            JSONArray ar = new JSONArray(content);
            List<Person_model> person_model_list = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);

                Person_model person_model = new Person_model();

                person_model.setId(obj.getInt("id"));
                person_model.setFirstName(obj.getString("first_name"));
                person_model.setLastName(obj.getString("last_name"));
                person_model.setType(obj.getInt("type"));
                person_model.setGender(obj.getString("gender"));
                person_model.setAddress(obj.getString("address"));
                person_model.setCreated_at(obj.getString("created_at"));
                person_model_list.add(person_model);
            }

            return person_model_list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
