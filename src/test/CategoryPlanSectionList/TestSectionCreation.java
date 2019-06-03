package test.CategoryPlanSectionList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import logic.entity.Category;
import logic.entity.Course;
import logic.entity.Section;
import logic.entity.Time;
import logic.entity.TimeBlock;

public class TestSectionCreation {
	@Test
	public void testCategoryWithMultipleCourses() {
		List<Course> inputCourses = new ArrayList<Course>();
		Category major = new Category("Major",inputCourses,true);
		String expectedToString = "Category(name=Major, isAvailable=true):\n" + 
				"      Course(name=C0, department=CSC)\n" + 
				"      Course(name=C1, department=CSC)\n" + 
				"      Course(name=C2, department=CSC)\n" + 
				"      Course(name=C3, department=CSC)\n" + 
				"      Course(name=C4, department=CSC)\n";
		
		for(int c = 0; c < 5; c++) {
			List<Section> sections = new ArrayList<Section>();
			for(int s = 1; s <=5; s++) {
				Section section = new Section("S"+c+s, null);
				sections.add(section);
			}
			Course course = new Course("C"+c,"CSC",sections);
			inputCourses.add(course);
		}
		
		assertEquals(expectedToString,major.toString());
		
	}
	@Test
	public void testBadSection() {
		TimeBlock times[] = new TimeBlock[10];
		System.out.println(times);
		assertThrows(IllegalArgumentException.class,() -> {new Section("",times);} );
	}
	@Test
	public void testCompleteSectionCreation() {
		Section section,newSectionCompare,badSection;
		//setup necessary variables
		TimeBlock times[] = new TimeBlock[7]; 
		TimeBlock newTimes[] = new TimeBlock[7]; 
		Time time9 =new Time(9,10);
		Time time10 =new Time(10,10);
		Time time11 =new Time(11,10);
		Time time12 =new Time(12,10);
		
		times[1] = new TimeBlock(time9,time11);
		times[3] = new TimeBlock(time9,time11);
		times[4] = new TimeBlock(time10,time12);
		times[5] = new TimeBlock(time9,time11);
		newTimes[1] = new TimeBlock(time10,time11);
		newTimes[3] = new TimeBlock(time10,time11);
		newTimes[4] = new TimeBlock(time11,time12);
		newTimes[5] = new TimeBlock(time10,time11);
		
		
		section = new Section("A", times, "CPE101", 2);
		newSectionCompare = new Section("1", newTimes, "CPE101", 0);
		badSection = new Section("1",times);
		section.setID("1");
		assertEquals("1",section.getID());
		section.setTimeBlock(newTimes);
		assertEquals(true,section.getTimes().equals(newTimes));
		section.setWaitList(1);
		newSectionCompare.setWaitList(1);
		assertEquals(1,section.getWaitList());
		section.setOpenSpots(0);
		assertEquals(0,section.getOpenSpots());
		assertEquals("CPE101",section.getCourseName());
		assertEquals(false,badSection.equals(section));
		
	}
}
