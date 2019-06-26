/*
 * Simone Ray
 * Assignment 2, Part 2
 * 5/10/19
 *
 * This Sorting GUI allows users to generate N numbers in sorted or random order, which are then saved to a file.
 * The user can then select a sorting algorithm to apply to the generated numbers and the GUI will display the
 * running time of the selected sorting algorithm.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SortingGUI {
    //Initialize text/selection boxes and labels
    private JFrame frm = new JFrame("Run Time of Different Sorting Algorithms");
    private JPanel pnlMain = new JPanel();

    private JLabel dataSizeLbl = new JLabel("  Enter Data Size: ");
    private JTextField dataSizeTxt = new JTextField(20);

    private JLabel generateOptionsLbl = new JLabel("  Select if data should be sorted or random: ");
    private String[] sorted = {"Sorted", "Random"};
    private JComboBox generateOptions = new JComboBox(sorted);

    private JLabel sortingOptionsLbl = new JLabel("  Select a sorting algorithm: ");
    private String[] sortingAlg = Context.getSortingStrategies();
    private JComboBox sortingOptions = new JComboBox(sortingAlg);

    private JButton sortBtn = new JButton("SORT");

    private JLabel runningTimeLbl = new JLabel("  Running Time: ");
    private JTextField runningTimeTxt = new JTextField(20);

    public SortingGUI() {
        pnlMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.insets = new Insets(5,30,5,30);
        pnlMain.add(dataSizeLbl, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlMain.add(dataSizeTxt, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlMain.add(generateOptionsLbl, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlMain.add(generateOptions, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 20;
        pnlMain.add(sortingOptionsLbl, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        pnlMain.add(sortingOptions, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 20;
        gbc.gridwidth = 2;
        pnlMain.add(sortBtn, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlMain.add(runningTimeLbl, gbc);
        runningTimeLbl.setFont(runningTimeLbl.getFont().deriveFont(Font.BOLD, 14f));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        pnlMain.add(runningTimeTxt, gbc);

        frm.add(pnlMain);

        // SORT button
        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Random rand = new Random();
                    int n = Integer.parseInt(dataSizeTxt.getText());

                    //Generate array of N random numbers
                    Comparable[] array = new Comparable[n];
                    for (int i = 0; i < n; i++) {
                        array[i] = rand.nextInt();
                    }

                    // Write generated numbers to a file
                    if (generateOptions.getSelectedItem().equals("Sorted")) {
                        Sorting type = Context.getSortMethod("MergeSort");
                        type.sort(array);
                        writeToFile("sorted500000", array);
                    } else {
                        writeToFile("random500000", array);
                    }

                    // Find running time of selected sorting algorithm
                    long initialTime = System.currentTimeMillis();
                    int selection = sortingOptions.getSelectedIndex();
                    Sorting type = Context.getSortMethod(sortingAlg[selection]);    //sort according to selection
                    type.sort(array);

                    long finalTime = System.currentTimeMillis();
                    long runningTime = finalTime - initialTime;

                    // Display run time
                    runningTimeTxt.setBackground(Color.CYAN);
                    int delayTime = 1000; //1 second
                    new Timer(delayTime, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            runningTimeTxt.setBackground(Color.WHITE);
                            // Stop the timer
                            ((Timer) e.getSource()).stop();
                        }
                    }).start();
                    runningTimeTxt.setText(runningTime + " ms");

                } catch (NumberFormatException nfe) {
                    runningTimeTxt.setText("Please enter an integer for data size.");
                } catch (IOException ioe) {
                    runningTimeTxt.setText(ioe.getMessage());
                }
            }
        });

        frm.setVisible(true);
        frm.setPreferredSize(new Dimension(800, 500));
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Write a given array to the file
    public static void writeToFile(String fileName, Comparable[] array) throws IOException {
        File file = new File(fileName);
        BufferedWriter bf = new BufferedWriter(new FileWriter(file, false));
        try {
            for (int i = 0; i < array.length; i++) {
                bf.write(array[i].toString() + "\n");
            }
        } finally {
            bf.close();
        }
    }

    // Main method
    public static void main(String[] args) {
        SortingGUI s = new SortingGUI();
    }
}
