package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import DAO.DAO;
import DAO.Student;

public class gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField S_ID_textField;
	private JTextField In_Time_textField;
	private JTextField Out_Time_textField;
	private JTextField Date_textField;
	private JLabel Total_Time_Label;
	private JTextField TotalTime_textField;
	private JButton searchButton;
	private JScrollPane Table_Pane;
	private JTable table;
	private JPanel Menu_Panel;
	private JButton insertButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel ID_Options;
	private JButton ID_lessThan;
	private JButton ID_greaterThan;
	private JButton ID_equal;
	private JPanel Date_Options;
	private JButton Date_lessThan;
	private JButton Date_equal;
	private JButton Date_greater;
	private JPanel In_Time_Options;
	private JButton In_Time_Less;
	private JButton In_Time_equal;
	private JButton In_Time_greater;
	private JPanel Out_Time_Options;
	private JButton Out_Time_less;
	private JButton Out_Time_Equal;
	private JButton Out_Time_Greater;
	private JPanel Total_Time_Options;
	private JButton totalTime_less;
	private JButton totalTime_equal;
	private JButton totalTime_greater;
	private JButton advancedButton;
	private JButton logoutButton;
	private JPanel Adv_Options_Panel;
	private JPanel Search_Panel;

	private DAO StudentDAO;

	private String con1="=";
	private String con2="=";
	private String con3="=";
	private String con4="=";
	private String con5="=";

	public static void main(String[] args) 
	{
		// MAIN GUI CALLER
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					Dimension windowSize = frame.getSize();
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					Point centerPoint = ge.getCenterPoint();
					int dx = centerPoint.x - windowSize.width / 2;
					int dy = centerPoint.y - windowSize.height / 2;    
					frame.setLocation(dx, dy);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	
	// GUI Constructor with Background and design feel
	public gui() throws IOException {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		this.setUndecorated(false);	
		final Image bgImage = ImageIO.read(loginGUI.class.getResource("../Images/GrownEarly.png"));
		Color Green = new Color(0, 153, 51);
		try 
		{
			StudentDAO = new DAO();
		} 
		catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(gui.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
		}

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

		this.setVisible(true);
		setTitle("DAPP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		try 
		{
			Search_Panel = new JPanel()
			{
				private static final long serialVersionUID = 5747569395341314593L;

				public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
			};
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		// Designing each part of the GUI
		
		Search_Panel.setFocusable(false);
		Search_Panel.setBorder(null);
		contentPane.add(Search_Panel, BorderLayout.NORTH);
		Search_Panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),},
				new RowSpec[] {
						FormSpecs.LINE_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormSpecs.LINE_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),}));

		JLabel ID_Label = new JLabel("STD ID");
		ID_Label.setForeground(Color.WHITE);
		ID_Label.setFocusable(false);
		ID_Label.setFont(new Font("Dialog", Font.PLAIN, 17));
		Search_Panel.add(ID_Label, "1, 2, center, fill");

		S_ID_textField = new JTextField();
		S_ID_textField.setBorder(new EmptyBorder(2, 2, 2, 2));
		S_ID_textField.setFont(new Font("Dialog", Font.PLAIN, 13));
		Search_Panel.add(S_ID_textField, "2, 2, center, center");
		S_ID_textField.setColumns(10);

		JLabel Date_Label = new JLabel("Date");
		Date_Label.setForeground(Color.WHITE);
		Date_Label.setFocusable(false);
		Date_Label.setFont(new Font("Dialog", Font.PLAIN, 17));
		Search_Panel.add(Date_Label, "3, 2, center, fill");

		Date_textField = new JTextField();
		Date_textField.setBorder(new EmptyBorder(2, 2, 2, 2));
		Date_textField.setFont(new Font("Dialog", Font.PLAIN, 13));
		Search_Panel.add(Date_textField, "4, 2, center, center");
		Date_textField.setColumns(10);

		JLabel In_Time_Label = new JLabel("In Time");
		In_Time_Label.setForeground(Color.WHITE);
		In_Time_Label.setFocusable(false);
		In_Time_Label.setFont(new Font("Dialog", Font.PLAIN, 17));
		Search_Panel.add(In_Time_Label, "5, 2, center, fill");

		In_Time_textField = new JTextField();
		In_Time_textField.setBorder(new EmptyBorder(2, 2, 2, 2));
		In_Time_textField.setFont(new Font("Dialog", Font.PLAIN, 13));
		Search_Panel.add(In_Time_textField, "6, 2, center, center");
		In_Time_textField.setColumns(10);

		JLabel Out_Time_Label = new JLabel("Out Time");
		Out_Time_Label.setForeground(Color.WHITE);
		Out_Time_Label.setFocusable(false);
		Out_Time_Label.setFont(new Font("Dialog", Font.PLAIN, 17));
		Search_Panel.add(Out_Time_Label, "7, 2, center, fill");

		Out_Time_textField = new JTextField();
		Out_Time_textField.setBorder(new EmptyBorder(2, 2, 2, 2));
		Out_Time_textField.setFont(new Font("Dialog", Font.PLAIN, 13));
		Out_Time_textField.setForeground(new Color(0, 0, 0));
		Search_Panel.add(Out_Time_textField, "8, 2, center, center");
		Out_Time_textField.setColumns(10);

		Total_Time_Label = new JLabel("Total Time");
		Total_Time_Label.setForeground(Color.WHITE);
		Total_Time_Label.setFocusable(false);
		Total_Time_Label.setFont(new Font("Dialog", Font.PLAIN, 17));
		Search_Panel.add(Total_Time_Label, "9, 2, center, fill");

		TotalTime_textField = new JTextField();
		TotalTime_textField.setFont(new Font("Dialog", Font.PLAIN, 13));
		TotalTime_textField.setBorder(new EmptyBorder(2, 2, 2, 2));
		Search_Panel.add(TotalTime_textField, "10, 2, center, center");
		TotalTime_textField.setColumns(10);

		try 
		{
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		// Advanced option button functionality
		
		Adv_Options_Panel = new JPanel();
		Adv_Options_Panel.setVisible(false);
		Search_Panel.add(Adv_Options_Panel, "1, 4, 10, 1, fill, fill");
		Adv_Options_Panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),
				ColumnSpec.decode("90px"),
				ColumnSpec.decode("90px:grow"),},
				new RowSpec[] {
						RowSpec.decode("default:grow"),}));
		ID_Options = new JPanel()
		{
			private static final long serialVersionUID = -3178092340351385804L;

			public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
		};
		Adv_Options_Panel.add(ID_Options, "1, 1, 2, 1, fill, fill");

		ID_Options.setBorder(null);
		
		In_Time_Options = new JPanel()
		{
			private static final long serialVersionUID = -8780720202423750292L;

			public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
		};
		Adv_Options_Panel.add(In_Time_Options, "5, 1, 2, 1, fill, fill");
		
		// for input less than
		ID_lessThan = new JButton("<");
		ID_lessThan.setBorder(new EmptyBorder(2, 10, 2, 10));
		ID_lessThan.setFont(new Font("Dialog", Font.BOLD, 15));
		ID_lessThan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{

				con1="<";

				ID_lessThan.setBackground(Green);
				ID_equal.setBackground(Color.WHITE);
				ID_greaterThan.setBackground(Color.WHITE);
			}
		});

		ID_Options.add(ID_lessThan);

		// for input equal
		ID_equal = new JButton("=");
		ID_equal.setBorder(new EmptyBorder(2, 10, 2, 10));
		ID_equal.setFont(new Font("Dialog", Font.BOLD, 15));
		ID_equal.setBackground(Green);
		ID_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				con1="=";
				ID_equal.setBackground(Green);
				ID_lessThan.setBackground(Color.WHITE);
				ID_greaterThan.setBackground(Color.WHITE);


			}
		});
		ID_Options.add(ID_equal);

		// for input greater than
		ID_greaterThan = new JButton(">");
		ID_greaterThan.setBorder(new EmptyBorder(2, 10, 2, 10));
		ID_greaterThan.setFont(new Font("Dialog", Font.BOLD, 15));
		ID_greaterThan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con1=">";

				ID_greaterThan.setBackground(Green);
				ID_lessThan.setBackground(Color.WHITE);
				ID_equal.setBackground(Color.WHITE);

			}
		});
		ID_Options.add(ID_greaterThan);
		Date_Options = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = -8468904947965367710L;

			public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
		};
		Adv_Options_Panel.add(Date_Options, "3, 1, 2, 1, fill, fill");

		Date_lessThan = new JButton("<");
		Date_lessThan.setBorder(new EmptyBorder(2, 10, 2, 10));
		Date_lessThan.setFont(new Font("Dialog", Font.BOLD, 15));
		Date_lessThan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con2="<";

				Date_lessThan.setBackground(Green);				
				Date_equal.setBackground(Color.WHITE);
				Date_greater.setBackground(Color.WHITE);
			}
		});
		Date_Options.add(Date_lessThan);

		Date_equal = new JButton("=");
		Date_equal.setBorder(new EmptyBorder(2, 10, 2, 10));
		Date_equal.setBackground(Green);	
		Date_equal.setFont(new Font("Dialog", Font.BOLD, 15));
		Date_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con2="=";

				Date_equal.setBackground(Green);	
				Date_lessThan.setBackground(Color.WHITE);
				Date_greater.setBackground(Color.WHITE);
			}
		});
		Date_Options.add(Date_equal);

		Date_greater = new JButton(">");
		Date_greater.setBorder(new EmptyBorder(2, 10, 2, 10));
		Date_greater.setFont(new Font("Dialog", Font.BOLD, 15));
		Date_greater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				con2=">";

				Date_greater.setBackground(Green);				
				Date_equal.setBackground(Color.WHITE);
				Date_lessThan.setBackground(Color.WHITE);
			}
		});
		Date_Options.add(Date_greater);

		In_Time_Less = new JButton("<");
		In_Time_Less.setBorder(new EmptyBorder(2, 10, 2, 10));

		In_Time_Less.setFont(new Font("Dialog", Font.BOLD, 15));
		In_Time_Less.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				con3="<";

				In_Time_Less.setBackground(Green);
				In_Time_greater.setBackground(Color.WHITE);
				In_Time_equal.setBackground(Color.WHITE);
			}
		});
		In_Time_Options.add(In_Time_Less);

		In_Time_equal = new JButton("=");
		In_Time_equal.setBorder(new EmptyBorder(2, 10, 2, 10));
		In_Time_equal.setBackground(Green);	
		In_Time_equal.setFont(new Font("Dialog", Font.BOLD, 15));
		In_Time_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				con3="=";

				In_Time_equal.setBackground(Green);				
				In_Time_greater.setBackground(Color.WHITE);
				In_Time_Less.setBackground(Color.WHITE);

			}
		});
		In_Time_Options.add(In_Time_equal);

		In_Time_greater = new JButton(">");
		In_Time_greater.setBorder(new EmptyBorder(2, 10, 2, 10));

		In_Time_greater.setFont(new Font("Dialog", Font.BOLD, 15));
		In_Time_greater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con3=">";
				In_Time_greater.setBackground(Green);
				In_Time_Less.setBackground(Color.WHITE);
				In_Time_equal.setBackground(Color.WHITE);
			}
		});
		In_Time_Options.add(In_Time_greater);
		Out_Time_Options = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 4821975980719877788L;

			public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
		};
		Adv_Options_Panel.add(Out_Time_Options, "7, 1, 2, 1, fill, fill");

		Out_Time_less = new JButton("<");
		Out_Time_less.setBorder(new EmptyBorder(2, 10, 2, 10));
		Out_Time_less.setFont(new Font("Dialog", Font.BOLD, 15));
		Out_Time_less.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con4="<";

				Out_Time_less.setBackground(Green);				
				Out_Time_Greater.setBackground(Color.WHITE);
				Out_Time_Equal.setBackground(Color.WHITE);
			}
		});
		Out_Time_Options.add(Out_Time_less);

		Out_Time_Equal = new JButton("=");
		Out_Time_Equal.setBorder(new EmptyBorder(2, 10, 2, 10));
		Out_Time_Equal.setBackground(Green);	
		Out_Time_Equal.setFont(new Font("Dialog", Font.BOLD, 15));
		Out_Time_Equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con4="=";

				Out_Time_Equal.setBackground(Green);				
				Out_Time_Greater.setBackground(Color.WHITE);
				Out_Time_less.setBackground(Color.WHITE);
			}
		});
		Out_Time_Options.add(Out_Time_Equal);

		Out_Time_Greater = new JButton(">");
		Out_Time_Greater.setBorder(new EmptyBorder(2, 10, 2, 10));
		Out_Time_Greater.setFont(new Font("Dialog", Font.BOLD, 15));
		Out_Time_Greater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con4=">";

				Out_Time_Greater.setBackground(Green);				
				Out_Time_less.setBackground(Color.WHITE);
				Out_Time_Equal.setBackground(Color.WHITE);
			}
		});
		Out_Time_Options.add(Out_Time_Greater);
		Total_Time_Options = new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 8256857742092356424L;

			public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
		};
		Adv_Options_Panel.add(Total_Time_Options, "9, 1, 2, 1, fill, fill");

		totalTime_less = new JButton("<");
		totalTime_less.setBorder(new EmptyBorder(2, 10, 2, 10));
		totalTime_less.setFont(new Font("Dialog", Font.BOLD, 15));
		totalTime_less.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con5="<";

				totalTime_less.setBackground(Green);				
				totalTime_equal.setBackground(Color.WHITE);
				totalTime_greater.setBackground(Color.WHITE);
			}
		});
		Total_Time_Options.add(totalTime_less);

		totalTime_equal = new JButton("=");
		totalTime_equal.setBorder(new EmptyBorder(2, 10, 2, 10));
		totalTime_equal.setBackground(Green);	
		totalTime_equal.setFont(new Font("Dialog", Font.BOLD, 15));
		totalTime_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con5="=";

				totalTime_equal.setBackground(Green);	
				totalTime_less.setBackground(Color.WHITE);
				totalTime_greater.setBackground(Color.WHITE);
			}
		});
		Total_Time_Options.add(totalTime_equal);

		totalTime_greater = new JButton(">");
		totalTime_greater.setBorder(new EmptyBorder(2, 10, 2, 10));
		totalTime_greater.setFont(new Font("Dialog", Font.BOLD, 15));
		totalTime_greater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				con5=">";

				totalTime_greater.setBackground(Green);				
				totalTime_less.setBackground(Color.WHITE);
				totalTime_equal.setBackground(Color.WHITE);
			}
		});
		Total_Time_Options.add(totalTime_greater);

		Table_Pane = new JScrollPane();
		Table_Pane.setBackground(new Color(204, 153, 51));
		Table_Pane.setBorder(null);
		Table_Pane.setFocusable(false);
		contentPane.add(Table_Pane, BorderLayout.CENTER);

		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setBorder(null);
		table.setSelectionBackground(Color.BLACK);
		table.setSelectionForeground(Color.WHITE);
		TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setBackground(Color.WHITE);
		table.setFocusable(false);
		Table_Pane.setViewportView(table);

		try 
		{
			Menu_Panel = new JPanel()
			{
				private static final long serialVersionUID = 8887524991741383138L;

				public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
			};
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		Menu_Panel.setBorder(null);
		Menu_Panel.setBackground(Color.WHITE);
		Menu_Panel.setFocusable(false);
		contentPane.add(Menu_Panel, BorderLayout.SOUTH);
		Menu_Panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("85px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("495px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("470px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("110px"),},
				new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("31px"),}));
		
		// LOGOUT button that returns us to the LOGIN screen
		logoutButton = new JButton("Logout");
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(Color.BLACK);
		InputMap im5 = logoutButton.getInputMap(JComponent.WHEN_FOCUSED);
		logoutButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		Menu_Panel.add(logoutButton, "1, 2, center, center");
		final gui dapp=this;
		logoutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				loginGUI.main(null);
				dapp.setVisible(false);
				dapp.dispose();
			}
		});
		
		// Functionality and design of each of the 4 major buttons
		
		//SEARCH BUTTON FUNCTIONALITY GOES HERE
		searchButton = new JButton("Search");
		Menu_Panel.add(searchButton, "5, 2, center, center");
		searchButton.setBackground(Color.BLACK);
		searchButton.setBorder(new EmptyBorder(5, 15, 5, 15));
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		InputMap im4 = searchButton.getInputMap(JComponent.WHEN_FOCUSED);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{ 
				try {
					String S_ID = S_ID_textField.getText();
					String Date = Date_textField.getText();
					String In_Time = In_Time_textField.getText();
					String Out_Time = Out_Time_textField.getText();
					String Total_Time = TotalTime_textField.getText();

					List<Student> Students = null;
					if 
					(	S_ID != null && S_ID.trim().length() > 0 ||
					Date != null && Date.trim().length() > 0 ||
					In_Time != null && In_Time.trim().length() > 0 ||
					Out_Time != null && Out_Time.trim().length() > 0 ||
					Total_Time != null && Total_Time.trim().length() > 0	) 
					{
						if(S_ID == null || S_ID.trim().length() <= 0){con1="";}
						if(Date == null || Date.trim().length() <= 0){con2="";}
						if(In_Time == null || In_Time.trim().length() <= 0){con3="";}
						if(Out_Time == null || Out_Time.trim().length() <= 0){con4="";}
						if(Total_Time == null || Total_Time.trim().length() <= 0){con5="";}
						Students = StudentDAO.searchStudents(con1+S_ID, con2+Date, con3+In_Time, con4+Out_Time, con5+Total_Time);
					} 
					else 
					{
						Students = StudentDAO.getAllStudents();
					}

					Table TableModel = new Table(Students);
					table.setModel(TableModel);
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.CENTER);
					table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

				} catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(gui.this, "Invalid Search Arguments", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//INSERT BUTTON FUNCTIONALITY GOES HERE
		insertButton = new JButton("Insert");
		Menu_Panel.add(insertButton, "7, 2, center, center");
		insertButton.setBackground(Color.BLACK);
		insertButton.setBorder(new EmptyBorder(5, 21, 5, 21));
		insertButton.setForeground(Color.WHITE);
		insertButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		InputMap im1 = insertButton.getInputMap(JComponent.WHEN_FOCUSED);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				StudentDialog dialog = new StudentDialog(gui.this,StudentDAO,null,false);

				dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				Dimension windowSize = dialog.getSize();
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				Point centerPoint = ge.getCenterPoint();

				int dx = centerPoint.x - windowSize.width / 2;
				int dy = centerPoint.y - windowSize.height / 2;    
				dialog.setLocation(dx, dy);
				dialog.setUndecorated(true);
				dialog.setVisible(true);
			}
		});
		
		//UPDATE BUTTON FUNCTIONALITY GOES HERE
		updateButton = new JButton("Update");
		Menu_Panel.add(updateButton, "9, 2, center, center");
		updateButton.setBackground(Color.BLACK);
		updateButton.setBorder(new EmptyBorder(5, 16, 5, 16));
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		InputMap im2 = updateButton.getInputMap(JComponent.WHEN_FOCUSED);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int row = table.getSelectedRow();
				if (row<0) 
				{
					JOptionPane.showMessageDialog(gui.this, "You must select an Entry", "No Entry Selected", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Student tempStudent = (Student) table.getValueAt(row, Table.OBJECT_COL);

				StudentDialog dialog = new StudentDialog(gui.this,StudentDAO,tempStudent,true);

				dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				Dimension windowSize = dialog.getSize();
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				Point centerPoint = ge.getCenterPoint();

				int dx = centerPoint.x - windowSize.width / 2;
				int dy = centerPoint.y - windowSize.height / 2;    
				dialog.setLocation(dx, dy);
				dialog.setUndecorated(true);
				dialog.setVisible(true);
			}
		});

		//DELETE BUTTON FUNCTIONALITY GOES HERE
		deleteButton = new JButton("Delete");
		Menu_Panel.add(deleteButton, "11, 2, center, center");
		deleteButton.setBackground(Color.BLACK);
		deleteButton.setBorder(new EmptyBorder(5, 18, 5, 18));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		InputMap im3 = deleteButton.getInputMap(JComponent.WHEN_FOCUSED);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					int row = table.getSelectedRow();

					if (row<0) 
					{
						JOptionPane.showMessageDialog(gui.this, "You must select an Entry", "No Entry Selected", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int response = JOptionPane.showConfirmDialog(gui.this, "Delete this Entry?", "Delete", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

					if(response!=JOptionPane.YES_OPTION)
					{
						return;
					}

					Student tempStudent = (Student) table.getValueAt(row, Table.OBJECT_COL);

					StudentDAO.deleteStudent(tempStudent.getId(),tempStudent.getDate());

					refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		//ADVANCED BUTTON FUNCTIONALITY
		advancedButton = new JButton("Advanced");
		Menu_Panel.add(advancedButton, "15, 2, center, center");
		advancedButton.setForeground(Color.WHITE);
		advancedButton.setBackground(Color.BLACK);
		advancedButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		InputMap im6 = advancedButton.getInputMap(JComponent.WHEN_FOCUSED);
		advancedButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(Adv_Options_Panel.isVisible()==true)
				{
					Adv_Options_Panel.setVisible(false);
				}
				else
				{
					Adv_Options_Panel.setVisible(true);
				}

			}
		});
		this.refresh();

		//sets Buttons to be pressed by Enter key when in focus
		im1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		im1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		im3.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		im3.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		im4.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		im4.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		im5.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		im5.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		im6.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		im6.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
	}

	public void refresh() 
	{
		//refreshes table for user
		try {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			List<Student> Students = null;
			Students = StudentDAO.getAllStudents();
			Table TableModel = new Table(Students);
			table.setModel(TableModel);
			table.setDefaultRenderer(table.getColumnClass(0), centerRenderer);
			table.setDefaultRenderer(table.getColumnClass(1), centerRenderer);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(gui.this, "Error: "+e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
