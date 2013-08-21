package framework.core.utilities;

import java.util.Date;

public interface DateUtils {

    public abstract Long addSecondsUnixTime(Integer seconds);

    Date convertToDate(Long unixtime);

    Long convertToUnixTime(Date date);

    Date getCurrentTime();

    Long getCurrentUnixTime();

    boolean isBefore(Long dateToCompare);
    
    boolean isAfter(Long dateToCompare);
}