package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import logic.Category;
import logic.PlanFactory;
public class TestSavePlan {
	@Test
	public void testWriteString() throws JAXBException
	{
		ArrayList<Category> categories = new ArrayList<Category>();
		Category cat = new Category("1234abc",true);
		Category cat1 = new Category("testCatFalse",false);
		Category cat2 = new Category("testcatTrue",true);
		Category cat3 = new Category("testReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongNametestReallyLongName",true);
		categories.add(cat);
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		PlanFactory A = new PlanFactory();
		A.makePlan("", 0, categories,"testFile1234");
	}
}
