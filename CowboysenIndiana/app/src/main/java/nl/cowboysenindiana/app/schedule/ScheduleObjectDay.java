package nl.cowboysenindiana.app.schedule;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tessa on 22/11/15.
 */
public class ScheduleObjectDay extends Date {

    public int itemId;
    public Date itemDayDate;
    public String itemLeadSigned;
    public Integer itemEntityScheduled;

    public ScheduleObjectDay(Date itemDayDate, String itemLeadSigned, Integer itemEntityScheduled) {
        this.itemDayDate = itemDayDate;
        this.itemLeadSigned = itemLeadSigned;
        this.itemEntityScheduled = itemEntityScheduled;
    }

}
