/**
 * 
 * @author pratik
 * @version 3/31/2018
 */
public class WrongTimeZoneException extends Exception
{
    /**
     * id
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     */
    public WrongTimeZoneException()
    {
        super("Invalid time zone detected, should be UTC");
        // default implementation ignored
    }
}
