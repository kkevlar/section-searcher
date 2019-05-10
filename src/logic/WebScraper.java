package logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

//import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.gargoylesoftware.htmlunit.html.HtmlTable;
//import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
//import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class WebScraper 
{
//	ArrayList<ArrayList<String>> fun;
//	
//	public static void start() 
//	{
//		WebScraper scraper = new WebScraper();
//		try {
//			System.out.println(scraper.scrapeCoursesByDept("CPE"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public List<Course> scrapeCoursesByDept(String dept)
//	{
//		String combinedCSV = "";
//		
//		try (final WebClient webClient = new WebClient()) {
//	        final HtmlPage page = webClient.getPage("http://schedules.calpoly.edu/subject_" + dept + "_curr.htm");
//	        final HtmlTable table = page.getHtmlElementById("listing");
//	        for (final HtmlTableRow row : table.getRows()) {
//	            combinedCSV += ("\n");
//	            for (final HtmlTableCell cell : row.getCells()) {
//	            	 combinedCSV += ( cell.asText() +",");
//	            }
//	        }
//	    } catch (FailingHttpStatusCodeException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if(combinedCSV.length() > 1)
//		{
//			combinedCSV = combinedCSV.substring(1);
//		}
//		else
//		{
//			return null;
//		}
//		
//		return get_Course_List(ParseHtml.parselines(combinedCSV));
//		
//	}
//	
//	public List<Course> get_Course_List(ArrayList<ArrayList<String>> class_list)
//	{
//		ArrayList<Course> course_list;
//		ArrayList<Section> sect_list;
//		String id;
//		int i, j;
//
//		course_list = new ArrayList<Course>();
//		i = 1;
//		while (i < class_list.size())
//		{
//			CourseData data = new CourseData(class_list.get(i));
//			Course course = new Course(CourseData.course);
//			sect_list = new ArrayList<Section>();
//			id = data.course.substring(0, 7); //screws up with the 8 character P___ courses
//			j = i;
//			while (j < class_list.size() && id.equals(data.course.substring(0, 7)))
//			{
//				sect_list.add(new Section(CourseData.sect, new TimeBlock[2], course, CourseData.lcap - CourseData.enrl));
//				j ++;
//			}
//			i = j + 1;
//			course.setSections(sect_list);
//			course_list.add(course);
//		}
//		
//		System.out.println(course_list);
//
//		return course_list;
//	}
	
}
