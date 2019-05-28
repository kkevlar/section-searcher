package test;

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

import logic.scraper.ClassDB;

public class TestSavePlan {
	
	@Test
	public void testCreateAndLoadXML() throws JAXBException
	{
		
		//Setup
		
		ArrayList<Category> categories = new ArrayList<>();
		
		ArrayList<Course> cscCourses = new ArrayList<>();
		ArrayList<Course> mathCourses = new ArrayList<>();
		
		cscCourses.add(new Course("CSC 300", "", null));
		cscCourses.add(new Course("CSC 225", "", null));
		
		mathCourses.add(new Course("MATH 306", "", null));
		mathCourses.add(new Course("MATH 330", "", null));

		categories.add(new Category("CSC classes",cscCourses,false));
		categories.add(new Category("MATH classes",mathCourses,true));

		Plan plan = new Plan("CSC and MATH plan", 1, categories);
		
		//testing
		
		//delete any plans for testing
		List<String> plans = PlanFactory.getPlanList();
		for(String name : plans) {
			PlanFactory.deletePlan(name);
		}
		
		//starting with no plans
		plans = PlanFactory.getPlanList();
		assertEquals(plans.size(), 0);
		
		//save the plan
		PlanFactory.savePlan(plan);
		
		//should now have 1 plan
		plans = PlanFactory.getPlanList();
		assertEquals(plans.size(), 1);
		
		//get that plan
		Optional<Plan> planFromXML = PlanFactory.getPlan(plans.get(0));
		
		//and verify it
		assertTrue(planFromXML.isPresent());
		if(planFromXML.isPresent()) {	//to appease sonarcloud
			assertEquals(plan.toString(), planFromXML.get().toString());
			assertEquals(planFromXML.get().getName(), "CSC and MATH plan");
		}
	}
}
