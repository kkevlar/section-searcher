package logic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class WebScraper 
{
	public List<Course> scrapeCoursesByDept(String dept)
	{
		String combinedCSV = "";
		
		try (final WebClient webClient = new WebClient()) {
			PrintStream oldErr = System.err;
			PrintStream newErr = new PrintStream(new ByteArrayOutputStream());
			System.setErr(newErr);
			final HtmlPage page = webClient.getPage("http://schedules.calpoly.edu/subject_" + dept + "_2198.htm");
			System.setErr(oldErr);
			final HtmlTable table = page.getHtmlElementById("listing");
	        for (final HtmlTableRow row : table.getRows()) {
	            combinedCSV += ("\n");
	            for (final HtmlTableCell cell : row.getCells()) {
	            	 combinedCSV += ( cell.asText() +",");
	            }
	        }
	    } catch (FailingHttpStatusCodeException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(combinedCSV.length() > 1)
		{
			combinedCSV = combinedCSV.substring(1);
		}
		else
		{
			return null;
		}
		
		return get_Course_List(ParseHtml.parselines(combinedCSV));
		
	}
	
	public List<Course> get_Course_List(ArrayList<ArrayList<String>> class_list)
	{
		TimeBlock[] times;
		Time startTime, endTime;
		CourseData data, data1;
		Course course, course1;
		ArrayList<Course> course_list;
		ArrayList<Section> sect_list;
		String id;
		int[] startTimeList, endTimeList;
		int i, j, k, l, spots;

		course_list = new ArrayList<Course>();
		i = 1;
		while (i < class_list.size())
		{
			data = new CourseData(class_list.get(i));
			course = new Course(getCourseID(data.course));
			sect_list = new ArrayList<Section>();
			id = data.course; //screws up with the departments w/ 4 chars
			j = i;
			while (j < class_list.size() && id.equals(data.course))
			{
				data1 = new CourseData(class_list.get(j));
				course1 = new Course(getCourseID(data1.course));
				id = data1.course;
				spots = data1.lcap - data1.enrl;
				if (spots < 0)
					spots = 0;
				// set up time block
				times = new TimeBlock[7];
				startTimeList = parseTime(data1.start);
				endTimeList = parseTime(data1.end);
				for (k = 0; k < data1.days.length(); k ++)
				{
					l = 0;
					if (data1.days.charAt(k) == 'M') {l = 1;}
					else if (data1.days.charAt(k) == 'T') {l = 2;}
					else if (data1.days.charAt(k) == 'W') {l = 3;}
					else if (data1.days.charAt(k) == 'R') {l = 4;}
					else if (data1.days.charAt(k) == 'F') {l = 5;}
					else {l = 0;} // bad news bears
					startTime = new Time(startTimeList[0], startTimeList[1]);
					endTime = new Time(endTimeList[0], endTimeList[1]);
					times[l] = new TimeBlock(startTime, endTime);
				}
				sect_list.add(new Section(data1.sect, times, course1.getName(), spots));
				j ++;
			}
			i = j + 1;
			course.setSections(sect_list);
			course_list.add(course);
		}

		return course_list;
	}
	
	public String getCourseID(String name)
	{
		String [] arr;
		
		arr = name.split("\\s+");
		return arr[0] + " " + arr[1];
	}
	
	public int[] parseTime(String input)
	{
		String timeStr;
		String[] timeArr;
		int[] retArr;
		
		timeStr = input.split("\\s+")[0];
		timeArr = timeStr.split(":");
		retArr = new int[2];
		retArr[0] = Integer.parseInt(timeArr[0]);
		retArr[1] = Integer.parseInt(timeArr[1]);
		return retArr;
		
	}
	
}