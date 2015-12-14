package nl.cowboysenindiana.app.network;

/**
 * Created by Rene on 7-12-2015.
 */
public class URL {
    private String BASE = "http://tqm.cowboysenindiana.nl/";

    final public String TEST  = BASE + "person.php";
    final public String LOGIN = BASE + "login.php";
    final public String Schedule = BASE + "schedule_group.php";
    final public String Kids = BASE + "retrieve_kids.php"; 

    public boolean URL () {
        return true;
    }
}
