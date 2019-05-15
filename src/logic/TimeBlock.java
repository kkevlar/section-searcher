package logic;

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
		if (other == null)
			return false;
		
		if (other.getClass() != this.getClass())
			return false;
		
		if(this.startTime != null && ((TimeBlock)other).startTime == null)
			return false;
		
		if(this.startTime != null && ((TimeBlock)other).startTime != null) {
			if(!this.startTime.equals(((TimeBlock)other).startTime))
				return false;
		}
		
		if(this.endTime != null && ((TimeBlock)other).endTime == null)
			return false;
		
		if(this.endTime != null && ((TimeBlock)other).endTime != null) {
			if(!this.endTime.equals(((TimeBlock)other).endTime))
				return false;
		}
		return true;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(startTime, endTime);
	}
}
