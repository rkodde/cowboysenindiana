package nl.cowboysenindiana.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Tessa on 22/11/15.
 */
public class User implements Serializable {

    private long    userID;
    private String  username;
    private String  password;
    private boolean login;

    public static final int KEY = 31;

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    public long getUserID() {
        return this.userID;
    }

    public boolean isAuthenticated() {
        return this.login;
    }
    public void setUserID(long userID) {this.userID = userID;}
    public void setAuthentication(boolean booli) {
        this.login = booli;
    }
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}

    public User(int user_id){
        this.setUserID(user_id);
        this.login = true;
    }

    public User(){
        this.login = false;
    }

    public User(int user_id, String username, String password) {
        this.userID   = user_id;
        this.username = username;
        this.password = password;
        this.login    = true;
    }

    protected User(Parcel in) {
        userID = in.readLong();
        username = in.readString();
        password = in.readString();
        login = in.readByte() != 0x00;
    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeLong(userID);
//        dest.writeString(username);
//        dest.writeString(password);
//        dest.writeByte((byte) (login ? 0x01 : 0x00));
//    }
//
//    @SuppressWarnings("unused")
//    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
}