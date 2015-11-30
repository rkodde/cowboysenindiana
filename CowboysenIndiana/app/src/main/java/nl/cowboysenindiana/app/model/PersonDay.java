package nl.cowboysenindiana.app.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Tessa on 30/11/15.
 */
public class PersonDay {
    private int group_id;
    private int person_id;
    private int day;
    private Time    time_start, time_end;
    private Date    date_start, date_end;
}
