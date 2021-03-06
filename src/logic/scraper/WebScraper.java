package logic.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
			final HtmlPage page = webClient.getPage("http://schedules.calpoly.edu/subject_" + dept + "_2198.htm");
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
			return Collections.emptyList();
		}
		
		return getCourseList(ParseHtml.parselines(combinedCSV));
		
	}
	
	public List<Course> getCourseList(List<ArrayList<String>> classList)
	{
		TimeBlock[] times;
		CourseData data;
		CourseData data1;
		Course course;
		Course course1;
		ArrayList<Course> courseList;
		ArrayList<Section> sectList;
		String id;
		int i;
		int j;
		int spots;

		courseList = new ArrayList<>();
		i = 1;
		while (i < classList.size())
		{
			data = new CourseData(classList.get(i));
			course = new Course(getCourseID(data.getCourse()));
			sectList = new ArrayList<>();
			id = data.getCourse();
			j = i;
			while (j < classList.size() && id.equals(data.getCourse()))
			{
				data1 = new CourseData(classList.get(j));
				course1 = new Course(getCourseID(data1.getCourse()));
				id = data1.getCourse();
				spots = data1.getLcap() - data1.getEnrl();
				if (spots < 0)
					spots = 0;
				times = getTimeBlock(data1);
				sectList.add(new Section(data1.getSect(), times, course1.getName(), spots));
				j ++;
			}
			i = j + 1;
			course.setSections(sectList);
			courseList.add(course);
		}

		return courseList;
	}

	public TimeBlock[] getTimeBlock(CourseData data1)
	{
		TimeBlock[] times;
		Time startTime;
		Time endTime;
		int[] startTimeList;
		int[] endTimeList;
		int k;
		int l;

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
		return times;
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