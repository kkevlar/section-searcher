package test.createplancategorysections;



import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import logic.entity.Category;
import logic.entity.Course;
import logic.entity.Section;


public class TestCategoryCreation {
	@Test
	public void testCategoryWithMultipleCourses() {
		List<Course> inputCourses;
		String expectedToString = "Category(name=Major, isAvailable=true):\n" + 
				"      Course(name=C0, department=CSC)\n" + 
				"      Course(name=C1, department=CSC)\n" + 
				"      Course(name=C2, department=CSC)\n" + 
				"      Course(name=C3, department=CSC)\n" + 
				"      Course(name=C4, department=CSC)\n";

		inputCourses = makeCourseList();
		Category major = new Category("Major",inputCourses,true);
		
		assertEquals(expectedToString,major.toString());
		
	}
	@Test
	public void testCategoryNoParameters() {
		Category cat = new Category();
		Category cat2 = new Category();
		assertEquals("",cat.getName());
		assertEquals(false,cat.isAvailable());
		assertEquals(true,cat.equals(cat2));
		assertEquals(false,cat.equals(null));
		
	}
	
	@Test
	public void testGettersAndSetters() {
		List<Course> inputCourses;
		
		String testName = "testName";
		Course testCourse = new Course();
		
		inputCourses = makeCourseList();
		Category major = new Category("Major",inputCourses,true);

		major.setName(testName);
		assertEquals(testName,major.getName());
		major.setAvailable(false);
		assertEquals(false,major.isAvailable());
		assertEquals(inputCourses,major.getCourses());
		major.addCourse(testCourse);
		assertEquals(inputCourses,major.getCourses());
		major.removeCourse(testCourse);
		assertEquals(inputCourses,major.getCourses());
		major.setCourses(null);
		assertEquals(null,major.getCourses());
		
	}
	List<Course> makeCourseList(){
		List<Course> inputCourses = new ArrayList<>();
		for(int c = 0; c < 5; c++) {
			List<Section> sections = new ArrayList<>();
			for(int s = 1; s <=5; s++) {
				Section section = new Section("S"+c+s, null);
				sections.add(section);
			}
			Course course = new Course("C"+c,"CSC",sections);
			inputCourses.add(course);
		}
		return inputCourses;
	}
}
