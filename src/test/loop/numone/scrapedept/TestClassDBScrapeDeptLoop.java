package test.loop.numone.scrapedept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.scraper.ClassDB;
import logic.scraper.DepartmentLister;

public class TestClassDBScrapeDeptLoop 
{
	@Test
	public void testClassDBScrapeDeptLoopZero()
	{
		List<String> departments = new ArrayList<>();
		
		ClassDB classDb = ClassDB.getInstance();
		classDb.getCourses().removeIf(s->true);
		
		for(String department : departments)
		{
			classDb.scrapeAndAddDepartment(department);
		}
		
		assertEquals(0, classDb.getCourses().size());
	}
	@Test
	public void testClassDBScrapeDeptLoopOne()
	{
		List<String> departments = new ArrayList<>();
		
		departments.add("CSC");
		
		ClassDB classDb = ClassDB.getInstance();
		classDb.getCourses().removeIf(s->true);
		
		for(String department : departments)
		{
			classDb.scrapeAndAddDepartment(department);
		}
		
		boolean hasAtLeastOneCourse = classDb.getCourses().size() > 0;
		boolean allCoursesCSC;
		int numCscCourses = 0;
		for( Course c: classDb.getCourses())
		{
			if(c.getDepartment().equals("CSC"))
				numCscCourses++;
			
		}
		allCoursesCSC = numCscCourses == classDb.getCourses().size();
		
		assertTrue(hasAtLeastOneCourse && allCoursesCSC);
	}
	@Test
	public void testClassDBScrapeDeptLoopTwo()
	{
		List<String> departments = new ArrayList<>();
		
		departments.add("CSC");
		departments.add("MATH");
		
		ClassDB classDb = ClassDB.getInstance();
		classDb.getCourses().removeIf(s->true);
		
		for(String department : departments)
		{
			classDb.scrapeAndAddDepartment(department);
		}
		
		boolean hasAtLeastOneCourse = classDb.getCourses().size() > 0;
		boolean allCoursesCSCorMath = classDb.getCourses().stream()
				.filter(c -> c.getDepartment().equals("CSC") || c.getDepartment().equals("MATH"))
				.count() == classDb.getCourses().size();
		boolean hasCSCcourse = classDb.getCourses().stream()
				.filter(c -> c.getDepartment().equals("CSC"))
				.count() > 0;
		boolean hasMathcourse = classDb.getCourses().stream()
				.filter(c -> c.getDepartment().equals("MATH"))
				.count() > 0;


		assertTrue(hasAtLeastOneCourse && allCoursesCSCorMath && hasCSCcourse && hasMathcourse);
	}	
	@Test
	public void testClassDBScrapeDeptLoopTypical()
	{
		DepartmentLister lister = new DepartmentLister();
		List<String> departments = lister.getDepartmentList();
		
		ClassDB classDb = ClassDB.getInstance();
		classDb.getCourses().removeIf(s->true);
		
		for(String department : departments)
		{
			classDb.scrapeAndAddDepartment(department);
		}
		
		boolean hasAtLeastOneCourse = classDb.getCourses().size() > 0;
		boolean hasCSCcourse = classDb.getCourses().stream()
				.filter(c -> c.getDepartment().equals("CSC"))
				.count() > 0;
		boolean hasMathcourse = classDb.getCourses().stream()
				.filter(c -> c.getDepartment().equals("MATH"))
				.count() > 0;


		assertTrue(hasAtLeastOneCourse && hasCSCcourse && hasMathcourse);
	}

	
}
