//author: Brandon Lyday

package test.timeclassdb;

import logic.Time;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestTime {
	@Test
	public void testTime1() {
		//valid hour, valid minute
		Time time = new Time(5, 0);
		
		assertEquals(0, time.getMinutes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeBadHour1() {
		//hour too large
		Time time = new Time(25, 0);
		assertEquals(true, time.getHours() < 24 && time.getHours() >= 0 );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeBadMinute1() {
		//minute too large
		Time time = new Time(5, 61);
		assertEquals(true, time.getMinutes() < 60 && time.getMinutes() >= 0 );

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeBadHour2() {
		//hour too small
		Time time = new Time(-1, 0);
		assertEquals(true, time.getHours() < 24 && time.getHours() >= 0 );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeBadMinute2() {
		//minute too small
		Time time = new Time(5, -1);
		assertEquals(true, time.getMinutes() < 60 && time.getMinutes() >= 0 );
	}
}
