import javax.swing.*;
import java.awt.*;

public class AboutPage extends JPanel {

    // Constructor for AboutPage
    public AboutPage(CardLayout cardLayout, JPanel parentPanel) {
        // Set layout for the AboutPage
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255)); // White background for the page

        // Create a container panel to hold the content in the middle of the page
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center content
        containerPanel.setOpaque(false); // Transparent background for the container

        // Create the content panel with shadow and rounded edges
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw shadow behind the box
                g2.setColor(new Color(0, 0, 0, 100)); // Shadow color (transparent black)
                g2.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 30, 30); // Shadow offset

                // Draw main box with rounded corners
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 20, getHeight() - 20, 30, 30); // Box with rounded corners
            }
        };
        contentPanel.setPreferredSize(new Dimension(400, 200)); // Set size of the content box
        contentPanel.setBackground(new Color(255, 255, 255)); // White background for the box
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding inside the box

        // Add the text content in the middle of the content panel
        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<h2>About the Loan Calculator</h2>"
                + "<p>This tool helps you calculate the real cost of your UK Student Loan, including interest rates and repayment terms.</p>"
                + "</div></html>", JLabel.CENTER);
        contentPanel.add(infoLabel, BorderLayout.CENTER); // Center the text in the box

        // Add the content panel to the container
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the contentPanel in the container
        containerPanel.add(contentPanel, gbc); // Add contentPanel to containerPanel

        // Add the container panel to the center of the AboutPage
        add(containerPanel, BorderLayout.CENTER); // Center everything

        // Create a "Back" button to navigate back to the main page
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(50, 50)); // Set the size of the button
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "MainPanel"));

        // Create a panel to center the Back button at the bottom
        JPanel backPanel = new JPanel(new GridBagLayout()); // GridBagLayout to center the button
        backPanel.setOpaque(false); // Transparent background
        backPanel.add(backButton); // Add the back button to the backPanel

        // Add the backPanel to the bottom of the AboutPage
        add(backPanel, BorderLayout.SOUTH); // Add back button at the bottom center
    }
}
