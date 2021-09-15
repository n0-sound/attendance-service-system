package DAO;
import java.sql.Date;
import java.sql.Time;

public class Student {
	private Integer id;
	private Date date;
	private Time in_time;
	private Time out_time;
	private Time total_time;
	
	public Student(int id, String date,String in_time,String out_time)
	{
		try 
		{
			if (Time.valueOf(in_time).before(Time.valueOf(out_time))) 
			{
				this.id=id;
				this.date=Date.valueOf(date);
				this.in_time=Time.valueOf(in_time);
				this.out_time=Time.valueOf(out_time);
				this.total_time=new Time(this.out_time.getTime()-this.in_time.getTime());
			}
			else
			{
				throw new IllegalArgumentException("In Time greater than or equal Out Time");
			}
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Invalid Arguments");
		}
	}
	
	public void setId(Integer id) {this.id=id;}
	public void setDate(Date date) {this.date=date;}
	public void setIn_time(Time in_time) {this.in_time=in_time;this.total_time=new Time(this.out_time.getTime()-this.in_time.getTime());}
	public void setOut_time(Time out_time) {this.out_time=out_time;this.total_time=new Time(this.out_time.getTime()-this.in_time.getTime());}
	
	public Integer getId(){return id;};
	public String getDate(){return date.toString();};
	public String getIn_time(){return in_time.toString();};
	public String getOut_time(){return out_time.toString();};
	public String getTotal_time(){return total_time.toString();};
	
	@Override
	public String toString() {
		return String.format("Student [id=%s, date=%s, in_time=%s, out_time=%s, total_time=%s]",id, date, in_time, out_time, total_time);
	}
}
