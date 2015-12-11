package nl.cowboysenindiana.app.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Tessa on 30/11/15.
 */
public class Entry {
    private int     entry_id;
    private Person  person;
    private int     group_id;
    private Date    date;
    private int     confirm;
    private Date    time_arrival, time_departure;
    private Date    created_at, changed_at;
    private String  description;
    private int     kids_total;

    public int getTeachers_total() {
        return teachers_total;
    }

    public void setTeachers_total(int teachers_total) {
        this.teachers_total = teachers_total;
    }

    private int     teachers_total;

    public int getKids_total() {
        return kids_total;
    }

    public void setKids_total(int kids_total) {
        this.kids_total = kids_total;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }



    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public Date getTime_arrival() {
        return time_arrival;
    }

    public void setTime_arrival(Date time_arrival) {
        this.time_arrival = time_arrival;
    }

    public Date getTime_departure() {
        return time_departure;
    }

    public void setTime_departure(Date time_departure) {
        this.time_departure = time_departure;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getChanged_at() {
        return changed_at;
    }

    public void setChanged_at(Date changed_at) {
        this.changed_at = changed_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
