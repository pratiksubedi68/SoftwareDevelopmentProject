/**
 * 
 * @author Pratik
 * @version 3/31/2018
 */
public abstract class StatisticsAbstract
{
    /**
     * comparator
     * 
     * @param inParamId
     *            id
     * @return returns true or false
     * @throws WrongParameterIdException
     */
    public abstract StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException;

    /**
     * comparator
     * 
     * @param inParamId
     *            id
     * @return returns true or false
     * @throws WrongParameterIdException
     */
    public abstract StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException;

}
