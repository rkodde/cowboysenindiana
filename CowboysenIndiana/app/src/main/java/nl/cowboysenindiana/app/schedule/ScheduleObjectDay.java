package nl.cowboysenindiana.app.schedule;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tessa on 22/11/15.
 */
public class ScheduleObjectDay {

    public int itemId;
    public String itemDayDate;
    public String itemLeadSigned;
    public Integer itemEntityScheduled;

    public ScheduleObjectDay(String itemDayDate, String itemLeadSigned, Integer itemEntityScheduled) {
        this.itemDayDate = itemDayDate;
        this.itemLeadSigned = itemLeadSigned;
        this.itemEntityScheduled = itemEntityScheduled;
    }

}
