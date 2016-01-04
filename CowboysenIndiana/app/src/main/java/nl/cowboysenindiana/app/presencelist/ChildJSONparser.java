package nl.cowboysenindiana.app.presencelist;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.cowboysenindiana.app.model.Child;

/**
 * ChildJSONparser utility class
 * to parse a content from JSON array to List<Child>
 * Project : CowboysenIndiana
 *
 * @autor Sasha
 * @since 3-1-2016
 */
public class ChildJSONparser {

        public static List<Child> parseFeed(String content) {

            try {
                JSONArray array = new JSONArray(content);
                List<Child> dataList = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Child child = new Child();

                    Date birthDay = null;
                    try {
                        birthDay = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                                .parse(object.getString("birthDay"));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    Date dateOfPlacement = null;
                    try {
                        dateOfPlacement = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                                .parse(object.getString("dateOfPlacement"));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    child.setBirthDay(birthDay);
                    child.setParticularities(object.getString("particularities"));
                    child.setChildProviders(object.getString("providers"));
                    child.setDateOfPlacement(dateOfPlacement);
                    child.setInside(object.getBoolean("isInside"));
                    child.setImage(object.getString("image"));

                    dataList.add(child);
                }
                return dataList;

            } catch (JSONException e) {
                Log.e("Fout?", "Ja");
                Log.e("Exception", e.toString());
                e.printStackTrace();
                return null;
            }

        }
    }
