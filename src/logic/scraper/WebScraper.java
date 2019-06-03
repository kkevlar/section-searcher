package logic.scraper;

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

import logic.entity.Course;
import logic.entity.CourseData;
import logic.entity.Section;
import logic.entity.Time;
import logic.entity.TimeBlock;

public class WebScraper 
{
	public List<Course> scrapeCoursesByDept(String dept)
	{
		String combinedCSV = "";
		StringBuilder bld = new StringBuilder();
		
		try (final WebClient webClient = new WebClient()) {
			PrintStream oldErr = System.err;
			PrintStream newErr = new PrintStream(new ByteArrayOutputStream());
			System.setErr(newErr);
			final HtmlPage page = webClient.getPage("http://schedules.calpoly.edu/subject_" + dept + "_2198.htm");
			System.setErr(oldErr);
			final HtmlTable table = page.getHtmlElementById("listing");
	        for (final HtmlTableRow row : table.getRows()) {
	            bld.append("\n");
	            for (final HtmlTableCell cell : row.getCells()) {
	            	bld.append(cell.asText() +",");
	            }
	            combinedCSV = bld.toString();
	        }
	    } 
		catch (FailingHttpStatusCodeException|IOException e) 
		{
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
	
	public List<Course> get_Course_List(List<ArrayList<String>> class_list)
	{
		TimeBlock[] times;
		Time startTime;
		Time endTime;
		CourseData data;
		CourseData data1;
		Course course;
		Course course1;
		ArrayList<Course> courseList;
		ArrayList<Section> sectList;
		String id;
		int[] startTimeList;
		int[] endTimeList;
		int i;
		int j;
		int k;
		int l;
		int spots;

		courseList = new ArrayList<>();
		i = 1;
		while (i < class_list.size())
		{
			data = new CourseData(class_list.get(i));
			course = new Course(getCourseID(data.getCourse()));
			sectList = new ArrayList<>();
			id = data.getCourse();
			j = i;
			while (j < class_list.size() && id.equals(data.getCourse()))
			{
				data1 = new CourseData(class_list.get(j));
				course1 = new Course(getCourseID(data1.getCourse()));
				id = data1.getCourse();
				spots = data1.getLcap() - data1.getEnrl();
				if (spots < 0)
					spots = 0;
				// set up time block
				times = new TimeBlock[7];
				startTimeList = parseTime(data1.getStart());
				endTimeList = parseTime(data1.getEnd());
				for (k = 0; k < data1.getDays().length(); k ++)
				{
					if (data1.getDays().charAt(k) == 'M') {l = 1;}
					else if (data1.getDays().charAt(k) == 'T') {l = 2;}
					else if (data1.getDays().charAt(k) == 'W') {l = 3;}
					else if (data1.getDays().charAt(k) == 'R') {l = 4;}
					else if (data1.getDays().charAt(k) == 'F') {l = 5;}
					else {l = 0;} // bad news bears
					startTime = new Time(startTimeList[0], startTimeList[1]);
					endTime = new Time(endTimeList[0], endTimeList[1]);
					times[l] = new TimeBlock(startTime, endTime);
				}
				sectList.add(new Section(data1.getSect(), times, course1.getName(), spots));
				j ++;
			}
			i = j + 1;
			course.setSections(sectList);
			courseList.add(course);
		}

		return courseList;
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