package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import logic.Category;
import logic.Course;
import logic.Plan;
import logic.PlanFactory;
public class TestSavePlan {
	@Test
	public void testWriteString() throws JAXBException
	{
		ArrayList<Category> categories = new ArrayList<Category>();
		Plan p;
		List<Course> courses = new ArrayList<Course>();
		courses.add(new Course("CSC 999"));
		Category cat = new Category("1234abc",courses,true);
		Category cat1 = new Category("testCatFalse",courses,false);
		Category cat2 = new Category("testcatTrue",courses,true);
		Category cat3 = new Category("testReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongName",courses,true);
		categories.add(cat);
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		PlanFactory pf = new PlanFactory();
		p=pf.makePlan("testPlan", 0, categories);
		pf.jaxbObjectToXML(p,"testXMLfile.txt");
	}
}