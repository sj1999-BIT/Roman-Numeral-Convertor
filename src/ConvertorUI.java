import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Application.ConvertAPI;
import Utils.SpringUtilities;

public class ConvertorUI {

    private static final String helpText = "RomanNumeralConverter can take in either Roman Values or Numeral values. " +
            "and convert it to the alternate form. It can convert values from 1 to 3999.\n" +
            "\n\nInstructions:\n" +
            "1. input the original value in input fielded, can be numeral or roman.\n" +
            "2. press the convert button\n" +
            "INVALID: if input is not Roman or Numeral, output return INVALID\n" +
            "ERROR: input is valid, but out of the valid range of the converter.";

    private static final String defaultGray = "Enter ROMAN/NUMERALS here...";

    /**
     * Standardised creation of panel with label and textfield for converter UI
     * @param labelStr label for the input
     * @param textField field to place input
     * @return JPanel object
     */
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
     * Creates actionalistener: takes input from textfield, pass into converter, return output into the correct field
     * @param inputField textField containing user inputs
     * @param outputField textField to present the converted input
     * @return an ActionListener which takes user input and pass them into the converterAPI for converion, then present
     * in output field.
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

    /**
     * Creates a JPanel with the button located in the centre.
     * @param button target button
     * @return JPanel Object
     */
    private static JPanel createCentredButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.add(new JPanel());
        buttonPanel.add(button);
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

        return finalButtonPanel;
    }

    /**
     * Creates a DocumentListener to implement default text function to input field such as showing defualt gray text
     * when empty.
     * @param inputTextField target inputfield
     * @return DocumentListener.
     */
    private static DocumentListener createDefaultInputDocListener(JTextField inputTextField) {
        return new DocumentListener() {
            boolean update = false;
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    // any input cause the default gray text to be cleared, leaving only user input
                    if (!update && !inputTextField.getText().equals(defaultGray)) {
                        String input = "";
                        try {
                            Document doc = e.getDocument();
                            input = doc.getText(e.getOffset(), e.getLength());
                            update = true;
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                        inputTextField.setText(input);
                        inputTextField.setForeground(Color.BLACK);
                    }
                });
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // when input field cleared, add in the default gray text
                SwingUtilities.invokeLater(() -> {
                    if (update && inputTextField.getText().isEmpty()) {
                        inputTextField.setText(defaultGray);
                        inputTextField.setForeground(Color.GRAY);
                        update = false;
                    }
                });
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text components
            }
        };
    }

    /**
     * Actual Panel containing all of the ConverterUI functions.
     * @return JPanel Object
     */
    private static JPanel createConverterPanel() {

        // create input text field
        JTextField textField = new JTextField(defaultGray);
        textField.setForeground(Color.GRAY);
        // add default input feature
        textField.getDocument().addDocumentListener(createDefaultInputDocListener(textField));

        //Create a panel for input
        JPanel inputPanel = createPanelWithLabelAndField("input: ", textField);

        // create a button to initiate conversion
        JButton curConverterButton = new JButton("CONVERT");
        // a border to improve looks
        curConverterButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),  // Outer border
                BorderFactory.createEmptyBorder(1, 1, 1, 1)  // Inner border (padding)
        ));

        Font newButtonFont=new Font(curConverterButton.getFont().getName(),curConverterButton.getFont().getStyle(),10);
        //Set JButton font using new created font
        curConverterButton.setFont(newButtonFont);
        JPanel buttonPanel = createCentredButtonPanel(curConverterButton);

        //Create output Panel
        JTextField textField2 = new JTextField(10);
        JPanel outputPanel = createPanelWithLabelAndField("output:", textField2);

        // add action listener to overall UI
        ActionListener curActionListener = createConverterActionListener(textField, textField2);
        // add to button for user click input
        curConverterButton.addActionListener(curActionListener);
        // add to textfield to response to enter
        textField.addActionListener(curActionListener);

        // combine all components into a single panel
        JPanel overallPanel = new JPanel(new SpringLayout());
        overallPanel.add(inputPanel);
        overallPanel.add(buttonPanel);
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

    /**
     * Creates a toolbar with a help button for the converterUI.
     * @return JToolBar object
     */
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

            // Show the dialog
            dialog.setVisible(true);

        });

        return toolBar;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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
                frame.setVisible(true);
            }
        });
    }
}
