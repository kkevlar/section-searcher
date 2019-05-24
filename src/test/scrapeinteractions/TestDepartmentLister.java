package test.scrapeinteractions;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.scraper.DepartmentLister;
import logic.scraper.WebScraper;

public class TestDepartmentLister 
{
	@Test
	public void testDeptLister()
	{
		DepartmentLister lister = new DepartmentLister();
		List<String> departmentList = lister.getDepartmentList();
		assertTrue(departmentList.contains("CSC"));
	}	
	@Test
	public void testDeptListThenScrape()
	{
		ArrayList<Course> courses = new ArrayList<>();
		DepartmentLister lister = new DepartmentLister();
		List<String> departments = lister.getDepartmentList();
		for(String department : departments)
		{
			WebScraper scraper = new WebScraper();
			List<Course> scrapedCourses = scraper.scrapeCoursesByDept(department);
			for(Course course : scrapedCourses)
			{
				courses.add(course);
			}
		}
		assertTrue(courses.contains(new Course("MATH 244")));
	}
}
