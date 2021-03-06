//author: Brandon Lyday

package test.timeclassdb;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.entity.Section;
import logic.scraper.ClassDB;

public class TestClassDB {
	
	@Test
	public void testGetSectionsNoParam() {
		//instantiating a test ClassDB object
		
		ClassDB db = ClassDB.getInstance();
		
		for(int c = 0; c < 5; c++) {
			Course course = new Course("C"+c);
			for(int s = 1; s <=5; s++) {
				Section section = new Section("S"+c+s, null);
				course.addSection(section);
			}
			db.addCourse(course);
		}
		
				
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
				
		ClassDB db = ClassDB.getInstance();
		db.addCourse(c1);
		db.addCourse(c2);
		db.addCourse(c3);
		
		List<Course> onlyCSC = ClassDB.filterDepartment(db.getCourses(), "CSC");
		
		assertEquals(2, onlyCSC.size());
		
		assertEquals("CSC", onlyCSC.get(0).getDepartment());
		assertEquals("CSC", onlyCSC.get(1).getDepartment());		
	}
}
