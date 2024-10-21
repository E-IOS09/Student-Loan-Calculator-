import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TooltipLabel extends JPanel {
    private JLabel mainLabel; // Main label
    private JLabel questionMarkLabel; // Question mark label for popup

    private JPopupMenu popupMenu; // Popup menu to show small info

    public TooltipLabel(String labelText, String tooltipText) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create the main label
        mainLabel = new JLabel(labelText);
        mainLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Create the question mark label
        questionMarkLabel = new JLabel("?");
        questionMarkLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionMarkLabel.setForeground(Color.BLUE); // Blue color for question mark
        questionMarkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Create the popup menu
        popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Border for the popup
        popupMenu.setBackground(new Color(255, 255, 204)); // Light yellow background for popup

        // Add the tooltip text inside the popup
        JLabel popupLabel = new JLabel(tooltipText);
        popupLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Smaller font for popup text
        popupLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding inside popup
        popupMenu.add(popupLabel);

        // Add a mouse listener to the question mark to show the popup on click or hover
        questionMarkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Show the popup when hovering over the question mark
                popupMenu.show(questionMarkLabel, questionMarkLabel.getWidth(), questionMarkLabel.getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Hide the popup when the mouse leaves the question mark
                popupMenu.setVisible(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Toggle popup on click as well (optional)
                if (popupMenu.isVisible()) {
                    popupMenu.setVisible(false);
                } else {
                    popupMenu.show(questionMarkLabel, questionMarkLabel.getWidth(), questionMarkLabel.getHeight());
                }
            }
        });

        // Add the main label and question mark to the panel
        add(mainLabel);
        add(questionMarkLabel);
    }
}
