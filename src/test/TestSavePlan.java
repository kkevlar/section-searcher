package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import logic.Category;
import logic.Plan;
import logic.PlanFactory;
public class TestSavePlan {
	@Test
	public void testWriteString() throws JAXBException
	{
		ArrayList<Category> categories = new ArrayList<Category>();
		Plan p;
		Category cat = new Category("1234abc",null,true);
		Category cat1 = new Category("testCatFalse",null,false);
		Category cat2 = new Category("testcatTrue",null,true);
		Category cat3 = new Category("testReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongName",null,true);
		categories.add(cat);
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		PlanFactory pf = new PlanFactory();
		p=pf.makePlan("testPlan", 0, categories);
		pf.jaxbObjectToXML(p,"testXMLfile.txt");
	}
}
