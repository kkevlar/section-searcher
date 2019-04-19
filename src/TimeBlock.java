
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
}
