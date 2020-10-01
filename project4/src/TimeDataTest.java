
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pratik
 * @version 2/15/2018 Project 1 to hold a single measurement and flag it if is
 *          bad
 */
public class TimeDataTest
{
    /**
     * constructor test
     */
    @Test
    public void imeDataConstructor1Test()
    {
        Measurement maTair = new Measurement(4.4);
        Measurement maTa9m = new Measurement(5.6);
        Measurement maSolar = new Measurement(6.8);

        TimeData a = new TimeData("NRMN", 2018, 01, 01, 0, maTair, maTa9m, maSolar);
        // GregorianCalendar dateTime1 = new
        // GregorianCalendar(TimeZone.getTimeZone("UTC"));
        // dateTime1.set(2018, 01, 01, 0, 0, 0);

        Assert.assertEquals(4.4, a.getTair().getValue(), 0.01);
        Assert.assertEquals(5.6, a.getTa9m().getValue(), 0.01);
        Assert.assertEquals(6.8, a.getSolarRadiation().getValue(), 0.01);
        Assert.assertEquals(2018, a.getYear());
        Assert.assertEquals(01, a.getMonth());
        Assert.assertEquals(01, a.getDay());
        Assert.assertEquals(0, a.getMinute());
        Assert.assertEquals("NRMN", a.getStationID());

    }

    /**
     * constructor test
     * 
     * @throws WrongTimeZoneException
     */
    @Test
    public void timeDataConstructor2Test() throws WrongTimeZoneException
    {

        Measurement maTair = new Measurement(4.4);
        Measurement maTa9m = new Measurement(5.6);
        Measurement maSolar = new Measurement(6.8);

        GregorianCalendar dateTime1 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime1.set(2018, 01, 01, 0, 0, 0);

        TimeData a = new TimeData("NRMN", dateTime1, maTair, maTa9m, maSolar);
        Assert.assertEquals(dateTime1, a.getMeasurementDateTime());

        a.setMeasurements(maTair, maTa9m, maSolar);
        Assert.assertEquals(4.4, a.getTair().getValue(), 0.01);
        Assert.assertEquals(5.6, a.getTa9m().getValue(), 0.01);
        Assert.assertEquals(6.8, a.getSolarRadiation().getValue(), 0.01);
    }

    /**
     * constructor test
     * 
     */
    @Test
    public void timeDataConstructorTest()
    {
        try
        {
            Measurement maTair = new Measurement(4.4);
            Measurement maTa9m = new Measurement(5.6);
            Measurement maSolar = new Measurement(6.8);

            GregorianCalendar dateTime1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            dateTime1.set(2018, 01, 01, 0, 0, 0);

            new TimeData("NRMN", dateTime1, maTair, maTa9m, maSolar);
        }

        catch (WrongTimeZoneException t)
        {
            Assert.assertEquals("Invalid time zone detected, should be UTC", t.getMessage());
        }
    }

}