package test.parseScrapeData;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.scraper.ParseHtml;
import logic.scraper.WebScraper;

public class TestWebScraperParsing {
	@Test
	public void testParseTime()
	{
		int[] timeArr;
		WebScraper scraper = new WebScraper();
		timeArr = scraper.parseTime("4:32");
		assertTrue(timeArr[0] == 4 && timeArr[1] == 32);
	}	
	@Test
	public void testGetCourseID()
	{
		WebScraper scraper = new WebScraper();
		String sample0 = "CPE 100 /0";
		String sample1 = "CPE 101 (1)";
		assertTrue(scraper.getCourseID(sample0).equals("CPE 100"));
		assertTrue(scraper.getCourseID(sample1).equals("CPE 101"));
	}
}
