package Com.Bank.Project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class SupportPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SupportPanel obj;
	
	public static SupportPanel getInstance(CardLayout layout, JPanel mainpanel) {
		if(obj==null)obj=new SupportPanel(layout,mainpanel);
		return obj;
	}
	
    public SupportPanel(CardLayout layout, JPanel mainpanel) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Contact Support", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JTextArea contactInfo = new JTextArea();
        contactInfo.setEditable(false);
        contactInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        contactInfo.setText(
            "Axis Bank Customer Support\n\n" +
            "Phone: +91-12345-67890\n" +
            "Email: support@axisbank.com\n" +
            "Address: 123, Axis Tower, Bangalore, India\n\n" +
            "Working Hours:\n" +
            "Monday - Friday: 9 AM to 6 PM\n" +
            "Saturday: 9 AM to 1 PM\n" +
            "Sunday: Closed"
        );

        JScrollPane scrollPane = new JScrollPane(contactInfo);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> layout.show(mainpanel, "Home"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
