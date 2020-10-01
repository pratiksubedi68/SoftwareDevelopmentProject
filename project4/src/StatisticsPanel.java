import java.awt.Color;
//import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 
 * @author Pratik
 * @version 5/3/2018
 */
public class StatisticsPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = -5778130703074619169L;
    public final static String MAX_BUTTON = "MAXIMUM";
    public final static String MIN_BUTTON = "MINIMUM";
    private JRadioButton maxButton;
    private JRadioButton minButton;

    private ButtonGroup bg;

    public StatisticsPanel()
    {
        // IMPLEMENTATION
        maxButton = new JRadioButton(MAX_BUTTON);
        minButton = new JRadioButton(MIN_BUTTON);
        bg = new ButtonGroup();
        bg.add(maxButton);
        bg.add(minButton);
        add(maxButton);
        add(minButton);
        setLayout(new GridLayout(2, 1));
        // IMPLEMENT
        setBackground(new Color(153, 204, 210));
        // Default to button
        setBorder(BorderFactory.createTitledBorder("statistics"));
        maxButton.setSelected(true);

    }

    public String getStatisticsType()
    {
        if (maxButton.isSelected() == true && minButton.isSelected() == false)
        {
            return "MAX";
        }
        else
            return "MIN";
    }
}
