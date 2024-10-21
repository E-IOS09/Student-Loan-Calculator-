import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculate extends JPanel {

    private JLabel resultLabel; // Label to display calculation results
    private JPanel resultPanel; // Panel to show the result in green box

    // Constructor for Calculate
    public Calculate(CardLayout cardLayout, JPanel parentPanel) {
        setLayout(new BorderLayout()); // Set layout for the form panel

        // Create the main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better form control
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between form elements
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Student Loan Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Loan Plan dropdown
        TooltipLabel loanPlanLabel = new TooltipLabel("Loan Plan:", "Select the type of student loan plan.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(loanPlanLabel, gbc);

        // Loan plan dropdown
        String[] loanPlans = {
                "Plan 1 (England/Wales/Ireland)",
                "Plan 2 (England/Wales)",
                "Postgraduate Loan (England/Wales)"
        };
        JComboBox<String> loanPlanDropdown = new JComboBox<>(loanPlans);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(loanPlanDropdown, gbc);

        // Salary Input
        TooltipLabel salaryLabel = new TooltipLabel("Annual Salary (Before Tax): £", "Enter your annual salary before taxes.");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(salaryLabel, gbc);

        JTextField salaryField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(salaryField, gbc);

        // Tuition Fees input
        TooltipLabel tuitionFeesLabel = new TooltipLabel("Tuition Fees per Year: £", "Enter the tuition fees you paid per year.");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(tuitionFeesLabel, gbc);

        JTextField tuitionFeesField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(tuitionFeesField, gbc);

        // Maintenance Loan input
        TooltipLabel maintenanceLoanLabel = new TooltipLabel("Maintenance Loan per Year: £", "Enter the maintenance loan you received per year.");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(maintenanceLoanLabel, gbc);

        JTextField maintenanceLoanField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(maintenanceLoanField, gbc);

        // Add Calculate and Reset buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton calculateButton = new JButton("Calculate");
        JButton resetButton = new JButton("Reset");

        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // Result panel for displaying calculation results
        resultPanel = new JPanel();
        resultPanel.setBackground(new Color(144, 238, 144)); // Light green background
        resultPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2)); // Dark green border
        resultPanel.setVisible(false); // Hide initially
        resultPanel.setLayout(new BorderLayout());

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        gbc.gridy = 6;
        formPanel.add(resultPanel, gbc);

        // Add action listener for the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset all input fields and hide the result panel
                salaryField.setText("");
                tuitionFeesField.setText("");
                maintenanceLoanField.setText("");
                loanPlanDropdown.setSelectedIndex(0);
                resultPanel.setVisible(false);
            }
        });

        // Add action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the calculation based on the selected loan plan
                String selectedPlan = (String) loanPlanDropdown.getSelectedItem();
                double salary = Double.parseDouble(salaryField.getText());
                double tuitionFees = Double.parseDouble(tuitionFeesField.getText());
                double maintenanceLoan = Double.parseDouble(maintenanceLoanField.getText());

                // Calculate based on loan plan
                double repayment = calculateRepayment(selectedPlan, salary);

                // Display result in the green box
                resultLabel.setText("Your annual repayment: £" + String.format("%.2f", repayment));
                resultPanel.setVisible(true);  // Show the result panel
            }
        });

        // Add the form panel to the main layout
        add(formPanel, BorderLayout.CENTER);

        // Create a "Back" button to go back to the main page
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> cardLayout.show(parentPanel, "MainPanel"));

        // Add the "Back" button at the bottom center
        JPanel backPanel = new JPanel(new FlowLayout());
        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);
    }

    // Method to calculate loan repayment based on loan plan
    private double calculateRepayment(String loanPlan, double salary) {
        double repayment = 0;

        // Plan 1 (England/Wales/Ireland)
        if (loanPlan.equals("Plan 1 (England/Wales/Ireland)")) {
            double threshold = 22015;
            double rate = 0.09;
            if (salary > threshold) {
                repayment = (salary - threshold) * rate;
            }
        }

        // Plan 2 (England/Wales)
        if (loanPlan.equals("Plan 2 (England/Wales)")) {
            double threshold = 27295;
            double rate = 0.09;
            if (salary > threshold) {
                repayment = (salary - threshold) * rate;
            }
        }

        // Postgraduate Loan (England/Wales)
        if (loanPlan.equals("Postgraduate Loan (England/Wales)")) {
            double threshold = 21000;
            double rate = 0.06;
            if (salary > threshold) {
                repayment = (salary - threshold) * rate;
            }
        }

        return repayment;
    }
}
