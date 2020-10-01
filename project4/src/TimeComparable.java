import java.text.ParseException;

/**
 * compares two dates
 * 
 * @author Pratik
 * @version 3/29/2012
 */
public interface TimeComparable
{
    /**
     * returns true if true else false
     * 
     * @param inDateTimeStr
     *            user time
     * @return true or false
     * @throws ParseException
     */
    boolean newerThan(String inDateTimeStr) throws ParseException;

    /**
     * returns true if false else true
     * 
     * @param inDateTimeStr
     *            user time
     * @return true or false
     * @throws ParseException
     */
    boolean olderThan(String inDateTimeStr) throws ParseException;
}
