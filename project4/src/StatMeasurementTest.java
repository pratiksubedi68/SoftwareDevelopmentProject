
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author pratik
 * @version 2/15/2018 Project 1 to hold a single measurement and flag it if is
 *          bad
 */
public class StatMeasurementTest
{
    /**
     * constructor test
     */
    @Test
    public void emptyConstructorTest()
    {

        StatMeasurement a = new StatMeasurement();
        Assert.assertEquals(Double.NaN, a.getValue(), 0.01);
        Assert.assertEquals(false, a.isValid());

    }

    /**
     * constructor test
     */
    @Test
    public void constructorTest()
    {
        GregorianCalendar dateTime = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime.set(2010, 10, 10, 0, 0, 0);

        StatMeasurement a = new StatMeasurement(-14.5, dateTime, "NRMN", "TAIR", StatType.MAX);
        a.setParamId("apple");
        a.setStatType(StatType.MIN);

        Assert.assertEquals(-14.5, a.getValue(), 0.01);
        Assert.assertEquals(dateTime, a.getDateTimeOfMeasurment());
        Assert.assertEquals(StatType.MIN, a.getStatType());
        Assert.assertEquals("apple", a.getParamId());

    }

    /**
     * constructor test
     * 
     * @throws ParseException
     */
    @Test
    public void isLessThanTest() throws ParseException
    {
        GregorianCalendar dateTime1 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime1.set(2012, 10, 10, 0, 0, 0);
        GregorianCalendar dateTime2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime2.set(2010, 10, 10, 0, 0, 0);
        GregorianCalendar dateTime3 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime3.set(2012, 10, 10, 0, 0, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        String dateString1 = dateFormat.format(dateTime1.getTime());
        String dateString2 = dateFormat.format(dateTime2.getTime());
        String dateString3 = dateFormat.format(dateTime3.getTime());

        StatMeasurement a = new StatMeasurement(-15.5, dateTime1, "NRMN", "TAIR", StatType.MAX);
        StatMeasurement b = new StatMeasurement(-14.5, dateTime2, "NRMN", "TAIR", StatType.MAX);
        StatMeasurement c = new StatMeasurement(-15.5, dateTime1, "NRMN", "TAIR", StatType.MAX);
        StatMeasurement d = new StatMeasurement(-10000.5, dateTime1, "NRMN", "TAIR", StatType.MAX);
        StatMeasurement e = new StatMeasurement(-11111.5, dateTime1, "NRMN", "TAIR", StatType.MAX);

        Assert.assertEquals(true, a.isLessThan(b));
        Assert.assertEquals(false, b.isLessThan(a));
        Assert.assertEquals(true, a.isLessThan(c));
        Assert.assertEquals(false, b.isLessThan(a));
        Assert.assertEquals(true, b.isLessThan(d));
        Assert.assertEquals(true, d.isLessThan(e));
        Assert.assertEquals(false, d.isLessThan(a));

        Assert.assertEquals(false, a.isGreaterThan(b));
        Assert.assertEquals(true, b.isGreaterThan(a));
        Assert.assertEquals(true, a.isGreaterThan(c));
        Assert.assertEquals(true, b.isGreaterThan(a));
        Assert.assertEquals(true, b.isGreaterThan(d));
        Assert.assertEquals(true, d.isGreaterThan(e));
        Assert.assertEquals(false, d.isGreaterThan(a));

        Assert.assertEquals(true, a.newerThan(dateString2));
        Assert.assertEquals(false, b.newerThan(dateString1));
        Assert.assertEquals(false, b.newerThan(dateString3));

        Assert.assertEquals(false, a.olderThan(dateString2));
        Assert.assertEquals(true, b.olderThan(dateString1));
        Assert.assertEquals(true, b.olderThan(dateString3));

        Assert.assertEquals(-1, a.compareWithTimeString(dateString2));
        Assert.assertEquals(1, b.compareWithTimeString(dateString1));
        Assert.assertEquals(1, b.compareWithTimeString(dateString3));

        Assert.assertEquals("      TAIR        MAX     -15.50  NRMN 2012-11-10T00:00:00 UTC", a.toString());

    }

}
