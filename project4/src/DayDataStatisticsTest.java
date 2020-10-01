
//import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Pratik
 * @version 3/31/2018
 */
public class DayDataStatisticsTest
{
    /**
     * constructor test
     */
    @Test
    public void constructorTest()
    {
        Measurement maTair1 = new Measurement(2.0);
        Measurement maTa9m1 = new Measurement(4.0);
        Measurement maSolar1 = new Measurement(6.0);

        TimeData data1 = new TimeData("NRMN", 2018, 01, 01, 0, maTair1, maTa9m1, maSolar1);

        Measurement maTair = new Measurement(8.0);
        Measurement maTa9m = new Measurement(10.0);
        Measurement maSolar = new Measurement(12.0);

        TimeData data2 = new TimeData("NRMN", 2017, 02, 02, 0, maTair, maTa9m, maSolar);

        ArrayList<TimeData> inData = new ArrayList<TimeData>();
        inData.add(data1);
        inData.add(data2);

        GregorianCalendar dateTime = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime.set(2018, 01, 01, 00, 00, 00);
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String time = dateFormat.format(dateTime.getTime());

        DayDataStatistics c = new DayDataStatistics(inData);
        Assert.assertEquals("on station: NRMN, solarRadiationTotal = 18.0 Ta9mTotal = 14.0\n" + 
                " TairTotal = 10.0 Ta9mMin = 4.0\n" + 
                " solarRadiationMin = 6.0 TairMax = \n" + 
                "8.0 Ta9mMax = 10.0 Ta9mAverage = 7.0 tairAverage = 5.0\n" + 
                " solarRadiationAverage = 9.0 SolarRadiationMax = 12.0\n" + 
                " on dateTime = " + time, c.toString());

    }

}
