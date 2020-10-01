import javax.swing.JLabel;
import javax.swing.JPanel;

public class MesonetMainPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 6224309422787783370L;

    public MesonetMainPanel()
    {
        JLabel greetingLabel = new JLabel("Mesonet Calculator");
        
        // Add greeting to this panel
        add(greetingLabel);
    }
}
