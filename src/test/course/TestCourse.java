package test.course;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.entity.Section;

public class TestCourse {
	@Test
	public void testEqualNoLoop() {
		//loop body isn't executed
		Course c1 = new Course();
		Course c2 = new Course();
		
		assertEquals(true, c1.equals(c2));
	}
	
	@Test
	public void testEqualLoopOnce() {
		//loop body is executed exactly once
		Course c1 = new Course();
		Course c2 = new Course();
		
		Section s1 = new Section("01", null);
		
		c1.addSection(s1);
		c2.addSection(s1);
		
		assertEquals(true, c1.equals(c2));
	}
	
	@Test
	public void testEqualLoopTwice() {
		//loop body is executed exactly twice
		Course c1 = new Course();
		Course c2 = new Course();
		
		Section s1 = new Section("01", null);
		Section s2 = new Section("02", null);
		c1.addSection(s1);
		c2.addSection(s1);
		c1.addSection(s2);
		c2.addSection(s2);
		
		assertEquals(true, c1.equals(c2));
	}
	
	@Test
	public void testEqualLoopNormal() {
		//loop body is executed for a typical amount (between maximum and minimum)
		Course c1 = new Course();
		Course c2 = new Course();
		
		for(int i = 1; i <= 30; i++) {
			Section s = new Section("" + i, null);
			c1.addSection(s);
			c2.addSection(s);
		}
		
		//sections in the  middle are not equal
		c1.addSection(new Section("1000000000", null));
		c2.addSection(new Section("31", null));
		
		for(int i = 32; i <= 60; i++) {
			Section s = new Section("" + i, null);
			c1.addSection(s);
			c2.addSection(s);
		}
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testEqualLoopMaxIter() {
		//loop body is executed n times
		Course c1 = new Course();
		Course c2 = new Course();
		
		for(int i = 1; i <= 60; i++) {
			Section s = new Section("" + i, null);
			c1.addSection(s);
			c2.addSection(s);
		}

		assertEquals(true, c1.equals(c2));
	}
	
	@Test
	public void testEqualLoopNearMaxIter() {
		//loop body is executed n-1 times
		Course c1 = new Course();
		Course c2 = new Course();
		
		for(int i = 1; i <= 59; i++) {
			Section s = new Section("" + i, null);
			c1.addSection(s);
			c2.addSection(s);
		}
		
		//last sections are not equal
		c1.addSection(new Section("60", null));
		c2.addSection(new Section("1000000", null));
		
		assertEquals(false, c1.equals(c2));
	}
}
