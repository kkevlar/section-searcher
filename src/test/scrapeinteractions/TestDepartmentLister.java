package test.scrapeinteractions;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.Course;
import logic.DepartmentLister;
import logic.WebScraper;

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
		ArrayList<Course> courses = new ArrayList<Course>();
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
