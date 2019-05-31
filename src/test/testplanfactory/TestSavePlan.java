package test.testplanfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXBException;
import org.junit.Test;

import logic.entity.Category;
import logic.entity.Course;
import logic.entity.Plan;
import logic.entity.PlanFactory;

public class TestSavePlan {
	
	private String planName = "CSC and MATH plan";
	
	private Plan getTestPlan() {
		ArrayList<Category> categories = new ArrayList<>();
		
		ArrayList<Course> cscCourses = new ArrayList<>();
		ArrayList<Course> mathCourses = new ArrayList<>();
		
		cscCourses.add(new Course("CSC 300", "CSC", null));
		cscCourses.add(new Course("CSC 225", "CSC", null));
		
		mathCourses.add(new Course("MATH 306", "MATH", null));
		mathCourses.add(new Course("MATH 330", "MATH", null));

		categories.add(new Category("CSC classes",cscCourses,false));
		categories.add(new Category("MATH classes",mathCourses,true));

		return new Plan(planName, 1, categories);
	}
	
	@Test
	public void testCreateXML() throws JAXBException
	{
		
		//Setup
		Plan plan = getTestPlan();
		
		//testing
		
		//delete any plans for testing
		List<String> plans = PlanFactory.getPlanList();
		for(String name : plans) {
			PlanFactory.deletePlan(name);
		}
		
		//save the plan
		PlanFactory.savePlan(plan);
		
		//should now have 1 plan
		plans = PlanFactory.getPlanList();
		assertEquals(plans.size(), 1);
	}
	
	@Test
	public void testXML() throws JAXBException
	{
		
		//Setup
		Plan plan = getTestPlan();
		
		//testing
		
		//delete any plans for testing
		List<String> plans = PlanFactory.getPlanList();
		for(String name : plans) {
			PlanFactory.deletePlan(name);
		}
		
		//save the plan
		PlanFactory.savePlan(plan);
		
		//get that plan
		Optional<Plan> planFromXML = PlanFactory.getPlan(plans.get(0));
		
		//and verify it
		assertTrue(planFromXML.isPresent());
		if(planFromXML.isPresent()) {
			assertEquals(plan.toString(), planFromXML.get().toString());
			assertEquals(planFromXML.get().getName(), planName);
		}
	}
}
