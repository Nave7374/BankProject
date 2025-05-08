package Com.Bank.Project;

import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(BankFrame::new);
	}
}