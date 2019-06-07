package test.guiImplementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.entity.CheckedSection;
import logic.entity.Course;
import logic.entity.Section;
import logic.entity.TimeBlock;
import logic.gui.Gui;
import logic.scraper.ClassDB;

public class TestCheckedSections {

	public void testCompleteCheckedSectionCreation() {
		CheckedSection sect = new CheckedSection("test",new TimeBlock[7], "testcourse", 10);
		assertFalse(sect.getChecked());
		sect.setChecked(true);
		assertTrue(sect.getChecked());
	}
	
	@Test
	public void testLoopingCheckedSections() {
		Gui.resetCourses();
		assertEquals(0, Gui.sections.size());
		List<Course> courses = new ArrayList<>();
		List<Section> testSects = new ArrayList<>();
		testSects.add(new Section("test",new TimeBlock[7], "testcourse", 10));
		courses.add(new Course("name", "dept", testSects));
		Gui.courses = courses;
		Gui.resetCourses();
		assertEquals(1, Gui.sections.size());
		testSects.add(new Section("test",new TimeBlock[7], "testcourse2", 10));
		courses = new ArrayList<>();
		courses.add(new Course("name", "dept", testSects));
		Gui.courses = courses;
		Gui.resetCourses();
		assertEquals(2, Gui.sections.size());
		
		ClassDB classDb = ClassDB.getInstance();
		classDb.scrapeAll();
		courses = classDb.getCourses();
		Gui.courses = courses;
		Gui.resetCourses();
		assertTrue(Gui.sections.size() > 10);
		
	}

}
