package test;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Application.ConvertAPI;
import Utils.SpringUtilities;

public class TestGUI {

    private static final String helpText = "RomanNumeralConverter can take in either Roman Values or Numeral values. " +
            "and convert it to the alternate form. It can convert values from 1 to 3999.\n" +
            "\n\nInstructions:\n" +
            "1. input the original value in input fielded, can be numeral or roman.\n" +
            "2. press the convert button\n" +
            "INVALID: if input is not Roman or Numeral, output return INVALID\n" +
            "ERROR: input is valid, but out of the valid range of the converter.";

    private static JPanel createPanelWithLabelAndField(String labelStr, JTextField textField) {
        //Create a panel
        JPanel curPanel = new JPanel(new SpringLayout());
        JLabel l = new JLabel(labelStr, JLabel.TRAILING);
        curPanel.add(l);
        l.setLabelFor(textField);
        curPanel.add(textField);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(curPanel,
                1, 2, //rows, cols
                0, 0,        //initX, initY
                0, 0);       //xPad, yPad

        return curPanel;
    }

    /**
     *  Creates actionalistener: takes input from textfield, pass into converter, return output into the correct field
     */
    private static ActionListener createConverterActionListener(JTextField inputField, JTextField outputField) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = inputField.getText().toUpperCase();
                    String output = ConvertAPI.convert(input);
                    outputField.setText(output);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(outputField, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private static JPanel createConverterPanel() {
        String defaultGray = "Enter ROMAN/NUMERALS here...";
        //Create a panel for input
        JTextField textField = new JTextField(defaultGray);

        JPanel inputPanel = new JPanel();
        JLabel l = new JLabel("input:  ", JLabel.TRAILING);
        inputPanel.add(l);

        JTextPane inputTextPane = new JTextPane();
        StyledDocument doc = inputTextPane.getStyledDocument();

        Style style = inputTextPane.addStyle("Uneditable", null);
        StyleConstants.setForeground(style, Color.GRAY);



        //Lay out the panel.
        SpringUtilities.makeCompactGrid(inputPanel,
                1, 2, //rows, cols
                0, 0,        //initX, initY
                0, 0);       //xPad, yPad



        // create a button to initiate conversion
        JPanel buttonPanel = new JPanel(new SpringLayout());
        JButton curConverterButton = new JButton("CONVERT");

//        curConverterButton.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.BLACK, 1),  // Outer border
//                BorderFactory.createEmptyBorder(1, 1, 1, 1)  // Inner border (padding)
//
//        ));

        curConverterButton.setPreferredSize(new Dimension(200, 30));

        Font newButtonFont=new Font(curConverterButton.getFont().getName(),curConverterButton.getFont().getStyle(),10);

        //Set JButton font using new created font
        curConverterButton.setFont(newButtonFont);

        buttonPanel.add(new JPanel());
        buttonPanel.add(curConverterButton, BorderLayout.CENTER);
        buttonPanel.add(new JPanel());

        //Lay out the panel.
        SpringUtilities.makeGrid(buttonPanel,
                1, 3, //rows, cols
                0, 0,        //initX, initY
                0, 0);       //xPad, yPad

        JPanel finalButtonPanel = new JPanel(new SpringLayout());

        finalButtonPanel.add(new JPanel());
        finalButtonPanel.add(buttonPanel);
        finalButtonPanel.add(new JPanel());

        //Lay out the panel.
        SpringUtilities.makeGrid(finalButtonPanel,
                3, 1, //rows, cols
                0, 0,        //initX, initY
                0, 0);       //xPad, yPad



        //Create output Panel
        JTextField textField2 = new JTextField(10);
        JPanel outputPanel = createPanelWithLabelAndField("output:", textField2);

        // add action listener
        ActionListener curActionListener = createConverterActionListener(textField, textField2);
        curConverterButton.addActionListener(curActionListener);
        textField.addActionListener(curActionListener);

        // combine all components into a single panel
        JPanel overallPanel = new JPanel(new SpringLayout());
//        overallPanel.add(inputPanel, BorderLayout.NORTH);
//        overallPanel.add(buttonPanel, BorderLayout.CENTER);
//        overallPanel.add(outputPanel, BorderLayout.SOUTH);
        overallPanel.add(inputPanel);
        overallPanel.add(finalButtonPanel);
        overallPanel.add(outputPanel);

        //Lay out the panel.
        SpringUtilities.makeGrid(overallPanel,
                3, 1, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad



        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Combine Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        overallPanel.setOpaque(true);  //content panes must be opaque

        overallPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),  // Outer border
                BorderFactory.createEmptyBorder(10, 10, 10, 10)  // Inner border (padding)
        ));

        return overallPanel;
    }

    private static JToolBar createToolBarForConverter() {
        // Create a toolbar
        JToolBar toolBar = new JToolBar("Toolbar");
        JFrame frame = new JFrame();

        // Create a help button
        JButton helpButton = new JButton("Help");
        toolBar.add(helpButton);

        // Add an action listener to the help button
        helpButton.addActionListener(e -> {
            // Create a new dialog
            JDialog dialog = new JDialog(frame, "Help", true);
            dialog.setSize(300, 300);

            // Create a text area with some long text
            JTextArea textArea = new JTextArea(helpText);

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            // Add the text area to a scroll pane
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 100));

            // Add the scroll pane to the dialog
            dialog.add(scrollPane);

            // Pack the dialog to adjust its size to its content
//            dialog.pack();

            // Show the dialog
            dialog.setVisible(true);

            // Create a label with some text
//            JLabel label = new JLabel(helpText);
//            dialog.add(label);

            // Show the dialog
//            dialog.setVisible(true);
        });

        return toolBar;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        JPanel overallPanel = new JPanel(new SpringLayout());

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        //Create and set up the window.
        JFrame frame = new JFrame("RomanNumeralConverter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        overallPanel.add(createToolBarForConverter(), BorderLayout.PAGE_START);

        overallPanel.add(createConverterPanel());

        SpringUtilities.makeCompactGrid(overallPanel,
                2, 1, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        //Set up the content pane.
        overallPanel.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(overallPanel);

        frame.setSize(400, 300);

        //Display the window.
//        frame.pack();
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }

    public static void setContainerSize(Container parent,
                                        int pad) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component[] components = parent.getComponents();
        Spring maxHeightSpring = Spring.constant(0);
        SpringLayout.Constraints pCons = layout.getConstraints(parent);

        //Set the container's right edge to the right edge
        //of its rightmost component + padding.
        Component rightmost = components[components.length - 1];
        SpringLayout.Constraints rCons =
                layout.getConstraints(rightmost);
        pCons.setConstraint(
                SpringLayout.EAST,
                Spring.sum(Spring.constant(pad),
                        rCons.getConstraint(SpringLayout.EAST)));

        //Set the container's bottom edge to the bottom edge
        //of its tallest component + padding.
        for (int i = 0; i < components.length; i++) {
            SpringLayout.Constraints cons =
                    layout.getConstraints(components[i]);
            maxHeightSpring = Spring.max(maxHeightSpring,
                    cons.getConstraint(
                            SpringLayout.SOUTH));
        }
        pCons.setConstraint(
                SpringLayout.SOUTH,
                Spring.sum(Spring.constant(pad),
                        maxHeightSpring));
    }
}
