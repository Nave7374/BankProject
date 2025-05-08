package Com.Bank.Project;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Com.entity.Account;
import Com.entity.Transactions;

public class TransactionPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField amountField;
    private JButton withdrawButton, creditButton, resetButton;
    private JLabel balanceLabel;
    private Account acc;

    public TransactionPanel(Account acc) {
    	this.acc=acc;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        this.setBackground(Color.WHITE);
        
        // Amount field
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(15);

        // Buttons
        withdrawButton = new JButton("Withdraw");
        creditButton = new JButton("Credit");
        resetButton = new JButton("Reset");

        // Balance label
        balanceLabel = new JLabel("Current Balance: ₹" + acc.getBal());

        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(withdrawButton, gbc);
        gbc.gridx = 1;
        add(creditButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(resetButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(balanceLabel, gbc);

        // Add button actions
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdrawAmount();
            }
        });

        creditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creditAmount();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                amountField.setText("");
            }
        });
    }

    private void withdrawAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a valid amount!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount > acc.getBal()) {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Transaction Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            acc.setBal(acc.getBal()-amount);
            updateBalance();
            Transactions t = new Transactions(amount + "Amount has been Withdrawn");
            t.setAcc(acc);
            acc.getTransactions().add(t);
            JOptionPane.showMessageDialog(this, "₹" + amount + " withdrawn successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void creditAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a valid amount!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            acc.setBal(acc.getBal()+amount); 
            updateBalance();
            Transactions t = new Transactions(amount + "Amount has been Credited");
            t.setAcc(acc);
            acc.getTransactions().add(t);
            JOptionPane.showMessageDialog(this, "₹" + amount + " credited successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Current Balance: ₹" + acc.getBal());
    }

}