import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoanCal extends JFrame {

    private CardLayout cardLayout;  // For switching between panels
    private JPanel mainPanel;       // Main calculator panel (will now include the form)
    private JPanel aboutPanel;      // About page panel
    private JPanel parentPanel;     // Parent panel that holds all pages

    public StudentLoanCal() {
        // Set title of the JFrame
        setTitle("Student Loan Calculator");

        // Set size of the JFrame
        setSize(600, 600);  // Increased height to accommodate the form

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the CardLayout and parentPanel
        cardLayout = new CardLayout();
        parentPanel = new JPanel(cardLayout);

        // Create the main calculator panel (which will now include the Calculate form)
        mainPanel = createMainPanel();

        // Create the About page panel
        aboutPanel = new AboutPage(cardLayout, parentPanel);

        // Add both panels to the parent panel (CardLayout container)
        parentPanel.add(mainPanel, "MainPanel");   // Main calculator view
        parentPanel.add(aboutPanel, "AboutPanel"); // About page view

        // Add parent panel to the JFrame
        add(parentPanel);

        // Display the main calculator panel by default
        cardLayout.show(parentPanel, "MainPanel");

        // Make the JFrame visible
        setVisible(true);
    }

    // Method to create the main calculator view panel
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Set background color to light purple
        panel.setBackground(new Color(220, 200, 255)); // Light purple

        // Create the top panel (Logo and buttons)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(220, 200, 255)); // Match frame background

        // Add a logo (use a placeholder for now, you can replace it with an image)
        JLabel logoLabel = new JLabel("LOGO", JLabel.CENTER);
        logoLabel.setPreferredSize(new Dimension(100, 100));
        logoLabel.setFont(new Font("Serif", Font.BOLD, 20)); // Elegant font

        // Add a title label
        JLabel titleLabel = new JLabel("Student Loan Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28)); // Larger font size

        // Add the logo and title to the top panel
        topPanel.add(logoLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Create a button panel for About and Articles buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(220, 200, 255)); // Match frame background

        // Create the buttons with fancy styles
        JButton aboutButton = createFancyButton("About"); // Will switch to "About" page
        JButton articlesButton = createFancyButton("Articles");

        // Add action listener to About button to switch to About page
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to About page when About button is clicked
                cardLayout.show(parentPanel, "AboutPanel");
            }
        });

        // Add the buttons to the button panel
        buttonPanel.add(aboutButton);
        buttonPanel.add(articlesButton);

        // Add the button panel to the top panel
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Add the top panel to the main panel
        panel.add(topPanel, BorderLayout.NORTH);

        // Add the Calculate form directly to the main panel
        Calculate calculateForm = new Calculate(cardLayout, parentPanel);
        panel.add(calculateForm, BorderLayout.CENTER);  // Add the form below the top panel

        return panel;
    }

    // Method to create fancy buttons with margin, padding, and border
    private JButton createFancyButton(String text) {
        JButton button = new JButton(text);

        // Set the font for the button
        button.setFont(new Font("Arial", Font.PLAIN, 16));

        // Set text color and make sure the button doesn't use default background handling
        button.setForeground(new Color(70, 70, 70)); // Dark text
        button.setFocusPainted(false); // Remove the focus painting

        // Keep content area filled for background color to show
        button.setOpaque(true);
        button.setContentAreaFilled(true); // Enable background painting

        // Set the preferred size for the button
        button.setPreferredSize(new Dimension(120, 40));

        // Add padding by setting an empty border around the button text
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a visible border to the button, rounded corners (line border)
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150), 2, true), // Outer border (rounded, gray color)
                BorderFactory.createEmptyBorder(5, 15, 5, 15) // Inner padding for content inside the border
        ));

        // Add hover effect (change background color on hover)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 33, 250)); // Blue color on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 255, 255)); // Revert to original white background
            }
        });

        return button;
    }

}
