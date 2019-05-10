package logic;

public class Section {
	private String id;
	private TimeBlock[] times; //each index corresponds to a day of the week. 0 = Sunday, 1 = Monday, 2 = Tuesday, etc.
	private int openSpots;
	private int waitList;
	private Course course;
	
	public Section(String id, TimeBlock[] times) {
		this.id = id;
		if(times.length != 7)
			throw new IllegalArgumentException("TimeBlock[] times must have length 7.");
		else 
			this.times = times;
	}
	
	public Section(String id, TimeBlock[] times, Course course, int openSpots) {
		this.id = id;
		if(times.length != 7)
			throw new IllegalArgumentException("TimeBlock[] times must have length 7.");
		else 
			this.times = times;
		this.course = course;
		this.openSpots = openSpots;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public TimeBlock[] getTimes() {
		return this.times;
	}
	
	public void setTimeBlock(TimeBlock[] times) {
		this.times = times;
	}

	public int getWaitList() {
		return waitList;
	}

	public void setWaitList(int waitList) {
		this.waitList = waitList;
	}

	public int getOpenSpots() {
		return openSpots;
	}

	public void setOpenSpots(int openSpots) {
		this.openSpots = openSpots;
	}
	
	public String getCourseName() {
		return this.course.getName();	
	}
}
