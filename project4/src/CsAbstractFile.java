import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.util.Date;

/**
 * 
 * @author Pratik
 * @version 3/29/2018
 */
public abstract class CsAbstractFile implements TimeComparable
{
    /**
     * file name
     */
    protected File file;
    /**
     * date format
     */
    protected DateFormat dateFormat;
    /**
     * time format used for strings
     */
    protected static String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss z";
    /**
     * file name
     */
    protected String fileName;

    /**
     * file to be pass
     * 
     * @param inFileName
     *            passes the file name
     */
    protected CsAbstractFile(String inFileName)
    {
        this.fileName = inFileName;
    }

    /**
     * returns true if file exists else false
     * 
     * @return true or false
     */
    public boolean exists()
    {
        file = new File(fileName);

        return file.exists();

    }

    /**
     * returns files last date of modified
     * 
     * @return date
     */
    public long getDateModified()
    {
        return file.lastModified();
    }

    /**
     * returns as formated
     * 
     * @return returns file name
     */
    @Override
    public String toString()
    {
        return fileName + "exists " + "= " + exists();
    }

    /**
     * returns 1 if true and 1 if false -1
     * 
     * @param inDateTimeStr
     *            user date
     * @return returns -1 or 1
     * @throws ParseException
     */
    public abstract int compareWithTimeString(String inDateTimeStr) throws ParseException;

}
