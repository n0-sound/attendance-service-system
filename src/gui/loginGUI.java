package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class loginGUI implements ActionListener
{
	static JLabel userLabel;
	static JTextField userText;
	static JLabel passwordLabel;
	static JPasswordField passwordText;
	static JButton loginButton;
	static JLabel result;
	private static Connection conn;

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JPanel panel=null;
		
		// CONNECTION ESTABLISHMENT
		try 
		{
			conn = DriverManager.getConnection("jdbc:sqlite:Instructor.db");
			
			// BACKGROUND
			Image bgImage = ImageIO.read(loginGUI.class.getResource("../images/loginBG.jpg"));
			panel = new JPanel()
			{
				private static final long serialVersionUID = -3157927814592952172L;

				public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
			};
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// DESIGN ENHANCEMENT
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// STRUCTURE DESIGN
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(panel);
		panel.setLayout(null);
		userLabel = new JLabel("Username");
		userLabel.setForeground(Color.WHITE);
		userLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		userLabel.setBounds(107,201,197,52);
		panel.add(userLabel);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		passwordLabel.setBounds(107,332,222,52); //10 to the right, 50 down

		userText = new JTextField();
		userText.setBackground(Color.WHITE);
		userText.setFont(new Font("Dialog", Font.BOLD, 30));
		userText.setBounds(107,263,263,42);
		panel.add(userText);

		passwordText = new JPasswordField();
		passwordText.setBackground(Color.WHITE);
		passwordText.setFont(new Font("Dialog", Font.BOLD, 30));
		passwordText.setBounds(107,394,263,42);
		panel.add(passwordText);
		panel.add(passwordLabel);


		loginButton = new JButton("Login");
		loginButton.setBackground(Color.BLACK);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBorder(null);
		loginButton.setFont(new Font("Dialog", Font.BOLD, 30));

		loginButton.setBounds(108,532,127,52);
		panel.add(loginButton);

		result = new JLabel("");
		result.setBounds(70,70,300,25);
		panel.add(result);
		
		// This lets the login GUI spawn in the middle with no max, min, and close button
		Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        frame.setLocation(dx, dy);
		frame.setUndecorated(true);
		frame.setVisible(true);

		frame.getRootPane().setDefaultButton(loginButton);

		// CREDENTIAL CHECKING on LOGIN button press
		loginButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						try {
							String user = userText.getText();
							@SuppressWarnings("deprecation")
							String password = passwordText.getText();
							boolean valid=false;

							Statement myStmt = conn.createStatement();
							ResultSet rset = myStmt.executeQuery("select Pass from 'Instructor_351' where User='"+user+"'");

							while(rset.next())
							{
								if(rset.getString("Pass").equals(password)) 
								{
									valid=true;
									break;
								}
							}
							
							// Opens GUI and removes LOGIN screen
							if(valid)
							{	
								gui gui = new gui();
								Dimension windowSize = gui.getSize();
						        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						        Point centerPoint = ge.getCenterPoint();

						        int dx = centerPoint.x - windowSize.width / 2;
						        int dy = centerPoint.y - windowSize.height / 2;    
						        gui.setLocation(dx, dy);
								
								result.setBounds(105,70,300,25);
								frame.setVisible(false);
								frame.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Invalid", JOptionPane.ERROR_MESSAGE);
							}
						} 
						catch (Exception e1) 
						{
							JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Invalid", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
}
