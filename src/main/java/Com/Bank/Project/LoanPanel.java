package Com.Bank.Project;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoanPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> loanTypeCombo;
    private JTextField amountField, tenureField, interestField;
    private JButton submitButton, resetButton;
    private JTextArea resultArea;
    

    public LoanPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        this.setBackground(Color.WHITE);

        // Loan Type
        JLabel loanTypeLabel = new JLabel("Loan Type:");
        loanTypeCombo = new JComboBox<>(new String[]{"Personal Loan", "Home Loan", "Car Loan", "Education Loan"});

        // Loan Amount
        JLabel amountLabel = new JLabel("Loan Amount:");
        amountField = new JTextField(15);

        // Tenure
        JLabel tenureLabel = new JLabel("Tenure (Years):");
        tenureField = new JTextField(15);

        // Interest Rate
        JLabel interestLabel = new JLabel("Interest Rate (%):");
        interestField = new JTextField(15);

        // Buttons
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        // Result Area
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        add(loanTypeLabel, gbc);
        gbc.gridx = 1;
        add(loanTypeCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(tenureLabel, gbc);
        gbc.gridx = 1;
        add(tenureField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(interestLabel, gbc);
        gbc.gridx = 1;
        add(interestField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(submitButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Add button actions
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateLoan();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
    }

    private void calculateLoan() {
        try {
            String loanType = (String) loanTypeCombo.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            int tenure = Integer.parseInt(tenureField.getText());
            double interestRate = Double.parseDouble(interestField.getText());

            double totalInterest = amount * (interestRate / 100) * tenure;
            double totalPayable = amount + totalInterest;

            resultArea.setText("Loan Type: " + loanType + "\n"
                    + "Loan Amount: " + amount + "\n"
                    + "Tenure: " + tenure + " years\n"
                    + "Interest Rate: " + interestRate + "%\n"
                    + "Total Interest: " + totalInterest + "\n"
                    + "Total Payable Amount: " + totalPayable);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        amountField.setText("");
        tenureField.setText("");
        interestField.setText("");
        resultArea.setText("");
    }

}
