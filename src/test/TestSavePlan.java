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
		
		PlanFactory pf = new PlanFactory();
		
		pf.jaxbObjectToXML(plan,"testXMLfile.txt");
		
		Optional<Plan> planFromXML = pf.XMLToObject("testXMLfile.txt");
		
		assertTrue(planFromXML.isPresent());
		assertEquals(plan.toString(), planFromXML.get().toString());
		
		//System.out.println(planFromXML.get());
	}
}
