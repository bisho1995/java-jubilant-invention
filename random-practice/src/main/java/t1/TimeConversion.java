package t1;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class TimeConversion {
    public static void main(String[] args) throws ParseException {
        String ksaDropOffStr = "2023-02-20T12:52:14";
        TimeZone saudiArabiaTimeStamp = TimeZone.getTimeZone("Asia/Riyadh");

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        System.out.println("now time = " + time);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        dateFormat.setTimeZone(saudiArabiaTimeStamp);
        String nowKsaTimestamp = dateFormat.format(time);
        System.out.println("now Ksa Timestamp = " + nowKsaTimestamp);

        dateFormat.setTimeZone(saudiArabiaTimeStamp);

        Instant ksaDropOffInstant = getInstant(ksaDropOffStr);
        System.out.println("KSA dropoff "+ ksaDropOffInstant);

        Instant nowInKSAInstant = getInstant(nowKsaTimestamp);
        System.out.println("now time in KSA = "+nowInKSAInstant);

        Duration timeDifference = Duration.between(ksaDropOffInstant,nowInKSAInstant);

        System.out.println(timeDifference);
        System.out.println(timeDifference.toMillis());
    }

    static Instant getInstant(String dateTime) {
        return Instant.parse(dateTime+"Z");
    }
}
