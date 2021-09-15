package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class DAO {
	private Connection conn;

	public DAO() throws Exception
	{
		/*
		  // get db properties
			Properties props = new Properties();
			props.load(new FileInputStream("Properties"));

			String user = props.getProperty("User");
			String password = props.getProperty("Pass");
			String dburl = props.getProperty("DBurl");
		 */
		// connect to database
		conn = DriverManager.getConnection("jdbc:sqlite:Student.db");

		System.out.println("DAO - DB connection successful");
	}
	
	public void deleteStudent(int StudentId,String Date) throws Exception 
	{
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = conn.prepareStatement("delete from '351_online' where S_ID=? and DATE='"+Date+"'");
			
			// set param
			myStmt.setInt(1, StudentId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			DAOUtils.close(myStmt);
		}
	}

	// update functionality
	public void updateStudent(Student student,Integer i) throws Exception
	{
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = conn.prepareStatement("update '351_online'"
					+ " set S_ID=?, Date=?, In_Time=?, Out_Time=?, Total_Time=?"
					+ " where S_ID=?");

			// set params
			myStmt.setInt(1, student.getId());
			myStmt.setString(2, student.getDate());
			myStmt.setString(3, student.getIn_time());
			myStmt.setString(4, student.getOut_time());
			myStmt.setString(5, student.getTotal_time());
			myStmt.setInt(6, i);

			// execute SQL
			myStmt.executeUpdate();
		}
		finally {
			DAOUtils.close(myStmt);
		}
	}

	// insert functionality
	public void addStudent(Student student) throws Exception 
	{
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = conn.prepareStatement("insert into '351_online'"
					+ " (S_ID, Date, In_Time, Out_Time, Total_Time)"
					+ " values (?, ?, ?, ?, ?)");

			// set params
			myStmt.setInt(1, student.getId());
			myStmt.setString(2, student.getDate());
			myStmt.setString(3, student.getIn_time());
			myStmt.setString(4, student.getOut_time());
			myStmt.setString(5, student.getTotal_time());

			// execute SQL
			myStmt.executeUpdate();	
		}
		finally {
			DAOUtils.close(myStmt);
		}

	}

	// search functionality
	public List<Student> searchStudents(String S_ID, String Date, String In_Time, String Out_Time, String Total_Time) throws Exception 
	{
		List<Student> list = new ArrayList<Student>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			if(S_ID.trim().length()!=0) {S_ID="S_ID"+S_ID;}
			else {S_ID="True";}

			if(Date.trim().length()!=0) {Date="Date"+Date.charAt(0)+"'"+Date.substring(1,Date.length())+"'";}
			else {Date="True";}

			if(In_Time.trim().length()!=0) {In_Time="In_Time"+In_Time.charAt(0)+"'"+In_Time.substring(1,In_Time.length())+"'";}
			else {In_Time="True";}

			if(Out_Time.trim().length()!=0) {Out_Time="Out_Time"+Out_Time.charAt(0)+"'"+Out_Time.substring(1,Out_Time.length())+"'";}
			else {Out_Time="True";}

			if(Total_Time.trim().length()!=0) {Total_Time="Total_Time"+Total_Time.charAt(0)+"'"+Total_Time.substring(1,Total_Time.length())+"'";}
			else {Total_Time="True";}

			myStmt = conn.prepareStatement("select * from '351_online' where "+S_ID+" AND "+Date+" AND "+In_Time+" AND "+Out_Time+" AND " +Total_Time);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Student stdn = convertRowToStudent(myRs);
				list.add(stdn);
			}

			return list;
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}

	public List<Student> getAllStudents() throws Exception 
	{
		List<Student> list = new ArrayList<Student>();

		Statement myStmt = null;
		ResultSet rset = null;

		try {
			myStmt = conn.createStatement();
			rset = myStmt.executeQuery("select * from '351_online' order by S_ID");
			while (rset.next()) {
				Student stdn = convertRowToStudent(rset);
				list.add(stdn);
			}

			return list;		
		}
		finally {
			DAOUtils.close(myStmt, rset);
		}
	}

	private Student convertRowToStudent(ResultSet rset) throws Exception 
	{
		Student stdn;
		stdn = new Student(rset.getInt("S_ID"),rset.getString("Date"),rset.getString("In_Time"),rset.getString("Out_Time"));
		return stdn;
	}
	public static void main(String[] args) throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		DAO dao = new DAO();
		//Student s = new Student(2,"2020-12-12","00:00:00","02:00:00");
		//dao.addStudent(s);
		System.out.println(dao.getAllStudents());
		System.out.println(dao.searchStudents("=4", "", "","",""));
	}
}
