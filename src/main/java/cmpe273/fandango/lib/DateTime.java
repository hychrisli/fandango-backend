package cmpe273.fandango.lib;

import java.util.Calendar;
import java.util.Date;

public class DateTime {

  public static Date getToday() {
    Date today = new Date();

    Calendar cal = Calendar.getInstance();
    cal.setTime(today);

    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    today = cal.getTime();
    return today;
  }

}
