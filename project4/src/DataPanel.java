import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
//import javax.swing.ButtonGroup;
//import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.JTextArea;

//import javax.swing.JTextField;
//import javax.swing.border.Border;
/**
 * 
 * @author Pratik
 * @version 5/3/2018
 *
 */

public class DataPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 5777740971062336138L;

    private JTextArea resultDescription;

    public DataPanel()
    {
        // IMPLEMENTATION
        final int COLUMN_FIELD_WIDTH = 40;
        System.out.println("Building Data panel");
        // Layout manager initialization
        this.setLayout(new GridBagLayout());

        // Create the variable description text area. We used 6 rows
        resultDescription = new JTextArea(6, COLUMN_FIELD_WIDTH);
        resultDescription.setWrapStyleWord(true);
        resultDescription.setLineWrap(true);
        resultDescription.setEditable(false);

        // Lay out all of the pieces
        GridBagConstraints layoutConst = new GridBagConstraints();

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        layoutConst.insets = new Insets(10, 10, 10, 10);
        add(resultDescription, layoutConst);

        this.setBackground(new Color(0, 128, 210));
        setBorder(BorderFactory.createTitledBorder("Output"));
    }

    public synchronized void updateData(String result)
    {
        resultDescription.setText(result);
    }

}
