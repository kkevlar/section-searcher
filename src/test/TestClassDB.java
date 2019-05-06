package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import logic.ClassDB;
import logic.Course;

public class TestClassDB {
	@Test
	public void testFilterDepartment1() {
		//3 courses: two CSC, one PSY
		//Should filter out PSY class
		
		Course c1 = new Course("Algorithms", 0);
		c1.setDepartment("CSC");
		
		Course c2 = new Course("Systems Programming", 0);
		c2.setDepartment("CSC");
		
		Course c3 = new Course("Intro Psych", 0);
		c3.setDepartment("PSY");
				
		ClassDB db = new ClassDB();
		db.addCourse(c1);
		db.addCourse(c2);
		db.addCourse(c3);
		
		List<Course> onlyCSC = ClassDB.filterDepartment(db.getCourses(), "CSC");
		
		assertEquals(2, onlyCSC.size());
		
		assertEquals("CSC", onlyCSC.get(0).getDepartment());
		assertEquals("CSC", onlyCSC.get(1).getDepartment());		
	}
	
	@Test
	public void testFilterWaitListCourses() {
		
	}
	
	@Test
	public void testFilterWaitListSections() {
		
	}
	
	@Test
	public void testSortWaitListSections() {
		
	}
	
	@Test
	public void testSortWaitListCourses() {
		
	}
}
