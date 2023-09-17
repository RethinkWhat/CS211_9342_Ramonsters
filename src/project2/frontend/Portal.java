package project2.frontend;

import javax.swing.*;
import java.awt.*;

/**
 * @author TBA
 * @version 1.00 (16 September 2023)
 * @apiNote Populates the UI components of the SLU Portal.
 * Template for Portal object.
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
        JPanel mainPanel = new JPanel(); // Holds the whole window
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ! Header Panel
        JPanel headerPanel = populateHeader(); // Holds the header
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ! Sidebar Panel
        JPanel sidebarPanel = populateSidebar(); // Holds the sidebar
        sidebarPanel.setPreferredSize(new Dimension(260,700));
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // ! Center Panel
        JPanel centerPanel = new JPanel(); // Holds the container for the program output
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(resources.antiflashWhite);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Portal constructor

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateHeader() {
        // Header panel
        JPanel headerPanel = new JPanel(); // Container for the header components
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(resources.thinPadding);
        headerPanel.setBackground(resources.yinmnBlue);
        headerPanel.setPreferredSize(new Dimension(1200, 55));

        // ! Header Panel components

        // ! Logo Label
        ImageIcon headerLogo = resources.scaleImage(resources.sluLogo, 25, 25);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(headerLogo);
        logoLabel.setFont(resources.montserratBlack.deriveFont(15f));
        logoLabel.setForeground(resources.antiflashWhite);
        logoLabel.setText("SLU Admin Portal");
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // ! Account Panel
        JPanel accountPanel = new JPanel(); // Holds the account details
        accountPanel.setLayout(new FlowLayout());
        accountPanel.setBackground(resources.airSuperiorityBlue);
        accountPanel.setPreferredSize(new Dimension(200,55));
        headerPanel.add(accountPanel, BorderLayout.EAST);

        // !! Account Panel Components

        // !! Account Image Label
        ImageIcon accountIcon = new ImageIcon("icons/account-icon-black.png");
        ImageIcon scaledAccountIcon = resources.scaleImage(accountIcon, 20, 20);

        JLabel accountImageLabel = new JLabel();
        accountImageLabel.setIcon(scaledAccountIcon);
        accountImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountPanel.add(accountImageLabel);

        // !! Account Name
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Manoj Kumar Tank");
        nameLabel.setFont(resources.montserrat.deriveFont(12f));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountPanel.add(nameLabel);

        return headerPanel;
    } // end of populateHeader method

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateSidebar() {
        // Sidebar Panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BorderLayout());
        sidebarPanel.setBorder(resources.thinPadding);
        sidebarPanel.setBackground(resources.airSuperiorityBlue);
        sidebarPanel.setPreferredSize(new Dimension(290,700));

        // ! Sidebar Panel Components

        // ! Semester Panel
        JPanel semesterPanel = new JPanel();
        semesterPanel.setLayout(new FlowLayout());
        semesterPanel.setBackground(resources.yinmnBlue);
        semesterPanel.setPreferredSize(new Dimension(290,25));
        sidebarPanel.add(semesterPanel, BorderLayout.NORTH);

        // !! Semester Panel Components

        // !! Semester Label
        JLabel semesterLabel = new JLabel();
        semesterLabel.setText("FIRST SEMESTER, 2023-2024");
        semesterLabel.setVerticalAlignment(SwingConstants.CENTER);
        semesterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        semesterLabel.setForeground(resources.antiflashWhite);
        semesterLabel.setFont(resources.montserratBold.deriveFont(12f));
        semesterPanel.add(semesterLabel);

        // ! Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridBagLayout());
        navPanel.setBackground(resources.airSuperiorityBlue);
        navPanel.setPreferredSize(new Dimension(290, 650));
        sidebarPanel.add(navPanel, BorderLayout.CENTER);

        // !! Navigation Panel Components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // fills the whole horizontal space
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,0,0,20);

        // !! Home Button
        gbc.gridy = 1;
        ImageIcon homeIcon = new ImageIcon("icons/home-icon-black.png");
        JButton homeButton = new JButton();
        homeButton.setText("Home");
        homeButton.setIcon(homeIcon);
        homeButton.setForeground(resources.antiflashWhite);
        homeButton.setHorizontalAlignment(SwingConstants.LEFT);
        homeButton.setFont(resources.montserratBold.deriveFont(12f));
        homeButton.setBorderPainted(true);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        navPanel.add(homeButton, gbc);

        // !! Schedule Button
        gbc.gridy = 2;
        ImageIcon scheduleIcon = new ImageIcon("icons/schedule-icon-black.png");
        JButton scheduleButton = new JButton();
        scheduleButton.setText("Schedule");
        scheduleButton.setIcon(scheduleIcon);
        scheduleButton.setHorizontalAlignment(SwingConstants.LEFT);
        scheduleButton.setFont(resources.montserratBold.deriveFont(12f));
        scheduleButton.setBorderPainted(false);
        scheduleButton.setOpaque(false);
        scheduleButton.setContentAreaFilled(false);
        navPanel.add(scheduleButton, gbc);

        // !! Attendance Button
        gbc.gridy = 3;
        ImageIcon torIcon = new ImageIcon("icons/tor-icon-black.png");
        JButton torButton = new JButton();
        torButton.setText("Transcript of Records");
        torButton.setIcon(torIcon);
        torButton.setHorizontalAlignment(SwingConstants.LEFT);
        torButton.setFont(resources.montserratBold.deriveFont(12f));
        torButton.setBorderPainted(false);
        torButton.setOpaque(false);
        torButton.setContentAreaFilled(false);
        navPanel.add(torButton, gbc);

        // !! Statement of Accounts Button
        gbc.gridy = 4;
        ImageIcon checklistIcon = new ImageIcon("icons/checklist-icon-black.png");
        JButton checklistButton = new JButton();
        checklistButton.setText("Curriculum Checklist");
        checklistButton.setIcon(checklistIcon);
        checklistButton.setHorizontalAlignment(SwingConstants.LEFT);
        checklistButton.setFont(resources.montserratBold.deriveFont(12f));
        checklistButton.setBorderPainted(false);
        checklistButton.setOpaque(false);
        checklistButton.setContentAreaFilled(false);
        navPanel.add(checklistButton, gbc);

        // !! Personal Details Button
        gbc.gridy = 5;
        ImageIcon personalIcon = new ImageIcon("icons/personal-icon-black.png");
        JButton personalButton = new JButton();
        personalButton.setText("Personal Details");
        personalButton.setIcon(personalIcon);
        personalButton.setHorizontalAlignment(SwingConstants.LEFT);
        personalButton.setFont(resources.montserratBold.deriveFont(12f));
        personalButton.setBorderPainted(false);
        personalButton.setOpaque(false);
        personalButton.setContentAreaFilled(false);
        navPanel.add(personalButton, gbc);

        return sidebarPanel;
    } // end of populateSidebar method


    /*
    To be removed.
    Will only be used for testing.
     */
    public static void main(String[] args) {
        new Portal();
    } // end of main method
} // end of class Portal
