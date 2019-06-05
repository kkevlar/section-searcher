package test.GuiImplementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.entity.Category;
import logic.entity.Course;
import logic.entity.Section;
import logic.entity.TimeBlock;
import logic.gui.Gui;

public class TestCategoryGui {

	@Test
	public void testSetSelectedCategory() {
		Category testCat  = new Category();
		Gui.setSelectedCategory(testCat);
		assertTrue(Gui.selectedCategory.getValue() == testCat);
	}
	
	@Test
	public void testSelectedCategoryContains() {
		Category testCat  = new Category();
		Gui.setSelectedCategory(testCat);
		List<Section> testSects = new ArrayList<>();
		testSects.add(new Section("test",new TimeBlock[7], "testcourse", 10));
		Course testCourse = new Course("name", "dept", testSects);
		testCat  = new Category();
		testCat.addCourse(testCourse);
		Gui.setSelectedCategory(testCat);
		assertTrue(Gui.selectedCategoryContains(testCourse));
	}

}
