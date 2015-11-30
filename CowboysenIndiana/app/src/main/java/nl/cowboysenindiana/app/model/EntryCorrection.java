package nl.cowboysenindiana.app.model;


import java.sql.Date;

/**
 * Created by Tessa on 30/11/15.
 */
public class EntryCorrection extends Entry {

    private int     entry_id;
    private int     entry_correction_id;
    private String  entry_correctioncol;
    private int     type;
    private int     confirm;
    private Date    time_arrival, time_departure;
    private Date    created_at, changed_at;
    private String  description;
}
