package nl.cowboysenindiana.app.network;

/**
 * Created by Rene on 7-12-2015.
 */
public class URL {
    private String BASE = "http://tqm.cowboysenindiana.nl/";

    final public String TEST  = BASE + "person.php";
    final public String LOGIN = BASE + "login.php";

    public boolean URL () {
        return true;
    }
}
