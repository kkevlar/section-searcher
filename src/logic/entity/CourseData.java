package logic.entity;

import java.util.ArrayList;

public class CourseData
{
	public String course;
	public String sect; 
	public String id;
	public String type;
	public String ge;
	public String req; 
	public String days;
	public String start; 
	public String end;
	public String instructor;
	public String location;
	public String ics;
	public int lcap, ecap, enrl, waitList, drop;

	public CourseData(ArrayList<String> data)
	{
		if (data.size() > 0)
			this.course = data.get(0);
		else
			this.course = "";
		if (data.size() > 1)
			this.sect = data.get(1);
		else
			this.sect = "";
		if (data.size() > 2)
			this.id = data.get(2);
		else
			this.id = "";
		if (data.size() > 3)
			this.type = data.get(3);
		else
			this.type = "";
		if (data.size() > 4)
			this.ge = data.get(4);
		else
			this.ge = "";
		if (data.size() > 5)
			this.req = data.get(5);
		else
			this.req = "";
		if (data.size() > 6)
			this.days = data.get(6);
		else
			this.days = "";
		if (data.size() > 7)
			this.start = data.get(7);
		else
			this.start = "";
		if (data.size() > 8)
			this.end = data.get(8);
		else
			this.end = "";
		if (data.size() > 9)
			this.instructor = data.get(9);
		else
			this.instructor = "";
		if (data.size() > 10)
			this.location = data.get(10);
		else
			this.location = "";
		if (data.size() > 11)
		{
			try
			{
				if (data.get(11).length() != 0)
					this.lcap = Integer.parseInt(data.get(11));
				else
					this.lcap = -1;
			}
			catch (NumberFormatException e)
			{
				this.lcap = -1;
			}
		}
		else
			this.lcap = -1;
		if (data.size() > 12)
		{
			try
			{
				if (data.get(12).length() != 0)
					this.ecap = Integer.parseInt(data.get(12));
				else
					this.ecap = -1;
			}
			catch (NumberFormatException e)
			{
				this.ecap = -1;
			}
		}
		else
			this.ecap = -1;
		if (data.size() > 13)
		{
			try
			{
				if (data.get(13).length() != 0)
					this.enrl = Integer.parseInt(data.get(13));
				else
					this.enrl = -1;
			}
			catch (NumberFormatException e)
			{
				this.enrl = -1;
			}
		}
		else
			this.enrl = -1;
		if (data.size() > 14)
		{
			try
			{
				if (data.get(14).length() != 0)
					this.waitList = Integer.parseInt(data.get(14));
				else
					this.waitList = -1;
			}
			catch (NumberFormatException e)
			{
				this.waitList = -1;
			}
		}
		else
			this.waitList = -1;
		if (data.size() > 15)
		{
			try
			{
				if (data.get(15).length() != 0)
					this.drop = Integer.parseInt(data.get(15));
				else
					this.drop = -1;
			}
			catch (NumberFormatException e)
			{
				this.drop = -1;
			}
		}
		else
			this.drop = -1;		
		if (data.size() > 16)
			this.ics = data.get(16);
		else
			this.ics = "";
	}

	public String str_CourseData()
	{
		String ret_str;
		ret_str = "Course: " + this.course + "\n";
		ret_str += "Section: " + this.sect + "\n";
		ret_str += "ID: " + this.id + "\n";
		ret_str += "Type: " + this.type + "\n";
		ret_str += "GE: " + this.ge + "\n";
		ret_str += "Requirement: " + this.req + "\n";
		ret_str += "Days: " + this.days + "\n";
		ret_str += "Start: " + this.start + "\n";
		ret_str += "End: " + this.end + "\n";
		ret_str += "Instructor: " + this.instructor + "\n";
		ret_str += "Location: " + this.location + "\n";
		ret_str += "lcap: " + String.valueOf(this.lcap) + "\n";
		ret_str += "ecap: " + String.valueOf(this.ecap) + "\n";
		ret_str += "enrl: " + String.valueOf(this.enrl) + "\n";
		ret_str += "waitList: " + String.valueOf(this.waitList) + "\n";
		ret_str += "drop: " + String.valueOf(this.drop) + "\n";
		ret_str += "ICS: " + this.ics + "\n";
		return ret_str;
	}
}