package nl.cowboysenindiana.app.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Tessa on 30/11/15.
 */
public class Entry {
    private int entry_id;
    private int persons_id;
    private int group_id;
    private Date    date;
    private int confirm;
    private Time    time_arrival, time_departure;
    private Date    created_at, changed_at;
    private String  description;
}
