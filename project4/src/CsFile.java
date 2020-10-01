import java.text.ParseException;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * impliments the abstract class
 * 
 * @author Pratik
 * @version 3/29/2018
 */
public class CsFile extends CsAbstractFile
{
    /**
     * inherited constructor
     * 
     * @param inFileName
     *            passes the file name
     */
    public CsFile(String inFileName)
    {
        super(inFileName);
    }

    /**
     * returns the file name
     * 
     * @return file name
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * returns true if else 1
     * 
     * @param inDateTime
     *            date time
     * @return true or false
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        boolean ret = true;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
            String dateString1 = dateFormat.format(file.lastModified());
            Date date1 = dateFormat.parse(dateString1);
            Date date2 = dateFormat.parse(inDateTime);

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
        return ret;
    }

    /**
     * returns true if else -1
     * 
     * @param inDateTime
     *            date time
     * @return true or false
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        boolean ret = true;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
            String dateString1 = dateFormat.format(file.lastModified());
            Date date1 = dateFormat.parse(dateString1);
            Date date2 = dateFormat.parse(inDateTime);

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
     * returns 1 if new -1 if false and 0 if equal
     * 
     * @param inDateTime
     *            date time
     * @return -1,0,1
     */
    @Override
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        int ret = 0;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CsAbstractFile.dateTimeFormat);
            String dateString1 = dateFormat.format(file.lastModified());
            Date date1 = dateFormat.parse(dateString1);
            Date date2 = dateFormat.parse(inDateTime);

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
}