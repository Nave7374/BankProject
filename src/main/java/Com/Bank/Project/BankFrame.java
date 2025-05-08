package Com.Bank.Project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Com.Dto.LoginDto;
import Com.Dto.SignupDto;
import Com.Service.DatabaseServiece;
import Com.Service.LoginService;
import Com.Service.SignupService;
import Com.entity.Account;
import Com.repository.DatabaseCredentials;

public class BankFrame extends JFrame {

	/**
	 * I Don't Know What this is,You guys have a guess
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar = new JMenuBar();

	private JPanel mainpanel,loginpanel,homepanel,signuppanel,loanpanel,supportpanel,transactionpanel;
	private CardLayout layout;
	
	private boolean isLogedin = false;
	
	private JTextField userfield;
	private JPasswordField passfield;
	private String accno;
	
	private Account acc;
	
	private SignupService signupservice = SignupService.getInstance();
	private LoginService loginservice = LoginService.getInstance();
	private DatabaseCredentials dbservice = DatabaseServiece.getInstance();
	
	private JTextField fnfield = new JTextField();
	private JTextField emailfield = new JTextField();
	private JTextField lnfield = new JTextField();
	private JTextField dobfield = new JTextField();
	private JTextField phonefield = new JTextField();
	private JTextField genderfield = new JTextField();
	private JTextField passfieldsignup = new JTextField();
	private JTextField userfieldsignup = new JTextField();
	
	public BankFrame() {
		System.out.println("hello");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		this.setTitle("Axis Bank");
		this.setSize(900,700);
		this.setLayout(new GridBagLayout());
		this.setLocationByPlatform(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("C:\\Users\\sanjay\\Downloads\\imagesofbank.png").getImage());
		this.getContentPane().setBackground(Color.WHITE);
		
		JMenu home = new JMenu("Home");
		JMenu action = new JMenu("Transaction");
		JMenu support = new JMenu("Support");
		JMenu setting = new JMenu("Settings");
		
		JMenuItem homepa = new JMenuItem("Go To Home Page");
		JMenuItem loginpage = new JMenuItem("Go To Login Page");
		JMenuItem exit = new JMenuItem("Exit");
		
		loginpage.addActionListener(e -> layout.show(mainpanel, "Login"));
		homepa.addActionListener(e -> homePage());
		exit.addActionListener(e -> Exit());
		
		home.add(homepa);
		home.add(loginpage);
		home.add(exit);
		
		JMenuItem payloan = new JMenuItem("Withdraw/Credit Amount");
		JMenuItem getloan = new JMenuItem("Get Loan Info");
		
		getloan.addActionListener(e->{
			if(isLogedin) {
				layout.show(mainpanel, "Loan");
			}
		});
		payloan.addActionListener(e->{
			if(isLogedin) {
				transactionpanel = new TransactionPanel(acc);
				mainpanel.add(transactionpanel,"Transaction");
				layout.show(mainpanel, "Transaction");
			}
		});
		
		action.add(getloan);
		action.add(payloan);
		
		JMenuItem contact = new JMenuItem("Contact");
		JMenuItem accdetails = new JMenuItem("Account details");
		
		contact.addActionListener(e->{
			if(isLogedin) {
				layout.show(mainpanel, "Support");
			}
		});
		accdetails.addActionListener(e->accdetails());
		
		support.add(contact);
		support.add(accdetails);
		
		JMenuItem logout = new JMenuItem("Log out");
		logout.addActionListener(e -> Logout());
		
		setting.add(logout);
		
		menubar.add(home);
		menubar.add(action);
		menubar.add(support);
		menubar.add(setting);
		
		this.setJMenuBar(menubar);
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Exit();
			}
		});
		
		layout = new CardLayout();
		mainpanel = new JPanel(layout);
		mainpanel.setBackground(Color.WHITE);
		
		createloginpanel();
		createSignuppanel();

		layout.show(mainpanel, "Login");
		
		this.add(mainpanel);
		
		this.setVisible(true);
		//Cons End
	}	
	
//	 ----------- HOME PANEL Code -------------
	
	private void createhomepanel() {
		homepanel = new Homepannel(acc);
		mainpanel.add(homepanel,"Home");
		
		loanpanel = new LoanPanel();
		mainpanel.add(loanpanel,"Loan");
		
		transactionpanel = new TransactionPanel(acc);
		mainpanel.add(transactionpanel,"Transaction");
		
		supportpanel = SupportPanel.getInstance(layout, mainpanel);
		mainpanel.add(supportpanel, "Support");
		
	}
	
//	----------- LOGIN PANEL Code -------------
	
	private void createloginpanel() {
		loginpanel  = new JPanel();
		loginpanel.setLayout(new GridBagLayout());
		loginpanel.setBackground(Color.WHITE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		
		JLabel user = new JLabel("Username : ");
		JLabel pass = new JLabel("Password : ");
		
		userfield = new JTextField(15);
		passfield = new JPasswordField(15);
		
		JButton btn = new  JButton("Login");
		btn.addActionListener(e-> LoginCredentials(userfield.getText(),new String(passfield.getPassword())));
		
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
            public void mouseExited(MouseEvent e) {
                btn.setCursor(Cursor.getDefaultCursor());
            }
		});
		
		JLabel signup = new JLabel("Click Here To Create A New Account");
		JButton sb = new JButton("Sign-up");
		sb.addActionListener(e -> layout.show(mainpanel, "Signup"));
		
		sb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				sb.setCursor(Cursor.getDefaultCursor());
			}
		});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.LINE_END;
		loginpanel.add(user,gbc);
		
		gbc.gridx=1;
		gbc.anchor = GridBagConstraints.LINE_START;
		loginpanel.add(userfield,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.LINE_END;
		loginpanel.add(pass,gbc);
		
		gbc.gridx=1;
		gbc.anchor = GridBagConstraints.LINE_START;
		loginpanel.add(passfield,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=2;
		gbc.anchor = GridBagConstraints.CENTER;
		loginpanel.add(new JLabel("Click Here To Login"),gbc);
		
		gbc.gridy=3;
		loginpanel.add(btn,gbc);
		
		gbc.gridy=4;
		loginpanel.add(signup,gbc);
		
		gbc.gridy=5;
		loginpanel.add(sb,gbc);
		
		mainpanel.add(loginpanel,"Login");
		
	}

//	 ----------- LOGIN Button Code -------------
	
	private void LoginCredentials(String user,String pass) {
		LoginDto l =new LoginDto();
		l.setPassword(pass);
		l.setUsername(user);
		if(user.equals("") || pass.equals("")) JOptionPane.showMessageDialog(this,"Fill the Fields","Warning",JOptionPane.WARNING_MESSAGE);
		else if(!isLogedin && loginservice.login(l)){
			acc = dbservice.findbyUsername(user);
			isLogedin=true;
			createhomepanel();
			layout.show(mainpanel, "Home");
		}
		else if(isLogedin) {
			JOptionPane.showMessageDialog(this,"Already Logined","Error",JOptionPane.ERROR_MESSAGE);
		}
		else JOptionPane.showMessageDialog(this,"Wrong Input","Error",JOptionPane.ERROR_MESSAGE);
		
		userfield.setText("");
		passfield.setText("");
	}
	
//	 ----------- LOGout Button Code -------------
	
	private void Logout() {
		if(isLogedin){
			layout.show(mainpanel, "Login");
			dbservice.Update(acc);
			isLogedin=false;
			acc=null;
		}
	}
	
//	 ----------- Signup panel Code -------------
	
	private void createSignuppanel() {
		signuppanel = new JPanel();
		signuppanel.setLayout(new GridBagLayout());
		signuppanel.setBackground(Color.WHITE);
		
		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(10,10,10,10);
		
		JLabel Firstname = new JLabel("FirstName : ");
		JLabel Lastname = new JLabel("LastName : ");
		JLabel Dob = new JLabel("Date of Birth(dd-mm-yyyy): ");
		JLabel phone = new JLabel("PhoneNumber : ");
		JLabel gender = new JLabel("Gender(Male / Female / Other): ");
		JLabel username = new JLabel("UserName : ");
		JLabel password = new JLabel("PassWord : ");
		JLabel email = new JLabel("E-mail : ");
		
		Dimension fieldSize = new Dimension(150, 25);
        fnfield.setPreferredSize(fieldSize);
        lnfield.setPreferredSize(fieldSize);
        dobfield.setPreferredSize(fieldSize);
        phonefield.setPreferredSize(fieldSize);
        genderfield.setPreferredSize(fieldSize);
        userfieldsignup.setPreferredSize(fieldSize);
        passfieldsignup.setPreferredSize(fieldSize);
        emailfield.setPreferredSize(fieldSize);
		
        
		JButton finish = new JButton("Sign-Up");
		finish.addActionListener(e -> signupEvent(userfieldsignup.getText(),passfieldsignup.getText(),dobfield.getText(),phonefield.getText(),fnfield.getText(),lnfield.getText(),genderfield.getText(),emailfield.getText()));         
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				finish.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				finish.setCursor(Cursor.getDefaultCursor());
			}
		});
		
		g.gridx=0;
		g.gridy=0;
		g.fill = GridBagConstraints.HORIZONTAL;
		signuppanel.add(Firstname,g);
		
		g.gridx=1;
		signuppanel.add(fnfield,g);
		
		g.gridx=0;
		g.gridy=1;
		signuppanel.add(Lastname,g);
		
		g.gridx = 1;
		signuppanel.add(lnfield,g);
		
		g.gridx = 0;
		g.gridy = 2;
		signuppanel.add(phone,g);
		
		g.gridx = 1;
		signuppanel.add(phonefield,g);
		
		g.gridx=0;
		g.gridy=3;
		
		signuppanel.add(email,g);
		
		g.gridx = 1;
		signuppanel.add(emailfield,g);
		
		g.gridx=0;
		g.gridy=4;
		signuppanel.add(Dob,g);
		
		g.gridx = 1;
		signuppanel.add(dobfield,g);
		
		g.gridx= 0;
		g.gridy= 5;
		signuppanel.add(gender,g);
		
		g.gridx = 1;
		signuppanel.add(genderfield,g);
		
		g.gridx = 0;
		g.gridy = 6;
		signuppanel.add(username,g);
		
		g.gridx = 1;
		signuppanel.add(userfieldsignup,g);
		
		g.gridx = 0;
		g.gridy = 7;
		signuppanel.add(password,g);
		
		g.gridx = 1;
		signuppanel.add(passfieldsignup,g);
		
		g.gridwidth = 2;
		g.gridx=0;
		g.gridy=8;
		g.anchor = GridBagConstraints.CENTER;
		signuppanel.add(new JLabel("Click Here To Sign-up"),g);
		
		g.gridy = 9;
		signuppanel.add(finish,g);
		
		mainpanel.add(signuppanel,"Signup");
	}
	
	private void signupEvent(String user, String pass, String date, String phone, String firstname, String lastname, String gender,String email) {
		if(!CheckPassword(pass)) {
			JOptionPane.showMessageDialog(this, "Enter A Strong Password (min of 8 characters,one Uper case,one special character,atleast one number","Error",JOptionPane.ERROR_MESSAGE);
		}
		else if(user.equals("") || pass.equals("") || gender.equals("") || phone.equals("") || firstname.equals("") || lastname.equals("") || date.equals("") || email.equals("") ) {
			JOptionPane.showMessageDialog(this, "Fill All the Fields","Warning",JOptionPane.WARNING_MESSAGE);
		}
		else {
			accno = getAccNo();
			SignupDto s = new SignupDto();
			s.setAccno(accno);
			s.setBal(0);
			s.setDob(date);
			s.setEmail(email);
			s.setFirstname(firstname);
			s.setGender(gender);
			s.setLastname(lastname);
			s.setPassword(pass);
			s.setPhone(phone);
			s.setUsername(user);
			
			if(signupservice.Signup(s) ) {
				fnfield.setText("");
		        lnfield.setText("");
		        dobfield.setText("");
		        phonefield.setText("");
		        genderfield.setText("");
		        userfieldsignup.setText("");
		        passfieldsignup.setText("");
		        emailfield.setText("");
				layout.show(mainpanel, "Login");
				JOptionPane.showMessageDialog(this, "Account has been Created,You're Account no : "+accno,"Information",JOptionPane.INFORMATION_MESSAGE);
			}
			else JOptionPane.showMessageDialog(this, "Something Went Wrong (Username Already Present)","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean CheckPassword(String pass){
		return Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").matcher(pass).matches();
	}
	
	// ----------- DB Code -------------

	private String getAccNo() {
		String num ="";
		for(int i=0;i<15;i++)num+=(int)(Math.random()*10);
		return num;
	}

	private void homePage() {
		if(isLogedin) {
			homepanel = new Homepannel(acc);
			mainpanel.add(homepanel, "Home");
			layout.show(mainpanel, "Home");
		}
	}
	
	private void accdetails(){
		if(isLogedin)	JOptionPane.showMessageDialog(this, acc, "Account Details",JOptionPane.INFORMATION_MESSAGE);
		else	JOptionPane.showMessageDialog(this, "Please Login", "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	private void Exit() {
		if(isLogedin) Logout();
		this.dispose();
	}	
}