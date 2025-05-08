package Com.Bank.Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePannel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static WelcomePannel obj;
	
	public static WelcomePannel getInstance() {
		if(obj==null) obj = new WelcomePannel();
		return obj;
	}
	
	private WelcomePannel() {
	
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel welcome = new JLabel("Welcome To Axis Bank!");
		welcome.setFont(new Font("Arial", Font.BOLD, 24));
		
		ImageIcon welcomeicon = resizeIcon("C:\\\\Users\\\\sanjay\\\\Downloads\\\\imagesofbank.png",70,70);
		JLabel welcomelabel =new JLabel(welcomeicon);
		
		this.setBackground(Color.WHITE);
		this.add(welcomelabel);
		this.add(welcome);
	}
	
	private ImageIcon resizeIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resized = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resized);
	}
	
}