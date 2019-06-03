package test.categoryPlanSectionObjCreation;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import logic.entity.Category;
import logic.entity.Course;
import logic.entity.Plan;
import logic.entity.Section;
public class TestPlanCreation {
	@Test
	public void testPlanNoParameters() {
		Plan p = new Plan();
		assertEquals("", p.getName());
		assertEquals(0, p.getID());
		assertEquals(null, p.getCategories());
	}
	@Test
	public void testPlanCreation() {
		ArrayList<Category> categories = new ArrayList<>();
		String expectedToString = "Plan(name=plan schedule, id=0):\n" + 
				"   Category(name=Major, isAvailable=true):\n      Course(name=C0, department=CSC)\n";
		
		//Category creation
		List<Course> inputCourses = new ArrayList<>();
		Category major = new Category("Major",inputCourses,true);
		List<Section> sections = new ArrayList<>();
		Section section = new Section("S0", null);
		sections.add(section);
		Course course = new Course("C0","CSC",sections);
		inputCourses.add(course);
		categories.add(major); 
		
		
		Plan schedule = new Plan("plan schedule",0,categories);
		assertEquals(expectedToString, schedule.toString());
	}
	@Test
	public void testGettersAndSetters() {
		ArrayList<Category> categories = new ArrayList<>();
		String expectedCatToString = "[Category(name=Major, isAvailable=true):\n" + 
				"      Course(name=C0, department=CSC)\n" + 
				", Category(name=Major, isAvailable=true):\n      Course(name=C0, department=CSC)\n]";
		
		String testNameInput = "testName";
		int testIDInput = 0;
		//Category creation
		List<Course> inputCourses = new ArrayList<>();
		Category major = new Category("Major",inputCourses,true);
		List<Section> sections = new ArrayList<>();
		Section section = new Section("S0", null);
		sections.add(section);
		Course course = new Course("C0","CSC",sections);
		inputCourses.add(course);
		categories.add(major); 
		//Plan creation
		Plan schedule = new Plan("plan schedule",0,categories);
		schedule.setName(testNameInput);
		assertEquals(testNameInput, schedule.getName());
		schedule.setID(testIDInput);
		assertEquals(testIDInput, schedule.getID());
		schedule.setValid(false);
		assertEquals(false, schedule.isValid());
		schedule.addCategory(major);
		assertEquals(expectedCatToString, schedule.getCategories().toString());
		
	}
}
