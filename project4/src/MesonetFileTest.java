
//import java.io.IOException;

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Pratik
 * @version 3/30/2018
 */
public class MesonetFileTest
{
    /**
     * tests the file
     * 
     * @throws ParseException
     * @throws IOException
     * @throws WrongCopyrightException
     */
    @Test
    public void constructorTest() throws ParseException, IOException, WrongCopyrightException
    {
        MesonetTimeFile a = new MesonetTimeFile("data/mesonet/20180102okcn.mts");

        Assert.assertEquals("data/mesonet/20180102okcn.mts", a.getFileName());
        Assert.assertEquals(true, a.exists());
        // Assert.assertEquals(1522476978007L, a.getDateModified());
        Assert.assertEquals("data/mesonet/20180102okcn.mtsexists = true", a.toString());

        GregorianCalendar dateTime1 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime1.set(2012, 10, 10, 0, 0, 0);
        GregorianCalendar dateTime2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        dateTime2.set(2018, 10, 10, 0, 0, 0);

        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        String dateString1 = dateFormat.format(dateTime1.getTime());
        String dateString2 = dateFormat.format(dateTime2.getTime());

        Assert.assertEquals(true, a.newerThan(dateString1));
        Assert.assertEquals(false, a.newerThan(dateString2));

        Assert.assertEquals(false, a.olderThan(dateString1));
        Assert.assertEquals(true, a.olderThan(dateString2));

        Assert.assertEquals(1, a.compareWithTimeString(dateString1));
        Assert.assertEquals(-1, a.compareWithTimeString(dateString2));

        Assert.assertEquals("2018-02-02T00:00:00 UTC", a.getStarDateTimeStringFromFile());
        Assert.assertEquals("2018-02-02T00:00:00 UTC File data/mesonet/20180102okcn.mts does not exist!!!",
                a.getDateTimeString());

    }

    /**
     * tests the file
     * 
     * @throws IOException
     * 
     *
     */
    @Test
    public void copyrightExceptionTest() throws IOException
    {
        try
        {
            MesonetTimeFile a = new MesonetTimeFile("data/mesonet/20180102okcn.mts");
            a.copyrightIsCorrect("102 @c");
        }
        catch (WrongCopyrightException c)
        {
            Assert.assertEquals("Invalid copyright detected", c.getMessage());
        }
    }
}
