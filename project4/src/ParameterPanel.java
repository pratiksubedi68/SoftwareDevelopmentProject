import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ParameterPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 4236142307230121244L;
    public final String TAIR = "TAIR";
    public final String TA9M = "TA9M";
    public final String SRAD = "SRAD";
    public final String WSPD = "WSPD";
    
    // Check boxes for the available parameters
    private JCheckBox airTemp;
    private JCheckBox ta9m;
    private JCheckBox srad;
    private JCheckBox wspd;
    
    public ParameterPanel()
    {
        System.out.println("Building Parameter panel");

        // Create a GridLayout Manager
        setLayout(new GridLayout(4,1));
	// IMPLEMENT
        setBackground(new Color(153, 204, 210));
        setBorder(BorderFactory.createTitledBorder("param"));
        airTemp = new JCheckBox(TAIR);
        ta9m = new JCheckBox(TA9M);
        srad = new JCheckBox(SRAD);
        wspd = new JCheckBox(WSPD);
        add(airTemp);
        add(ta9m);
        add(srad);
        add(wspd);
    }
    
    public ArrayList<String> getParamIds()
    {
	// create ArrayList<String> to hold selected parameters
        ArrayList<String> parameter = new ArrayList<String> ();
        if (airTemp.isSelected() == true)
        {
            parameter.add(TAIR);           
        }
        if (ta9m.isSelected() == true)
        {
            parameter.add(TA9M);           
        }
        if (srad.isSelected() == true)
        {
            parameter.add(SRAD);           
        }
        if (wspd.isSelected() == true)
        {
            parameter.add(WSPD);           
        }
        return parameter;
    }

}
