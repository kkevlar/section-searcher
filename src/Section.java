
public class Section {
	private String id;
	private TimeBlock[] times; //each index corresponds to a day of the week. 0 = Sunday, 1 = Monday, 2 = Tuesday, etc.
	
	public Section(String id, TimeBlock[] times) {
		this.id = id;
		this.times = new TimeBlock[7];
	}
	
	public String getID() {return this.id;}
	
	public void setID(String id) {this.id = id;}
	
	public TimeBlock[] getTimes() {return this.times;}
	
	public void setTimeBlock(TimeBlock[] times) {this.times = times;}
}
