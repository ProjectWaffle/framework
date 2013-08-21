package framework.support.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import framework.core.utilities.DateUtils;
import framework.core.utilities.PropertiesUtil;

@Named
public class DateUtilsImpl implements DateUtils {

    private final DateTimeZone dateTimeZone;

    @Inject
    public DateUtilsImpl(PropertiesUtil propertiesUtil) {
        final TimeZone timezone = TimeZone.getTimeZone(propertiesUtil.getProperty("framework.timezone"));
        this.dateTimeZone = DateTimeZone.forTimeZone(timezone);
    }

    @Override
    public Date convertToDate(Long unixtime) {
        if (unixtime != null) {
            return new DateTime(TimeUnit.SECONDS.toMillis(unixtime), this.dateTimeZone).toDate();
        }
        return null;
    }

    @Override
    public Long convertToUnixTime(Date date) {
        if (date != null) {
            return TimeUnit.MILLISECONDS.toSeconds(new DateTime(date, this.dateTimeZone).getMillis());
        }
        return null;
    }

    @Override
    public Date getCurrentTime() {
        return new DateTime(this.dateTimeZone).toDate();
    }

    @Override
    public Long getCurrentUnixTime() {
        return this.convertToUnixTime(Calendar.getInstance().getTime());
    }
    
    @Override
    public Long addSecondsUnixTime(Integer seconds) {
        return convertToUnixTime(DateTime.now().plusSeconds(seconds).toDate());
    }

    @Override
    public boolean isBefore(Long dateToCompare) {
        return this.getCurrentUnixTime() < dateToCompare;
    }
    
    @Override
    public boolean isAfter(Long dateToCompare) {
        return this.getCurrentUnixTime() > dateToCompare;
    }
}
