package nl.cowboysenindiana.app.JSON_model_parser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import nl.cowboysenindiana.app.model.User;
import nl.cowboysenindiana.app.networkTest.Person_model;

/**
 * Created by Rene on 10-12-2015.
 */
public class login_parser {

    public static User parseFeed(String content) {
        User gebruiker;
            Log.e("parser reached", "True");
        try {
            JSONObject jsonobject = new JSONObject(content);
            JSONObject login      = jsonobject.getJSONObject("login");

            if (login.getInt("login") == 0) {
                gebruiker = new User(0);
                gebruiker.setAuthentication(false);
                return gebruiker;
            }

            Log.e("Login", login.getString("login"));

            Log.e("username", login.getString("username"));

            gebruiker = new User(login.getInt("user_id"));
            gebruiker.setUsername(login.getString("username"));
            gebruiker.setAuthentication(true);
            gebruiker.setKey("azsxdcfvgbhnjm1029384756"); // todo: server has to return this value

            return gebruiker;

        } catch (JSONException e) {
            Log.e("Try", "Failed. Display Error.");
            Log.e("Errorrrr",e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
