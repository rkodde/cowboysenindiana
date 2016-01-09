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
import nl.cowboysenindiana.app.model.Person;

import static nl.cowboysenindiana.app.model.Person.*;

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
                    Date date = null;
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    child.setId(object.getLong("id"));
                    child.setFirstName(object.getString("firstName"));
                    child.setLastName(object.getString("lastName"));
                    child.setPersonType(object.getString("type"));
                    child.setGender(object.getString("gender"));
                    child.setContactInformation(object.getString("contactInformation"));

                    try {
                        date = formater.parse(object.getString("created"));
                        child.setDateCreated(date);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {
                        date = formater.parse(object.getString("birthDay"));
                        child.setBirthDay(date);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {
                        date = formater.parse(object.getString("dateOfPlacement"));
                        child.setDateOfPlacement(date);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

//                    child.setParticularities(object.getString("particularities"));
//                    child.setChildProviders(object.getString("providers"));
//                    child.setPrimaryChildProvider(object.getString("primaryProvider"));


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
