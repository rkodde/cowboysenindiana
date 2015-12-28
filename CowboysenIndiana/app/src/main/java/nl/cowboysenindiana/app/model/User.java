package nl.cowboysenindiana.app.model;

/**
 * Created by Tessa on 22/11/15.
 */
public class User {

    private long userID;
    private String username;
    private boolean login;
    private String key;

    public String getUsername() {
        return username;
    }


    public long getUserID() {
        return userID;
    }

    public boolean isAthenticated() {
        return login;
    }
    public void setUserID(long userID) {
        this.userID = userID;
    }
    public void setAuthentication(boolean booli) {
        this.login = booli;
    }
    public void setKey(String key){this.key = key;}
    public void setUsername(String username) {this.username = username;}

    public User(int user_id){
        this.setUserID(user_id);
        this.login = true;
    }

}
