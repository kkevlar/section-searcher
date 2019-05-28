package logic.entity;

import java.util.Objects;

public class TimeBlock {
	private Time startTime;
	private Time endTime;
	
	public TimeBlock(Time startTime, Time endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Time getStartTime() {return this.startTime;}
	
	public void setStartTime(Time startTime) {this.startTime = startTime;}
	
	public Time getEndTime() {return this.endTime;}
	
	public void setEndTime(Time endTime) {this.endTime = endTime;}
	
	@Override
	public boolean equals(Object other) {
		boolean equal = true;
		
		if (other == null ||
				other.getClass() != this.getClass() ||
				checkTimes((TimeBlock)other))
			equal = false;
		
		return equal;
	}
	
	private boolean checkTimes(TimeBlock other) {
		return checkStartTimes(other) && checkEndTimes(other);
	}
	
	private boolean checkEndTimes(TimeBlock other) {
		boolean equal = true;

		if(this.endTime == null) {
			if(other.endTime != null)
				equal = false;
		}
		else if(!this.endTime.equals(other.endTime))
			equal = false;
		return equal;
	}
	
	private boolean checkStartTimes(TimeBlock other) {
		boolean equal = true;

		if(this.startTime == null) {
			if(other.startTime != null)
				equal = false;
		}
		else if(!this.startTime.equals(other.startTime))
			equal = false;
		return equal;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(startTime, endTime);
	}
}
