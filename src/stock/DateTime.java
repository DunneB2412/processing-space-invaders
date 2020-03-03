package stock;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTime {
    private final long time;
    private final int day, month, year;

    public DateTime(long time, int day, int month, int year) {
        this.time = time;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public DateTime(String dateTime){
        LocalDateTime dateTimeNow = java.time.LocalDateTime.now();
        time = dateTimeNow.getNano();
        Matcher matcher = Pattern.compile("(?:(?:(\\d{4})[-/](\\d{1,2})[-/](\\d{1,2}))|(?:(\\d{1,2})[-/](\\d{1,2})[-/](\\d{2,4})))[.\\r\\n]*").matcher(dateTime);
        if(matcher.matches()){
            if (!matcher.group(1).equals("")){
                day = Integer.parseInt(matcher.group(1));
                month = Integer.parseInt(matcher.group(2));
                year = Integer.parseInt(matcher.group(3));
            }else{
                day = Integer.parseInt(matcher.group(4));
                month = Integer.parseInt(matcher.group(5));
                year = Integer.parseInt(matcher.group(6));
            }
        }else{
            day = dateTimeNow.getDayOfMonth();
            month = dateTimeNow.getMonthValue();
            year = dateTimeNow.getYear();
        }
    }

    public String yyyyMmDd() {
        return year+"-"+month+"-"+day;
    }
}
