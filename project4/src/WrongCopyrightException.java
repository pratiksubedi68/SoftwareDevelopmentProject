/**
 * 
 * @author pratik
 * @version 3/30/2018
 */
public class WrongCopyrightException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = -3352808845495117276L;

    /**
     * constructor
     */
    public WrongCopyrightException()
    {
        super("Invalid copyright detected");
    }

}
