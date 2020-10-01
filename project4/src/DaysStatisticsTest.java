import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Pratik
 * @version 3/31/2018
 */
public class DaysStatisticsTest
{
    private String[] files = { "data/mesonet/20180102stil.mts", "data/mesonet/20180102okcn.mts",
            "data/mesonet/20180102okce.mts" };

    /**
     * manual test because UTC time changes as we run at different times so to
     * calculate initial condition i haven't call findStatistics() method to
     * calculate actual value
     * 
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws IOException, WrongCopyrightException, ParseException
    {
        DaysStatistics a = new DaysStatistics(files);
        a.findStatistics();

        Assert.assertEquals("        ID      STAD     VALUE      STIDDATE T TIME        TZ\n" + 
                "---------------------------------------------------------------\n" + 
                "      TAIR        MAX      -6.40  OKCN 2018-02-02T21:35:00 UTC\n" + 
                "      TAIR        MIN     -13.30  OKCE 2018-02-02T08:05:00 UTC\n" + 
                "      TA9M        MAX      -6.60  OKCN 2018-02-02T21:05:00 UTC\n" + 
                "      TA9M        MIN     -12.40  OKCE 2018-02-02T08:20:00 UTC\n" + 
                "      SRAD        MAX     252.00  OKCN 2018-02-02T18:45:00 UTC\n" + 
                "      SRAD        MIN       0.00  STIL 2018-02-02T00:00:00 UTC\n" + 
                "", a.toString());

    }

}
