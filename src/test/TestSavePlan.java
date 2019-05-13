package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Optional;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import logic.Category;
import logic.Course;
import logic.Plan;
import logic.PlanFactory;
public class TestSavePlan {
	
	@Test
	public void testCreateAndLoadXML() throws JAXBException
	{

		//Setup

		ArrayList<Category> categories = new ArrayList<Category>();
		
		ArrayList<Course> cscCourses = new ArrayList<Course>();
		ArrayList<Course> mathCourses = new ArrayList<Course>();
		
		cscCourses.add(new Course("CSC101", "CSC", null));
		cscCourses.add(new Course("CSC225", "CSC", null));
		cscCourses.add(new Course("CSC357", "CSC", null));
		
		mathCourses.add(new Course("MATH100", "MATH", null));
		mathCourses.add(new Course("MATH200", "MATH", null));
		mathCourses.add(new Course("MATH300", "MATH", null));
		
		categories.add(new Category("CSC classes",cscCourses,false));
		categories.add(new Category("MATH classes",mathCourses,true));
		
		Plan plan = new Plan("CSC and MATH plan", 1, categories);
		
		//testing
		
		//delete any plans for testing
		ArrayList<String> plans = PlanFactory.GetPlanList();
		for(String name : plans) {
			PlanFactory.DeletePlan(name);
		}
		
		//starting with no plans
		plans = PlanFactory.GetPlanList();
		assertEquals(plans.size(), 0);
		
		//save the plan
		PlanFactory.SavePlan(plan);
		
		//should now have 1 plan
		plans = PlanFactory.GetPlanList();
		assertEquals(plans.size(), 1);
		
		//get that plan
		Optional<Plan> planFromXML = PlanFactory.GetPlan(plans.get(0));
		
		//and verify it
		assertTrue(planFromXML.isPresent());
		assertEquals(plan.toString(), planFromXML.get().toString());
		assertEquals(planFromXML.get().getName(), "CSC and MATH plan");
	}
}
