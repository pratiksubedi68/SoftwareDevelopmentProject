import java.util.Comparator;

/**
 * 
 * @author Pratik
 * @version 3/31/2018
 */
public class SradComparator implements Comparator<TimeData>
{

    /**
     * @param a
     *            passes timeData type
     * @param b
     *            passes timeData type
     * @return -1 or 1 or 0
     */
    @Override
    public int compare(TimeData a, TimeData b)
    {
        int comparisonVal = a.getSolarRadiation().getValue().compareTo(b.getSolarRadiation().getValue());
        return comparisonVal;
    }

}
