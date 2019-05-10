package logic;

import java.util.ArrayList;

public class CourseData
{
	public static String course, sect, id, type, ge, req, days, start, end, instructor, location, ics;
	public static int lcap, ecap, enrl, wait, drop;

	public CourseData(ArrayList<String> data)
	{
		this.course = data.get(0);
		this.sect = data.get(1);
		this.id = data.get(2);
		this.type = data.get(3);
		this.ge = data.get(4);
		this.req = data.get(5);
		this.days = data.get(6);
		this.start = data.get(7);
		this.end = data.get(8);
		this.instructor = data.get(9);
		this.location = data.get(10);
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
		try
		{
			if (data.get(14).length() != 0)
				this.wait = Integer.parseInt(data.get(14));
			else
				this.wait = -1;
		}
		catch (NumberFormatException e)
		{
			this.wait = -1;
		}
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
		{
			this.drop = -1;
		}		
		if (data.size() > 16)
		{
			this.ics = data.get(16);
		}
		else
		{
			this.ics = "";
		}
	}

	public String str_CourseData()
	{
		String ret_str;
		ret_str = "Course: " + course + "\n";
		ret_str += "Section: " + sect + "\n";
		ret_str += "ID: " + id + "\n";
		ret_str += "Type: " + type + "\n";
		ret_str += "GE: " + ge + "\n";
		ret_str += "Requirement: " + req + "\n";
		ret_str += "Days: " + days + "\n";
		ret_str += "Start: " + start + "\n";
		ret_str += "End: " + end + "\n";
		ret_str += "Instructor: " + instructor + "\n";
		ret_str += "Location: " + location + "\n";
		ret_str += "lcap: " + String.valueOf(lcap) + "\n";
		ret_str += "ecap: " + String.valueOf(ecap) + "\n";
		ret_str += "enrl: " + String.valueOf(enrl) + "\n";
		ret_str += "wait: " + String.valueOf(wait) + "\n";
		ret_str += "drop: " + String.valueOf(drop) + "\n";
		ret_str += "ICS: " + ics + "\n";
		return ret_str;
	}
}