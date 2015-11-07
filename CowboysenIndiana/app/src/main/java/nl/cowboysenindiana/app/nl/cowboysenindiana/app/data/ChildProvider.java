package nl.cowboysenindiana.app.nl.cowboysenindiana.app.data;

import java.util.Date;

/**
 * ChildProvider class
 * basic class to initialise "ChildProvider" object
 * @see Person, Child
 * @author Sasha Antipin
 * @since 7-11-2015.
 */
public class ChildProvider extends Person {

    private String appeal;
    private String composition;

    private ChildProvider(){
        super.setDateCreated(new Date());
    }

    public String getAppeal() { return appeal; }
    public void setAppeal(String appeal) { this.appeal = appeal; }

    public String getComposition() { return composition; }
    public void setComposition(String composition) { this.composition = composition; }
}
