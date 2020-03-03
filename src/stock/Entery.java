package stock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Entery {
    private final String ticker;
    private final float open_price, close_price, adjusted_close, low, high, volume;
    private final DateTime dateTime;

    public Entery(String ticker, float open_price, float close_price, float adjusted_close, float low, float high, float volume, DateTime date) {
        this.ticker = ticker;
        this.open_price = open_price;
        this.close_price = close_price;
        this.adjusted_close = adjusted_close;
        this.low = low;
        this.high = high;
        this.volume = volume;
        this.dateTime = date;
    }
    public Entery(String entry){
        Matcher matcher = Pattern.compile( "(?:([A-Z]+),)(?:(\\d+.\\d*),)(?:(\\d+.\\d*),)(?:(\\d+.\\d*),)(?:(\\d+.\\d*),)(?:(\\d+.\\d*),)(?:(\\d+),)" +
                "(?:((?:\\d{4})[-/](?:\\d{1,2})[-/](?:\\d{1,2}))|((?:\\d{1,2})[-/](?:\\d{1,2})[-/](?:\\d{4}))).*").matcher(entry);
        if(!matcher.matches()){
            throw new IllegalArgumentException("unable to extract entrie's infromation from <"+entry+">");
        }else {
            ticker = matcher.group(1);
            open_price = Float.parseFloat(matcher.group(2));
            close_price = Float.parseFloat(matcher.group(3));
            adjusted_close = Float.parseFloat(matcher.group(4));
            low = Float.parseFloat(matcher.group(5));
            high = Float.parseFloat(matcher.group(6));
            volume = Integer.parseInt(matcher.group(7));
            dateTime = new DateTime((matcher.group(8).equals("")?matcher.group(9):matcher.group(8)));
        }
    }

    @Override
    public String toString() {
        return ticker+","+open_price+","+close_price+","+adjusted_close+","+low+","+high+","+volume+","+dateTime.yyyyMmDd();
    }
}
