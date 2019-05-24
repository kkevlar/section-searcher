//author: Brandon Lyday

package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import logic.ClassDB;
import logic.Course;
import logic.Section;

public class TestClassDB {
	
	@Test
	public void testGetSectionsNoParam() {
		//instantiating a test ClassDB object
		List<Course> courses = new ArrayList<>();	
		
		for(int c = 0; c < 5; c++) {
			Course course = new Course("C"+c);
			for(int s = 1; s <=5; s++) {
				Section section = new Section("S"+c+s, null);
				course.addSection(section);
			}
			courses.add(course);
		}
		
		ClassDB db = new ClassDB(courses);
				
		//actual sections
		List<Section> actualSections = db.getAllSections();
		
		//expected sections
		List<Section> expectedSections = new ArrayList<>();
		for(int c = 0; c < 5; c++) {
			for(int s = 1; s <= 5; s++) {
				Section section = new Section("S"+c+s, null);
				expectedSections.add(section);
			}
		}
		
		//compare expected and actual
		assertEquals(expectedSections.size(), actualSections.size());
		
		for(int i = 0; i < expectedSections.size(); i++) {
			assertEquals(true, expectedSections.get(i).equals(actualSections.get(i)));
		}
	}
	
	@Test
	public void testFilterDepartment1() {
		//3 courses: two CSC, one PSY
		//Should filter out PSY class
		
		Course c1 = new Course("Algorithms");
		c1.setDepartment("CSC");
		
		Course c2 = new Course("Systems Programming");
		c2.setDepartment("CSC");
		
		Course c3 = new Course("Intro Psych");
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
