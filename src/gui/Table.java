package gui;

import java.util.List;
import DAO.Student;

import javax.swing.table.AbstractTableModel;

class Table extends AbstractTableModel {

	/**
	 * Java table which holds our database information
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private static final int S_ID_COL = 0;
	private static final int Date_COL = 1;
	private static final int In_Time_COL = 2;
	private static final int Out_Time_COL = 3;
	private static final int Total_Time_COL = 4;
	
	private String[] columnNames = { "STD ID", "Date", "In Time","Out Time", "Total Time"};
	private List<Student> students;

	public Table(List<Student> theStudents) {
		students = theStudents;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Student tempStudent = students.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempStudent;
		case S_ID_COL:
			return tempStudent.getId();
		case Date_COL:
			return tempStudent.getDate();
		case In_Time_COL:
			return tempStudent.getIn_time();
		case Out_Time_COL:
			return tempStudent.getOut_time();
		case Total_Time_COL:
			return tempStudent.getTotal_time();
		default:
			return tempStudent.getId();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

