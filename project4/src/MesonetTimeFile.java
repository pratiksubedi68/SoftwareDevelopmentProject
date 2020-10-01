import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Pratik
 * @version 3/29/2018
 */
public class MesonetTimeFile extends CsFile
{
    private ArrayList<TimeData> data = new ArrayList<TimeData>();
    private ArrayList<String> paramIds = new ArrayList<String>();
    private HeaderDateTime headerDateTime;

    private static final int YEAR = 1;
    private static final int MONTH = 2;
    private static final int DAY = 3;
    private static final int HOUR = 4;
    private static final int MINUTE = 5;

    private int tairPosition = 4;
    private int ta9mPosition = 14;
    private int sradPosition = 13;
    private int minutePosition = 2;
    private int stidPosition = 0;

    private GregorianCalendar dateTime;

    /**
     * 
     * @author ganga
     * @version 3/29/2018
     */
    class HeaderDateTime
    {
        /**
         * @param year
         *            year
         */
        public int year;
        /**
         * @param month
         *            month
         */
        public int month;
        /**
         * @param day
         *            day
         */
        public int day;
        /**
         * @param minute
         *            minute
         */
        public int minute;

        /**
         * constructs the date time
         * 
         * @param inYear
         *            passes year
         * @param inMonth
         *            passes month
         * @param inDay
         *            passes day
         * @param inMinute
         *            passes minute
         */
        HeaderDateTime(int inYear, int inMonth, int inDay, int inMinute)
        {
            year = inYear;
            month = inMonth;
            day = inDay;
            minute = inMinute;
        }
    }

    /**
     * constructor to pass file name
     * 
     * @param inFileName
     *            passes file name
     * @throws IOException
     * @throws WrongCopyrightException
     */
    MesonetTimeFile(String inFileName) throws IOException, WrongCopyrightException
    {
        super(inFileName);

        parseFile();

    }

    /**
     * 
     * @return array of timeDataType
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws NumberFormatException
     */
    public ArrayList<TimeData> parseFile() throws IOException, WrongCopyrightException, NumberFormatException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try
        {

            // Reads the copy right header
            String strg1 = br.readLine();
            // reads the header time
            String strg2 = br.readLine();
            // reads the parameter header
            String strg3 = br.readLine();
            // starts reading data
            String strg4 = br.readLine();

            copyrightIsCorrect(strg1);
            parseDateTimeHeader(strg2);
            parseParamHeader(strg3);

            while (strg4 != null)
            {
                parseData(strg4);
                strg4 = br.readLine();
            }

        }
        catch (IOException e)
        {
            System.out.println("Error reading from file!\n");
            e.printStackTrace();
        }
        catch (WrongCopyrightException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

        finally
        {
            br.close();
        }
        return data;
    }

    /**
     * passes the line of data and parse it
     * 
     * @param line
     *            passes the line of data
     */
    private void parseData(String line)
    {
        if (line != null)
        {
            String[] input = line.trim().split("\\s+");

            TimeData values = new TimeData(input[stidPosition], headerDateTime.year, headerDateTime.month,
                    headerDateTime.day, Integer.parseInt(input[minutePosition]),
                    new Measurement(Double.parseDouble(input[tairPosition])),
                    new Measurement(Double.parseDouble(input[ta9mPosition])),
                    new Measurement(Double.parseDouble(input[sradPosition])));
            data.add(values);

        }
    }

    /**
     * passes the line of data and parse it
     * 
     * @param inParamStr
     *            passes the line of data and parse it
     */
    private void parseParamHeader(String inParamStr)
    {
        if (inParamStr != null)
        {
            String[] input = inParamStr.trim().split("\\s+");

            paramIds = new ArrayList<String>();
            paramIds.add(input[tairPosition]);
            paramIds.add(input[ta9mPosition]);
            paramIds.add(input[sradPosition]);
            paramIds.add(input[minutePosition]);
            paramIds.add(input[stidPosition]);
        }

    }

    /**
     * if fist word in the copy right header is 101 then it passes else it throws
     * 
     * @param inCopyrightStr
     *            passes the copy right header
     * @throws WrongCopyrightException
     */
    public void copyrightIsCorrect(String inCopyrightStr) throws WrongCopyrightException
    {
        if (inCopyrightStr != null)
        {
            String[] input = inCopyrightStr.trim().split("\\s+");
            if (Integer.parseInt(input[0]) != 101)
            {
                throw new WrongCopyrightException();
            }

        }

    }

    /**
     * passes the header time data and parse it
     * 
     * @param inHeader
     *            passes the header time data
     */
    void parseDateTimeHeader(String inHeader)
    {
        if (inHeader != null)
        {
            String[] input = inHeader.trim().split("\\s+");

            int year = Integer.parseInt(input[YEAR]);
            int month = Integer.parseInt(input[MONTH]);
            int day = Integer.parseInt(input[DAY]);
            int hour = Integer.parseInt(input[HOUR]);
            int minute = Integer.parseInt(input[MINUTE]);
            dateTime = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            dateTime.set(year, month, day, hour, minute, 00);

            headerDateTime = new HeaderDateTime(year, month, day, minute);

        }
    }

    /**
     * @return return as formated
     */
    String getStarDateTimeStringFromFile()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(dateTime.getTime());
    }

    /**
     * @return returns as formated
     */
    String getDateTimeString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String msg = dateFormat.format(dateTime.getTime());
        msg += " File " + fileName + " does not exist!!!";

        return msg;
    }

}
