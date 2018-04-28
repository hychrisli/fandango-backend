package cmpe273.fandango.lib;

import java.util.Calendar;
import java.util.Date;

public class DateTime {

  public static Date getToday() {
    return getDate(new Date());
  }

  public static Date getDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }


  public static Date getMonth(Date date) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    cal.set(Calendar.DAY_OF_MONTH, 0);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }

}
