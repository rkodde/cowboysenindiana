package nl.cowboysenindiana.app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import nl.cowboysenindiana.app.model.Person;

/**
 * ChildProvider class
 * basic class to initialise "ChildProvider" object
 * @see Person , Child
 * @author Sasha Antipin
 * @since 7-11-2015.
 */
public class Provider extends Person {

    private String appeal;
    private String composition;

    private Provider(){
        super.setDateCreated(new Date());
    }

    public String getAppeal() { return appeal; }
    public void setAppeal(String appeal) { this.appeal = appeal; }

    public String getComposition() { return composition; }
    public void setComposition(String composition) { this.composition = composition; }

    @Override
    public String toString() {
        return super.toString();
    }
}
