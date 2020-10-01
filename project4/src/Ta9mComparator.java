import java.util.Comparator;

/**
 * 
 * @author Pratik
 * @version 3/31/2018
 */
public class Ta9mComparator implements Comparator<TimeData>
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
        int comparisonVal = a.getTa9m().getValue().compareTo(b.getTa9m().getValue());
        return comparisonVal;
    }
}
