import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Pratik
	@version 5/3/2018
 */

public class MesonetFrame extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Menu bar */
    private FileMenuBar fileMenuBar;

    private StatisticsPanel statistics;
    private ParameterPanel parameters;
    private DataPanel dataPanel;
    private MesonetMainPanel banner;
    private JPanel buttonPanel;
    private JButton calcButton;
    private JButton exitButton;
    FileMenuBar file;

    public MesonetFrame()
    {
        // IMPLEMENT
        super("Mesonet Calculator");
        // set close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set layout
        statistics = new StatisticsPanel();
        parameters = new ParameterPanel();
        dataPanel = new DataPanel();
        banner = new MesonetMainPanel();
        buttonPanel = new JPanel();
        fileMenuBar = new FileMenuBar();
        fileMenuBar = new FileMenuBar();
        this.setJMenuBar(fileMenuBar);
        this.setLayout(new BorderLayout());
        this.add(statistics, BorderLayout.WEST);
        this.add(parameters, BorderLayout.CENTER);
        this.add(dataPanel, BorderLayout.EAST);
        this.add(banner, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        buildButtonPanel();
        // color(153,204,210)
        // datapanalcolor(0,128,210)
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();

    }

    private void buildButtonPanel()
    {
        //
        // IMPLEMENT
        // Create a panel for buttons.
        buttonPanel = new JPanel();
        calcButton = new JButton("Calculate");
        exitButton = new JButton("Exit");

        // Register the action listeners
        calcButton.addActionListener(new CalcButtonListner());
        exitButton.addActionListener(new ExitButtonListner());
        buttonPanel.add(calcButton);
        buttonPanel.add(exitButton);
        buttonPanel.setBackground(new Color(102, 178, 210));
        ExitButtonListner exit = new ExitButtonListner();
        exitButton.addActionListener(exit);
    }

    private class ExitButtonListner implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);

        }

    }

    private class CalcButtonListner implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // IMPLEMENT

            // help with debugging
            // JOptionPane.showMessageDialog(null,
            // "Should dipslay what is calculated for " + type + " "
            // + paramId + " result: " + result);

            String type = statistics.getStatisticsType();
            System.out.println(">" + type + "<");
            ArrayList<String> paramId = parameters.getParamIds();
            ArrayList<String> fileList = fileMenuBar.getFileList();
            if (fileList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "please select file");
                return;
            }
            if (type.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Select statisitc type");
                return;
            }
            if (paramId.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "slect parameter type");
                return;
            }
            String[] files = fileList.toArray(new String[fileList.size()]);
            DaysStatistics stats = new DaysStatistics(files);
            try
            {
                stats.findStatistics();
                MesonetFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                MesonetFrame.this.setCursor(null);
            }
            catch (IOException | WrongCopyrightException | ParseException e2)
            {
                e2.printStackTrace();
            }
            String result = "";
            try
            {

                for (String id : paramId)
                {
                    if (type == "MAX")
                    {
                        result += stats.getMaximumDay(id).toString() + "\n";
                    }
                    else if (type == "MIN")
                    {
                        result += stats.getMinimumDay(id).toString() + "\n";
                    }
                }
            }
            catch (WrongParameterIdException e1)
            {
                JOptionPane.showMessageDialog(null, e1.toString());
            }
            MesonetFrame.this.dataPanel.updateData(result);
        }
    }

    ///////////////////////////////////////////////////////////////////
    /**
     * 
     * @author CS2334, modified by ???
     * @version 2018-x-x
     * 
     *          Menu bar that provides file loading and program exit capabilities.
     *
     */
    public class FileMenuBar extends JMenuBar
    {
        // Menu on the menu bar
        private JMenu menu;

        // Two options for the menu
        private JMenuItem menuOpen;
        private JMenuItem menuExit;

        // Reference to a file chooser pop-up
        private JFileChooser fileChooser;

        private ArrayList<String> listOfFiles;

        /**
         * Constructor: fully assemble the menu bar and attach the necessary action
         * listeners.
         */
        public FileMenuBar()
        {
            listOfFiles = new ArrayList<>();
            // Create the menu and add it to the menu bar
            menu = new JMenu("File");
            add(menu);

            // Create the menu items and add them to the menu
            menuOpen = new JMenuItem("Open Data File");
            menuOpen.setName("Menu Open");
            menuExit = new JMenuItem("Exit");
            menu.add(menuOpen);
            menu.add(menuExit);

            // Action listener for exit
            menuExit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            // Create the file chooser
            fileChooser = new JFileChooser(new File("./data/mesonet"));
            fileChooser.setMultiSelectionEnabled(true);

            // Action listener for file open
            menuOpen.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Ask for files
                    int returnVal = fileChooser.showOpenDialog(menuOpen);
                    // Did we get one?
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                        // Yes
                        File[] files = fileChooser.getSelectedFiles();
                        // System.out.println(files.length);
                        try
                        {
                            for (File file : files)
                            {
                                String fileName = file.toString();
                                System.out.println(fileName);
                                listOfFiles.add(fileName);
                            }
                        }
                        catch (Exception e2)
                        {
                            // Catch all other exceptions
                            JOptionPane.showMessageDialog(fileChooser, "File load error");
                            MesonetFrame.this.setCursor(null);
                        }
                    }
                    else
                    {
                        System.out.println("No files.");
                    }
                }
            });

        }

        public ArrayList<String> getFileList()
        {
            return (ArrayList<String>) listOfFiles.clone();
        }
    }

}
