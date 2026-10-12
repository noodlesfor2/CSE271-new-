
/**
 * Jonah Moore
 * CSE 271 - B
 * 10/7/2026
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * GUI for Lab W7: House Hunting
 * 
 * This GUI depends on the methods and constructors implemented in the House
 * class.
 */
public class HouseHuntingGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private ArrayList<House> houses; // Stores every House read from file
    private ArrayList<House> displayedHouses; // Stores Houses currently shown in GUI

    private int currentIndex;
    private JTextArea listingArea;
    private JLabel positionLabel;
    private JLabel countLabel;
    private JTextField downPaymentField;
    private JButton previousButton;
    private JButton nextButton;
    private JButton calculateButton;
    private JButton calculateDefaultButton;
    private JButton showAllButton;
    private JButton showSoldButton;
    private JButton showForSaleButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HouseHuntingGUI());
    }

    /**
     * Creates the House Hunting application.
     */
    public HouseHuntingGUI() {
        houses = new ArrayList<House>();
        displayedHouses = new ArrayList<House>();
        currentIndex = 0;

        this.setTitle("House Hunting");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 450));

        this.buildGUI();
        this.loadListings("listings.txt");
        this.showAllHouses();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Builds the GUI components.
     */
    private void buildGUI() {
        setLayout(new BorderLayout(10, 10));

        // ========================================================= Top Panel

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("House Hunting");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        countLabel = new JLabel();

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(countLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ======================================================= Center Panel

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        listingArea = new JTextArea();

        listingArea.setEditable(false);
        listingArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        listingArea.setLineWrap(true);
        listingArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(listingArea);

        scrollPane.setBorder(BorderFactory.createTitledBorder("Current Listing"));

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Filter panel
        JPanel filterPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        filterPanel.setBorder(BorderFactory.createTitledBorder("View Listings"));

        showAllButton = new JButton("Show All");
        showSoldButton = new JButton("Show Sold");
        showForSaleButton = new JButton("Show For Sale");

        showAllButton.addActionListener(e -> showAllHouses());
        showSoldButton.addActionListener(e -> showSoldHouses());
        showForSaleButton.addActionListener(e -> showForSaleHouses());

        filterPanel.add(showAllButton);
        filterPanel.add(showSoldButton);
        filterPanel.add(showForSaleButton);

        centerPanel.add(filterPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        // ======================================================= Bottom Panel

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Navigation panel
        JPanel navigationPanel = new JPanel(new FlowLayout());

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        positionLabel = new JLabel();

        previousButton.addActionListener(e -> showPreviousHouse());
        nextButton.addActionListener(e -> showNextHouse());

        navigationPanel.add(previousButton);
        navigationPanel.add(positionLabel);
        navigationPanel.add(nextButton);

        // Mortgage calculator panel
        JPanel mortgagePanel = new JPanel(new FlowLayout());

        mortgagePanel.add(new JLabel("Down Payment: $"));

        downPaymentField = new JTextField(10);

        calculateButton = new JButton("Calculate Custom Payment");
        calculateDefaultButton = new JButton("Calculate with 20% Down");

        calculateButton.addActionListener(e -> calculateMortgage());
        calculateDefaultButton.addActionListener(e -> calculateDefaultMortgage());

        mortgagePanel.add(downPaymentField);
        mortgagePanel.add(calculateButton);
        mortgagePanel.add(calculateDefaultButton);

        bottomPanel.add(navigationPanel);
        bottomPanel.add(mortgagePanel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Reads House data from a file and creates House objects. Each line of the file
     * should contain: address price sqft beds baths status separated by tab
     * characters.
     * 
     * @param fileName name of the listing file
     */
    private void loadListings(String fileName) {

        // !!!!! ACTION ITEM !!!!!
        Scanner reader = null;

        try {
            reader = new Scanner(new File(fileName));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\t");

                houses.add(new House(parts));

            }

        } catch (FileNotFoundException fnf) {
            System.out.println("File cannot be found.");
        }
    }

    /**
     * Displays all House listings.
     */
    private void showAllHouses() {
        displayedHouses.clear();

        for (House h : houses) {
            displayedHouses.add(h);
        }

        currentIndex = 0;
        updateDisplay();
    }

    /**
     * Displays only Houses that have been sold.
     */
    private void showSoldHouses() {
        /*
         * Do not remove the line below. We want to start with an empty list before
         * adding the Houses that should currently be displayed.
         */
        displayedHouses.clear();

        // ACTION ITEM: ADD CODE HERE

        for (int i = 0; i < houses.size(); i++) {

            House currentHouse = houses.get(i);

            if (currentHouse.isSold()) {
                displayedHouses.add(currentHouse);
            }
        }

        /*
         * Do not remove the lines below. 1. Reset to the first House in the newly
         * filtered list. 2. Refresh the GUI so it displays the updated list of Houses.
         */
        currentIndex = 0;
        updateDisplay();
    }

    /**
     * Displays only Houses that are currently for sale.
     */
    private void showForSaleHouses() {
        /*
         * Do not remove the line below. We want to start with an empty list before
         * adding the Houses that should currently be displayed.
         */
        displayedHouses.clear();

        // ACTION ITEM: ADD CODE HERE
        for (int i = 0; i < houses.size(); i++) {

            House currentHouse = houses.get(i);

            if (currentHouse.isSold() == false) {
                displayedHouses.add(currentHouse);
            }
        }
        /*
         * Do not remove the lines below. 1. Reset to the first House in the newly
         * filtered list. 2. Refresh the GUI so it displays the updated list of Houses.
         */
        currentIndex = 0;
        updateDisplay();
    }

    /**
     * Displays the previous House.
     */
    private void showPreviousHouse() {
        if (displayedHouses.isEmpty()) {
            return;
        }
        currentIndex--;

        if (currentIndex < 0) {
            currentIndex = displayedHouses.size() - 1;
        }
        updateDisplay();
    }

    /**
     * Displays the next House.
     */
    private void showNextHouse() {
        if (displayedHouses.isEmpty()) {
            return;
        }

        currentIndex++;

        if (currentIndex >= displayedHouses.size()) {
            currentIndex = 0;
        }
        updateDisplay();
    }

    /**
     * Updates the information displayed in the GUI.
     */
    private void updateDisplay() {
        countLabel.setText(displayedHouses.size() + " Listings");

        if (displayedHouses.isEmpty()) {

            listingArea.setText("No listings available.");
            positionLabel.setText("0 / 0");

            previousButton.setEnabled(false);
            nextButton.setEnabled(false);
            calculateButton.setEnabled(false);
            calculateDefaultButton.setEnabled(false);

            return;
        }

        previousButton.setEnabled(true);
        nextButton.setEnabled(true);
        calculateButton.setEnabled(true);
        calculateDefaultButton.setEnabled(true);

        House currentHouse = displayedHouses.get(currentIndex);

        listingArea.setText(currentHouse.toString());
        positionLabel.setText((currentIndex + 1) + " / " + displayedHouses.size());
    }

    /**
     * Calculates a mortgage payment using the user's chosen down payment.
     */
    private void calculateMortgage() {

        if (displayedHouses.isEmpty()) {
            return;
        }

        try {
            double downPayment = Double.parseDouble(downPaymentField.getText());

            House currentHouse = displayedHouses.get(currentIndex);

            double payment = currentHouse.mortgageRate(downPayment);

            JOptionPane.showMessageDialog(this, String.format("Estimated Monthly Payment: $%.2f", payment),
                    "Mortgage Estimate", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Enter a valid numeric down payment.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Calculates a mortgage payment assuming a 20% down payment.
     */
    private void calculateDefaultMortgage() {

        if (displayedHouses.isEmpty()) {
            return;
        }

        House currentHouse = displayedHouses.get(currentIndex);

        double payment = currentHouse.mortgageRate();

        JOptionPane.showMessageDialog(this, String.format("Estimated Monthly Payment: $%.2f", payment),
                "Mortgage Estimate - 20% Down", JOptionPane.INFORMATION_MESSAGE);
    }

}