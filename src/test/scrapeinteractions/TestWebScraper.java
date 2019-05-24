package test.scrapeinteractions;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.scraper.WebScraper;

public class TestWebScraper 
{
	@Test
	public void testScrapeHasClasses()
	{
		WebScraper scraper = new WebScraper();
		List<Course> course = scraper.scrapeCoursesByDept("CPE");
		assertTrue(course.size() > 1);
	}	
	@Test
	public void testScrapeHasCPE202()
	{
		WebScraper scraper = new WebScraper();
		List<Course> course = scraper.scrapeCoursesByDept("CPE");
		assertTrue(course.contains(new Course("CPE 202")));
	}
}
