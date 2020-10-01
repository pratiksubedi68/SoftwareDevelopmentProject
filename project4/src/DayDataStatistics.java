
//import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
//import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
//import java.util.TimeZone;
import java.util.TimeZone;

/**
 * 
 * @author Pratik
 * @version 3/31/2018
 */
public class DayDataStatistics
{
    /** The set of data. */
    private ArrayList<TimeData> data;

    private HashMap<ParamType, EnumMap<StatType, StatMeasurement>> paramStats;
    private EnumMap<StatType, StatMeasurement> tairMap = new EnumMap<StatType, StatMeasurement>(StatType.class);
    private EnumMap<StatType, StatMeasurement> ta9mMap = new EnumMap<StatType, StatMeasurement>(StatType.class);
    private EnumMap<StatType, StatMeasurement> sradMap = new EnumMap<StatType, StatMeasurement>(StatType.class);

    private String stationId = "nada";

    /**
     * passes data
     * 
     * @param inData
     *            passes data
     */
    public DayDataStatistics(ArrayList<TimeData> inData)
    {
        data = inData;
        stationId = inData.get(0).getStationID();
        paramStats = new HashMap<ParamType, EnumMap<StatType, StatMeasurement>>();
        calculateAirTemperatureStatistics("tair");
        calculateAirTemperatureStatistics("ta9m");
        calculateAirTemperatureStatistics("SRAD");
        paramStats.put(ParamType.TAIR, tairMap);
        paramStats.put(ParamType.TA9M, ta9mMap);
        paramStats.put(ParamType.SRAD, sradMap);

    }

    /**
     * finds stats
     * 
     * @param tairName
     *            finds stats
     */
    private void calculateAirTemperatureStatistics(String tairName)
    {
        // These variables represent the "best so far" for min and max.
        // By setting these these to the largest and smallest possible
        // values, we ensure that the first time a valid Measurement is
        // found, it will replace these values

        // Accumulator and counter for computing average
        if (tairName.equalsIgnoreCase("tair"))
        {
            String inParamId = "TAIR";
            double sum = 0;
            int numberOfValidObservations = 0;
            for (TimeData file : data)
            {
                if (file.getTair().isValid())
                {
                    TimeData maximum = Collections.max(data);
                    tairMap.put(StatType.MAX, new StatMeasurement(maximum.getTair().getValue(),
                            maximum.getMeasurementDateTime(), maximum.getStationID(), inParamId, StatType.MAX));
                    TimeData minimum = Collections.min(data);
                    tairMap.put(StatType.MIN, new StatMeasurement(minimum.getTair().getValue(),
                            minimum.getMeasurementDateTime(), minimum.getStationID(), inParamId, StatType.MIN));
                    sum = sum + file.getTair().getValue();
                    ++numberOfValidObservations;
                    tairMap.put(StatType.AVG, new StatMeasurement(sum / numberOfValidObservations,
                            new GregorianCalendar(0000, 00, 00, 00, 00, 00), stationId, inParamId, StatType.AVG));
                    tairMap.put(StatType.TOT, new StatMeasurement(sum, new GregorianCalendar(0000, 00, 00, 00, 00, 00),
                            stationId, inParamId, StatType.TOT));
                }
            }
        }

        if (tairName.equalsIgnoreCase("ta9m"))
        {
            String inParamId = "TA9M";
            double sum = 0;
            int numberOfValidObservations = 0;
            for (TimeData file : data)
            {
                if (file.getTa9m().isValid())
                {
                    TimeData maximum = Collections.max(data, new Ta9mComparator());
                    ta9mMap.put(StatType.MAX, new StatMeasurement(maximum.getTa9m().getValue(),
                            maximum.getMeasurementDateTime(), maximum.getStationID(), inParamId, StatType.MAX));
                    TimeData minimum = Collections.min(data, new Ta9mComparator());
                    ta9mMap.put(StatType.MIN, new StatMeasurement(minimum.getTa9m().getValue(),
                            minimum.getMeasurementDateTime(), minimum.getStationID(), inParamId, StatType.MIN));
                    sum = sum + file.getTa9m().getValue();
                    ++numberOfValidObservations;
                    ta9mMap.put(StatType.AVG, new StatMeasurement(sum / numberOfValidObservations,
                            new GregorianCalendar(0000, 00, 00, 00, 00, 00), stationId, inParamId, StatType.AVG));
                    ta9mMap.put(StatType.TOT, new StatMeasurement(sum, new GregorianCalendar(0000, 00, 00, 00, 00, 00),
                            stationId, inParamId, StatType.TOT));
                }
            }
        }

        if (tairName.equalsIgnoreCase("SRAD"))
        {
            String inParamId = "SRAD";
            double sum = 0;
            int numberOfValidObservations = 0;
            for (TimeData file : data)
            {
                if (file.getSolarRadiation().isValid())
                {
                    TimeData maximum = Collections.max(data, new SradComparator());
                    sradMap.put(StatType.MAX, new StatMeasurement(maximum.getSolarRadiation().getValue(),
                            maximum.getMeasurementDateTime(), maximum.getStationID(), inParamId, StatType.MAX));
                    TimeData minimum = Collections.min(data, new SradComparator());
                    sradMap.put(StatType.MIN, new StatMeasurement(minimum.getSolarRadiation().getValue(),
                            minimum.getMeasurementDateTime(), maximum.getStationID(), inParamId, StatType.MIN));
                    sum = sum + file.getSolarRadiation().getValue();
                    ++numberOfValidObservations;
                    sradMap.put(StatType.AVG, new StatMeasurement(sum / numberOfValidObservations,
                            new GregorianCalendar(0000, 00, 00, 00, 00, 00), stationId, inParamId, StatType.AVG));
                    sradMap.put(StatType.TOT, new StatMeasurement(sum, new GregorianCalendar(0000, 00, 00, 00, 00, 00),
                            stationId, inParamId, StatType.TOT));
                }
            }
        }

    }

    /**
     * @return average of solar radiation
     */
    public StatMeasurement getSolarRadiationAverage()
    {
        return paramStats.get(ParamType.SRAD).get(StatType.AVG);
    }

    /**
     * @return maximum value of solar radiation
     */
    public StatMeasurement getSolarRadiationMax()
    {
        return paramStats.get(ParamType.SRAD).get(StatType.MAX);
    }

    /**
     * @return minimum value of solar radiation
     */
    public StatMeasurement getSolarRadiationMin()
    {
        return paramStats.get(ParamType.SRAD).get(StatType.MIN);
    }

    /**
     * @return total value of solar radiation
     */
    public StatMeasurement getSolarRadiationTotal()
    {
        return paramStats.get(ParamType.SRAD).get(StatType.TOT);
    }

    /**
     * @return station ID
     */
    public String getStationID()
    {
        return stationId;
    }

    /**
     * @return average value of air temperature at 9m
     */
    public StatMeasurement getTa9mAverage()
    {
        return paramStats.get(ParamType.TA9M).get(StatType.AVG);
    }

    /**
     * @return maximum value of air temperature at 9m
     */
    public StatMeasurement getTa9mMax()
    {
        return paramStats.get(ParamType.TA9M).get(StatType.MAX);
    }

    /**
     * @return minimum value of air temperature at 9m
     */
    public StatMeasurement getTa9mMin()
    {
        return paramStats.get(ParamType.TA9M).get(StatType.MIN);
    }

    /**
     * @return minimum value of air temperature at 9m
     */
    public StatMeasurement getTa9mTotal()
    {
        return paramStats.get(ParamType.TA9M).get(StatType.TOT);
    }

    /**
     * @return average value of air temperature at 9m
     */
    public StatMeasurement getTairAverage()
    {
        return paramStats.get(ParamType.TAIR).get(StatType.AVG);
    }

    /**
     * @return maximum value of air temperature
     */
    public StatMeasurement getTairMax()
    {
        return paramStats.get(ParamType.TAIR).get(StatType.MAX);
    }

    /**
     * @return minimum value of air temperature
     */
    public StatMeasurement getTairMin()
    {
        return paramStats.get(ParamType.TAIR).get(StatType.MIN);
    }

    /**
     * @return average value of air temperature at 9m
     */
    public StatMeasurement getTairTotal()
    {
        return paramStats.get(ParamType.TAIR).get(StatType.TOT);
    }

    /**
     * Describe DayStatistics
     * 
     * @return A string describing the statistics for the day
     */
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String time = dateFormat.format(this.getTairMin().getDateTimeOfMeasurment().getTime());

        return "on station: " + this.getStationID() + ", solarRadiationTotal = "
                + this.getSolarRadiationTotal().getValue() + " Ta9mTotal = " + this.getTa9mTotal().getValue() + "\n"
                + " TairTotal = " + this.getTairTotal().getValue() + " Ta9mMin = " + this.getTa9mMin().getValue() + "\n"
                + " solarRadiationMin = " + this.getSolarRadiationMin().getValue() + " TairMax = " + "\n"
                + this.getTairMax().getValue() + " Ta9mMax = " + this.getTa9mMax().getValue() + " Ta9mAverage = "
                + this.getTa9mAverage().getValue() + " tairAverage = " + this.getTairAverage().getValue() + "\n"
                + " solarRadiationAverage = " + this.getSolarRadiationAverage().getValue() + " SolarRadiationMax = "
                + this.getSolarRadiationMax().getValue() + "\n" + " on dateTime = " + time;
    }
}
