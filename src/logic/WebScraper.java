package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
			scraper.scrapeDept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrapeDept() throws Exception 
	{
	    try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://schedules.calpoly.edu/subject_CSC_curr.htm");
	        final HtmlTable table = page.getHtmlElementById("listing");
	        for (final HtmlTableRow row : table.getRows()) {
	            System.out.print("\n");
	            for (final HtmlTableCell cell : row.getCells()) {
	                System.out.print("\"" + cell.asText() + "\"" +",");
	            }
	        }
	    }
	}


	private void aaascrapeDept() 
	{
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;

		try 
		{
			url = new URL("http://schedules.calpoly.edu/subject_CPE_curr.htm");
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));

			
		} 
		catch (MalformedURLException mue) 
		{
			mue.printStackTrace();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (is != null) is.close();
			} 
			catch (IOException ioe) 
			{
				
			}
		}
	}
}
