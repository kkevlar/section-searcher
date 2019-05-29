package logic.testtimeblocksection;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.entity.Section;

public class TestSection {
	@Test
	public void testEquals1() {
		Section s1 = new Section("01", null);
		Section s2 = null;
		assertEquals(false, s1.equals(s2));
	}
	
	@Test
	public void testEquals2() {
		Section s1 = new Section("01", null);
		Section s2 = s1;
		assertEquals(true, s1.equals(s2));
	}
	
	@Test
	public void testEquals3() {
		Section s1 = new Section("01", null);
		Section s2 = new Section("01", null);
		assertEquals(true, s1.equals(s2));
	}
}
