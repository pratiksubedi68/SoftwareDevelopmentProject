/**
 * 
 * @author pratik
 * @version 3/31/2018
 */
public class WrongParameterIdException extends Exception
{
    /**
     * id
     */
    private static final long serialVersionUID = 7394973112258653626L;

    /**
     * constructor
     */
    public WrongParameterIdException()
    {
        super("Invalid parameterID detected");
    }

    /**
     * constructor
     * 
     * @param msg
     *            massage to show
     */
    public WrongParameterIdException(String msg)
    {
        super(msg + " Invalid parameterID detected");
    }
}