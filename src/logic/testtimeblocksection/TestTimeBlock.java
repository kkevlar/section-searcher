package logic.testtimeblocksection;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.entity.Time;
import logic.entity.TimeBlock;

public class TestTimeBlock {
	@Test
	public void testEquals1() {
		Time t1 = new Time(7, 0);
		Time t2 = new Time(9, 0);
		TimeBlock tb1 = new TimeBlock(t1, t2);
		TimeBlock tb2 = new TimeBlock(t1, t2);
		
		assertEquals(true, tb1.equals(tb2));
	}
}
