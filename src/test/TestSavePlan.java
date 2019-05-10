package test;

import static org.junit.Assert.assertEquals;

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
		Plan p;
		
		ArrayList<Category> categories = new ArrayList<Category>();
		Category cat;
		
		ArrayList<Course> courses = new ArrayList<Course>();
		Course course;
		
		course = new Course("CSC309", "CSC", null);
		courses.add(course);
		
		cat = new Category("major classes",courses,true);
		categories.add(cat);
		
		p = new Plan("test plan", 1, categories);
		
		PlanFactory pf = new PlanFactory();
		
		pf.jaxbObjectToXML(p,"testXMLfile.txt");
		//pf.test(course,"testXMLfile.txt");
		
		Optional<Plan> plan = pf.XMLToObject("testXMLfile.txt");
		
		System.out.println(plan.get());
	}
}
