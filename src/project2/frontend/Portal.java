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

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.GREEN);
        headerPanel.setPreferredSize(new Dimension(1200, 50));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.red);
        leftPanel.setPreferredSize(new Dimension(260,700));
        mainPanel.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.blue);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Portal constructor


    /*
    To be removed.
    Will only be used for testing.
     */
    public static void main(String[] args) {
        new Portal();
    } // end of main method
} // end of class Portal
