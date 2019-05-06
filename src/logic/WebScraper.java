package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class WebScraper 
{
	public static void start() 
	{
		WebScraper scraper = new WebScraper();
		try {
			scraper.scrapeCoursesByDept("CPE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Course> scrapeCoursesByDept(String dept)
	{
		String combinedCSV = "";
		
		try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://schedules.calpoly.edu/subject_" + dept + "_curr.htm");
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
		
		
	}
}
