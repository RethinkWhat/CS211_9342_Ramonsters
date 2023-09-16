package project2.frontend;

import javax.swing.*;
import java.awt.*;

/**
 * @author TBA
 * @version 1.00 (16 September 2023)
 * Template for Portal object.
 * Populates the UI components of the SLU Portal.
 */
public class Portal extends JFrame {
    /**
     * An object of Resources that is used to invoke UI resources and elements.
     */
    private final Resources resources = new Resources();

    /**
     * Constructs an object of Portal.
     * Contains the components of the SLU Portal.
     */
    public Portal() {
        super("SLU Portal");

        resources.loadFonts();
        setIconImage(resources.sluLogo.getImage());

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ! Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.GREEN);
        headerPanel.setPreferredSize(new Dimension(1200, 50));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // !! Header Panel Components
        JPanel headerComponentsPanel = populateHeader();
        headerPanel.add(headerComponentsPanel);

        // ! Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(resources.airSuperiorityBlue);
        leftPanel.setPreferredSize(new Dimension(260,700));
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // ! Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(resources.antiflashWhite);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Portal constructor

    private JPanel populateHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(resources.thinPadding);
        headerPanel.setBackground(resources.yinmnBlue);


        ImageIcon headerLogo = resources.scaleImage(resources.sluLogo, 25, 25);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(headerLogo);
        logoLabel.setFont(resources.montserratBlack.deriveFont(15f));
        logoLabel.setForeground(resources.antiflashWhite);
        logoLabel.setText("SLU Portal");
        headerPanel.add(logoLabel, BorderLayout.WEST);

        return headerPanel;
    } // end of populateHeader method


    /*
    To be removed.
    Will only be used for testing.
     */
    public static void main(String[] args) {
        new Portal();
    } // end of main method
} // end of class Portal
