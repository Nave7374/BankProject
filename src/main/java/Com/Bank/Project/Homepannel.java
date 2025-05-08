package Com.Bank.Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Com.entity.Account;
import Com.entity.Transactions;

public class Homepannel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	
	private static WelcomePannel welcomepanel;
	
	
	public Homepannel(Account acc) {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		welcomepanel = WelcomePannel.getInstance();
		
		JPanel mainpanel = new JPanel(new GridLayout(2,4,20,20));
		mainpanel.setBackground(Color.WHITE);
		
		//Account panel
		JPanel accpanel = new JPanel(new GridLayout(3,1));
		accpanel.setBorder(BorderFactory.createTitledBorder("Account Summary"));
		accpanel.setBackground(Color.WHITE);
		
		JLabel bal = new JLabel("Account Balance :"+acc.getBal()+" rs");
		JLabel accno = new JLabel("Account no : " + acc.getAccno());
		
		
		accpanel.add(bal);
		accpanel.add(accno);
		
		List<Transactions> trans = acc.getTransactions();
//		JLabel Last = new JLabel(trans.getLast().getMsg());
//		accpanel.add(Last);
		
		//Transaction Panel
		JPanel transaction = new JPanel(new GridLayout(5,1));
		transaction.setBorder(BorderFactory.createTitledBorder("Transaction Details"));
		
        if(!trans.isEmpty()) {
        	int count=1;
        	for(int i=trans.size()-1; i>=0 && count<=5;i--) {
        		transaction.add(new JLabel(count++ +". " + trans.get(i).getMsg()));
        	}
        }else transaction.add(new JLabel("No Transactions Yet"));
        
        transaction.setBackground(Color.WHITE);
        
        mainpanel.add(accpanel);
        mainpanel.add(transaction);
		
		this.add(welcomepanel,BorderLayout.NORTH);
	    this.add(mainpanel,BorderLayout.CENTER);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	/*
		homepanel = new JPanel();
		homepanel.setLayout(new BorderLayout());
		homepanel.setBackground(Color.WHITE);
		
		ImageIcon welcomeicon = resizeIcon("C:\\\\Users\\\\sanjay\\\\Downloads\\\\imagesofbank.png",70,70);
//		ImageIcon billicon = resizeIcon("C:\\Users\\sanjay\\Downloads\\billicon.png",30,30);
//		ImageIcon transfericon = resizeIcon("C:\\Users\\sanjay\\Downloads\\transferimg.png",30,30);
		
		
		JLabel welcomelabel =new JLabel(welcomeicon);
//		JLabel billlabel = new JLabel(billicon);
//		JLabel transerflabel = new JLabel(transfericon);
		
		//Welcomepanel
		JPanel welcomepanel = new JPanel();
		welcomepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel welcome = new JLabel("Welcome To Axis Bank!");
		welcome.setFont(new Font("Arial", Font.BOLD, 24));
		
		welcomepanel.setBackground(Color.WHITE);
		welcomepanel.add(welcomelabel);
		welcomepanel.add(welcome);
		homepanel.add(welcomepanel,BorderLayout.NORTH);
		
		// main panel
		
        homepanel.add(mainpanel,BorderLayout.CENTER);
        
        CreatecardPanel();
        
//		homepanel = new HomePanel();
        
        mainpanel.add(homepanel,"Home");
	 */
	

	