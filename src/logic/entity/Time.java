//author: Brandon Lyday

package logic.entity;

import java.util.Objects;

public class Time {
	private int hours;
	private int minutes;
	
	public Time(int hours, int minutes) {
		if(hours >= 24 || hours < 0)
			throw new IllegalArgumentException("Hours must be less than 24 or greater than or equal to 0");
		if(minutes >= 60 || minutes < 0)
			throw new IllegalArgumentException("Minutes must be less than 60 or greater than or equal to 0");
		
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public int getMinutes() {return this.minutes;}
	
	/*
	 * @return 1 if minutes is too high, -1 if minutes is too low, and 0 on success
	 */
	public int setMinutes(int minutes) {
		if(minutes >= 60)
			return 1;
		if(minutes < 0)
			return -1;
		this.minutes = minutes;
		return 0;
	}
	
	public int getHours() {return this.hours;}
	
	/*
	 * @return 1 if hours is too high, -1 if hours is too low, and 0 on success
	 */
	public int setHours(int hours) {
		if(hours >= 24)
			return 1;
		if(minutes < 0)
			return -1;
		this.hours = hours;
		return 0;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean equal = true;
		if (other == null||
				other.getClass() != this.getClass()||
				this.hours != ((Time)other).hours || 
				this.minutes != ((Time)other).minutes)
			equal = false;
		
		return equal;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(this.hours, this.minutes);
	}
}
