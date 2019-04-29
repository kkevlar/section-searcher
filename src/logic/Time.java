package logic;

public class Time {
	public final int hours;
	public final int minutes;
	
	public Time(int hours, int minutes) throws InvalidTimeException{
		if(hours >= 24 || hours < 0)
			throw new InvalidTimeException("Hours must be less than 24 or greater than or equal to 0");
		if(minutes >= 60 || minutes < 0)
			throw new InvalidTimeException("Minutes must be less than 60 or greater than or equal to 0");
		
		this.hours = hours;
		this.minutes = minutes;
	}	
}
