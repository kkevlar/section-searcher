package test.testplanfactory;
import java.io.File;

import org.junit.Test;

import logic.entity.Plan;
import logic.entity.PlanFactory;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


public class TestCreatePlan 
{
	@Test
	public void testMakePlan()
	{
		Plan plan1 = new Plan("test", 5, null);
		Plan plan2 = PlanFactory.makePlan("test", 5, null);
		
		assertEquals(plan1.getName(), plan2.getName());
		assertEquals(plan1.getID(), plan2.getID());
		assertEquals(plan1.getCategories(), plan2.getCategories());
	}
	
	@Test
	public void testDeleteSavedPlan()
	{
		try {
			File file = new File("./saved_plans/ThisIsATest.xml");
			file.createNewFile();
			assertTrue(file.exists());
			PlanFactory.deletePlan("ThisIsATest");
			
			file = new File("./saved_plans/ThisIsATest.xml");
			assertFalse(file.exists());
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
}
