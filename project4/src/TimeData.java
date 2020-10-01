
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 
 * @author pratik
 * @version 2018-01-01
 * 
 *
 */
public class TimeData implements Comparable<TimeData>
{
    private GregorianCalendar measurementDateTimeUTC;
    private String stationID;
    private Measurement tair;
    private Measurement ta9m;
    private Measurement solarRadiation;

    
    public TimeData(String stationID, int year, int month, int day, int minute, Measurement tair, Measurement ta9m,
            Measurement solarRadiation)
    {
        // measurementDateTimeUTC = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        // measurementDateTimeUTC.set(year, month, day, 0, minute);
        setDateTimeComponents(year, month, day, minute);
        this.stationID = stationID;
        this.tair = tair;
        this.ta9m = ta9m;
        this.solarRadiation = solarRadiation;
    }

    /**
     * constructor 2
     * 
     * @param inStationID
     *            passes station id
     * @param dateTime
     *            passes date time
     * @param tair
     *            passes tair
     * @param ta9m
     *            passes ta9m
     * @param solarRadiation
     *            passes solar radiation
     * @throws WrongTimeZoneException
     */
    public TimeData(String inStationID, GregorianCalendar dateTime, Measurement tair, Measurement ta9m,
            Measurement solarRadiation) throws WrongTimeZoneException
    {
        if (dateTime.getTimeZone().getID().equalsIgnoreCase("UTC"))
        {
            this.measurementDateTimeUTC = dateTime;
            this.stationID = inStationID;
            this.tair = tair;
            this.ta9m = ta9m;
            this.solarRadiation = solarRadiation;
        }
        else
        {
            throw new WrongTimeZoneException();
        }
    }

    /**
     * 
     * @return measurementDateTimeUTC returns measurementDateTimeUTC
     */
    public GregorianCalendar getMeasurementDateTime()
    {
        return measurementDateTimeUTC;
    }

    /**
     * setter
     * 
     * @param year
     *            passes year
     * @param month
     *            passes month
     * @param day
     *            passes day
     * @param minute
     *            passes minute
     */
    public void setDateTimeComponents(int year, int month, int day, int minute)
    {
        measurementDateTimeUTC = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        measurementDateTimeUTC.set(year, month, day, 00, minute, 00);
    }

    /**
     * setter
     * 
     * @param tair1
     *            sets tair
     * @param ta9m1
     *            set ta9m
     * @param solarRadiation1
     *            sets solarRadiation
     */
    public void setMeasurements(Measurement tair1, Measurement ta9m1, Measurement solarRadiation1)
    {
        this.tair = tair1;
        this.ta9m = ta9m1;
        this.solarRadiation = solarRadiation1;
    }

    /**
     * returns minute
     * 
     * @return returns minute
     */
    public int getMinute()
    {
        return measurementDateTimeUTC.get(GregorianCalendar.MINUTE);
    }

    /**
     * 
     * @return month returns month
     */
    public int getMonth()
    {
        return measurementDateTimeUTC.get(GregorianCalendar.MONTH);
    }

    /**
     * 
     * @return day returns day
     */
    public int getDay()
    {
        return measurementDateTimeUTC.get(GregorianCalendar.DAY_OF_MONTH);

    }

    /**
     * 
     * @return year returns year
     */

    public int getYear()
    {
        return measurementDateTimeUTC.get(GregorianCalendar.YEAR);
    }

    /**
     * 
     * @return stationID returns station id
     */
    public String getStationID()
    {
        return stationID;
    }

    /**
     * returns tair
     * 
     * @return returns tair
     */
    public Measurement getTair()
    {
        return tair;
    }

    /**
     * returns ta9m
     * 
     * @return returns ta9m
     */
    public Measurement getTa9m()
    {
        return ta9m;
    }

    /**
     * returns solar radiation
     * 
     * @return returns solar radiation
     */
    public Measurement getSolarRadiation()
    {
        return solarRadiation;
    }

    @Override
    public int compareTo(TimeData other)
    {
        int comparisonVal = this.getTair().getValue().compareTo(other.getTair().getValue());
        return comparisonVal;
    }

}
