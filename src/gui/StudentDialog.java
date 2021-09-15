package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Time;

import javax.imageio.ImageIO;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import DAO.DAO;
import DAO.Student;

public class StudentDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = null;
	private JTextField S_ID_textField;
	private JTextField Date_textField;
	private JTextField In_Time_textField;
	private JTextField Out_Time_textField;

	private DAO dao;

	private gui gui;

	private Student prevStudent = null;

	private boolean update=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		// INSERT and UPDATE GUI code
		try {
			StudentDialog dialog = new StudentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StudentDialog(gui gui,DAO dao,Student prevStudent,boolean update)
	{
		this();
		this.dao=dao;
		this.gui=gui;
		this.prevStudent=prevStudent;
		this.update=update;
		if (update) 
		{
			setTitle("Update Student");
			populateGui(prevStudent);
		}
	}

	// For update tab
	private void populateGui(Student Student)
	{
		S_ID_textField.setText(Student.getId().toString());
		Date_textField.setText(Student.getDate().toString());
		In_Time_textField.setText(Student.getIn_time());
		Out_Time_textField.setText(Student.getOut_time());	
	}

	public StudentDialog()
	{	
		// design structure
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
		
		try 
		{
			Image bgImage = ImageIO.read(loginGUI.class.getResource("../images/panelBG.png"));
			contentPanel = new JPanel()
			{
				private static final long serialVersionUID = -3157927814592952172L;

				public void paintComponent(Graphics g) {g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);}
			};
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		setTitle("Add Student");
		setBounds(100, 100, 300, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
			contentPanel.add(lblNewLabel, "2, 2, center, center");
		}
		{
			S_ID_textField = new JTextField();
			S_ID_textField.setFont(new Font("Dialog", Font.PLAIN, 15));
			contentPanel.add(S_ID_textField, "4, 2, fill, default");
			S_ID_textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Date");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 17));
			contentPanel.add(lblNewLabel_1, "2, 4, center, center");
		}
		{
			Date_textField = new JTextField();
			Date_textField.setFont(new Font("Dialog", Font.PLAIN, 15));
			contentPanel.add(Date_textField, "4, 4, fill, default");
			Date_textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("In Time");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 17));
			contentPanel.add(lblNewLabel_2, "2, 6, center, center");
		}
		{
			In_Time_textField = new JTextField();
			In_Time_textField.setFont(new Font("Dialog", Font.PLAIN, 15));
			contentPanel.add(In_Time_textField, "4, 6, fill, default");
			In_Time_textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Out Time");
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 17));
			contentPanel.add(lblNewLabel_3, "2, 8, center, center");
		}
		{
			Out_Time_textField = new JTextField();
			Out_Time_textField.setFont(new Font("Dialog", Font.PLAIN, 15));
			contentPanel.add(Out_Time_textField, "4, 8, fill, default");
			Out_Time_textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						save();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				InputMap im1 = okButton.getInputMap(JComponent.WHEN_FOCUSED);
				im1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
				im1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						gui.refresh();
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				InputMap im1 = cancelButton.getInputMap(JComponent.WHEN_FOCUSED);
				im1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
				im1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
			}
		}
	}

	protected void save() 
	{
		if 
		(	S_ID_textField.getText().length() == 0 ||
			Date_textField.getText().length() == 0 ||
			In_Time_textField.getText().trim().length() == 0 ||
			Out_Time_textField.getText().trim().length() == 0	) 
		{
			JOptionPane.showMessageDialog(gui, "One or More boxes were left empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// UPDATING THE DATABASE though the DAO (Student class)
		try 
		{
			Integer S_ID=null;
			try 
			{
				S_ID = Integer.parseInt(S_ID_textField.getText());
			}
			catch (Exception e)
			{
				throw new IllegalArgumentException("Invalid Arguments");
			}
			String Date = Date_textField.getText();
			String In_Time = In_Time_textField.getText();
			String Out_Time = Out_Time_textField.getText();
			Integer i=null;
			Student Student=null;
			if (update) 
			{
				Student = prevStudent;
				i=Student.getId();
				Student.setId(S_ID);
				Student.setDate(java.sql.Date.valueOf(Date));
				Student.setIn_time(Time.valueOf(In_Time));
				Student.setOut_time(Time.valueOf(Out_Time));
			}
			else 
			{
				Student = new Student(S_ID, Date, In_Time, Out_Time);
			}
			if(update) 
			{
				dao.updateStudent(Student,i);
			}
			else 
			{
				dao.addStudent(Student);
			}
			setVisible(false);
			dispose();

			gui.refresh();
			JOptionPane.showMessageDialog(gui,"Student Saved Successfully", "Student Added", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (IllegalArgumentException e) {
			gui.refresh();
			JOptionPane.showMessageDialog(gui, "Error: "+e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(gui, "Error: "+e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
