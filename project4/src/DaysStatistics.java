import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.GregorianCalendar;
//import java.util.TimeZone;

/**
 * @author Pratik
 * @version 3/31/2018
 */
public class DaysStatistics extends StatisticsAbstract
{
    private ArrayList<String> files;

    private ArrayList<StatMeasurement> tairMinStats;
    private ArrayList<StatMeasurement> tairAvgStats;
    private ArrayList<StatMeasurement> tairMaxStats;

    private ArrayList<StatMeasurement> ta9mMinStats;
    private ArrayList<StatMeasurement> ta9mAvgStats;
    private ArrayList<StatMeasurement> ta9mMaxStats;

    private ArrayList<StatMeasurement> sradMinStats;
    private ArrayList<StatMeasurement> sradAvgStats;
    private ArrayList<StatMeasurement> sradMaxStats;
    private ArrayList<StatMeasurement> sradTotalStats;

    /**
     * calculates the stat
     * 
     * @param files
     *            passes file
     */
    public DaysStatistics(String[] files)
    {
        this.files = new ArrayList<String>(Arrays.asList(files));

        tairMinStats = new ArrayList<>();
        tairAvgStats = new ArrayList<>();
        tairMaxStats = new ArrayList<>();

        ta9mMinStats = new ArrayList<>();
        ta9mAvgStats = new ArrayList<>();
        ta9mMaxStats = new ArrayList<>();

        sradMinStats = new ArrayList<>();
        sradAvgStats = new ArrayList<>();
        sradMaxStats = new ArrayList<>();
        sradTotalStats = new ArrayList<>();

    }

    /**
     * @param files
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws ParseException
     */
    public void findStatistics() throws IOException, WrongCopyrightException, ParseException
    {
        for (String fileName : files)
        {
            MesonetTimeFile mtsFile = new MesonetTimeFile(fileName);
            mtsFile.parseFile();
            ArrayList<TimeData> data = mtsFile.parseFile();
            DayDataStatistics dataStats = new DayDataStatistics(data);

            assignStats(dataStats);
        }
    }

    /**
     * @param dataStats
     * @throws ParseException
     */
    private void assignStats(DayDataStatistics dataStats) throws ParseException
    {
        tairMinStats.add(dataStats.getTairMin());
        tairAvgStats.add(dataStats.getTairAverage());
        tairMaxStats.add(dataStats.getTairMax());

        ta9mMinStats.add(dataStats.getTa9mMin());
        ta9mAvgStats.add(dataStats.getTa9mAverage());
        ta9mMaxStats.add(dataStats.getTa9mMax());

        sradMinStats.add(dataStats.getSolarRadiationMin());
        sradAvgStats.add(dataStats.getSolarRadiationAverage());
        sradMaxStats.add(dataStats.getSolarRadiationMax());
        sradTotalStats.add(dataStats.getSolarRadiationTotal());

    }

    /*
     * (non-Javadoc)
     * 
     * @see StatisticsAbstract#getMinimumDay(java.lang.String)
     */
    @Override
    public StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException
    {
        if (inParamId.equalsIgnoreCase("tair"))
        {
            return Collections.min(tairMinStats);
        }
        if (inParamId.equalsIgnoreCase("ta9m"))
        {
            return Collections.min(ta9mMinStats);
        }
        if (inParamId.equalsIgnoreCase("srad"))
        {
            return Collections.min(sradMinStats);
        }
        else
        {
            throw new WrongParameterIdException();

        }
    }

    @Override
    public StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException
    {
        if (inParamId.equalsIgnoreCase("tair"))
        {
            return Collections.min(tairMaxStats);
        }
        if (inParamId.equalsIgnoreCase("ta9m"))
        {
            return Collections.min(ta9mMaxStats);
        }
        if (inParamId.equalsIgnoreCase("srad"))
        {
            return Collections.min(sradMaxStats);
        }
        else
        {
            throw new WrongParameterIdException();
        }
    }

    /**
     * passes parameter id and calculates
     * 
     * @param paramId
     *            station id
     * @return returns as formated
     * @throws WrongParameterIdException
     */
    public String combineMinMaxStatistics(String paramId) throws WrongParameterIdException
    {
        StatMeasurement maximumDay = getMaximumDay(paramId);
        StatMeasurement miniumuDay = getMinimumDay(paramId);
        return maximumDay.toString() + "\n" + miniumuDay.toString() + "\n";
    }

    /**
     * @return returns as formated
     */
    @Override
    public String toString()
    {
        String result = String.format("%10s%10s%10s%10s%10s%10s\n", "ID", "STAD", "VALUE", "STID", "DATE T TIME", "TZ");
        result += "---------------------------------------------------------------\n";
        try
        {
            result += combineMinMaxStatistics("tair");
            result += combineMinMaxStatistics("ta9M");
            result += combineMinMaxStatistics("srad");
        }

        catch (WrongParameterIdException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
