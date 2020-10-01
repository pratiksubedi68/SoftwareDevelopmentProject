
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Pratik 
 * @version 3/29/2018
 *
 */
public class StatMeasurement extends Measurement implements TimeComparable, Comparable<StatMeasurement>
{

    private GregorianCalendar dateTimeOfMeasurment;
    private String paramId;
    private StatType statType;
    private String stationId;
    // private static final String NADA = "nada";

    /**
     * constructor
     */
    public StatMeasurement()
    {
        // empty const
    }

    /**
     * constructor to hold the measurement statics
     * 
     * @param inValue
     *            passes value
     * @param obsDateTime
     *            passes observation time
     * @param inStationId
     *            passes station id
     * @param inParamId
     *            passes parameter id
     * @param inStatType
     *            passes enum type
     */
    public StatMeasurement(Double inValue, GregorianCalendar obsDateTime, String inStationId, String inParamId,
            StatType inStatType)
    {
        super(inValue);
        this.dateTimeOfMeasurment = obsDateTime;
        this.paramId = inParamId;
        this.statType = inStatType;
        this.stationId = inStationId;
    }

    /**
     * ateTimeOfMeasurment
     * 
     * @return ateTimeOfMeasurment
     */
    public GregorianCalendar getDateTimeOfMeasurment()
    {
        return dateTimeOfMeasurment;
    }

    /**
     * sets the parameter id
     * 
     * @param inParamId
     *            sets the parameter id
     */
    public void setParamId(String inParamId)
    {
        this.paramId = inParamId;
    }

    /**
     * returns paramId
     * 
     * @return paramId returns paramId
     */
    public String getParamId()
    {
        return paramId;
    }

    /**
     * sets the parameter type
     * 
     * @param type
     *            sets the parameter type
     */
    public void setStatType(StatType type)
    {
        this.statType = type;
    }

    /**
     * returns the stat type
     * 
     * @return statType returns the stat type
     */
    public StatType getStatType()
    {
        return statType;
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly smaller than
     *         s OR if this is valid and s is not valid
     */
    public boolean isLessThan(StatMeasurement compareWith)
    {
        boolean ret = true;
        if ((this.getValue() < compareWith.getValue()) && ((this.getValue() > -900) && (compareWith.getValue() > -900)))
        {
            ret = true;
        }
        if ((this.getValue() == compareWith.getValue()) && (this.getValue() > -900) && (compareWith.getValue() > -900))
        {
            ret = false;
        }
        if ((this.getValue() > compareWith.getValue()) && ((this.getValue() > -900) && (compareWith.getValue() > -900)))
        {
            ret = false;
        }
        if ((this.getValue() > -900) && (compareWith.getValue() < -900))
        {
            ret = true;
        }
        if ((this.getValue() < -900) && (compareWith.getValue() > -900))
        {
            ret = false;
        }
        if ((this.getValue() < -900) && (compareWith.getValue() < -900))
        {
            ret = true;
        }
        return ret;
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly larger than
     *         s OR if this is valid and s is not valid
     */
    public boolean isGreaterThan(StatMeasurement compareWith)
    {

        boolean ret = true;
        if ((this.getValue() > compareWith.getValue()) && ((this.getValue() > -900) && (compareWith.getValue() > -900)))
        {
            ret = true;
        }
        if ((this.getValue() == compareWith.getValue()) && (this.getValue() > -900) && (compareWith.getValue() > -900))
        {
            ret = false;
        }
        if ((this.getValue() < compareWith.getValue()) && ((this.getValue() > -900) && (compareWith.getValue() > -900)))
        {
            ret = false;
        }
        if ((this.getValue() > -900) && (compareWith.getValue() < -900))
        {
            ret = true;
        }
        if ((this.getValue() < -900) && (compareWith.getValue() > -900))
        {
            ret = false;
        }
        if ((this.getValue() < -900) && (compareWith.getValue() < -900))
        {
            ret = true;
        }
        return ret;
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param inDateTime
     *            date to compare with
     * @return true if both Measurements are valid AND this is strictly larger than
     *         s OR if this is valid and s is not valid
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        boolean ret = false;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
            Date date1 = dateFormat.parse(inDateTime);
            String dateString = dateFormat.format(this.dateTimeOfMeasurment.getTime());
            Date date2 = dateFormat.parse(dateString);
            if (date1.compareTo(date2) < 0)
            {
                ret = true;
            }
            else
            {
                ret = false;
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        {
            return ret;
        }
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param inDateTime
     *            date to compare with
     * 
     * @return true if both Measurements are valid AND this is strictly larger than
     *         s OR if this is valid and s is not valid
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        boolean ret = false;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
            Date date1 = dateFormat.parse(inDateTime);
            String dateString = dateFormat.format(this.dateTimeOfMeasurment.getTime());
            Date date2 = dateFormat.parse(dateString);

            if (date1.compareTo(date2) > 0)
            {
                ret = true;
            }
            else
            {
                ret = false;
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        {
            return ret;
        }
    }

    /**
     * @param inDateTime
     *            measure to compare with
     * @return ret
     * @throws ParseException
     */
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        int ret = 0;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
            Date date1 = dateFormat.parse(inDateTime);
            String dateString = dateFormat.format(this.dateTimeOfMeasurment.getTime());
            Date date2 = dateFormat.parse(dateString);
            ret = date1.compareTo(date2);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        {
            return ret;
        }
    }

    /**
     * returns string as formated in pdf
     * 
     * @return dd
     */
    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateStr = dateFormat.format(this.dateTimeOfMeasurment.getTime());
        StringBuilder buffer = new StringBuilder();
        Formatter format = new Formatter(buffer);
        format.format("%10s %10s %10.2f %5s", paramId.toUpperCase(), statType.toString(), value,
                stationId.toUpperCase());
        buffer.append(" " + dateStr);
        format.close();
        return buffer.toString();
    }

    @Override
    public int compareTo(StatMeasurement othersValue)
    {
        int comparisonVal = this.getValue().compareTo(othersValue.getValue());
        return comparisonVal;
    }

}
