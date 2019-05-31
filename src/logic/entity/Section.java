package logic.entity;

import java.util.Objects;

public class Section {
	private String id;
	private TimeBlock[] times; //each index corresponds to a day of the week. 0 = Sunday, 1 = Monday, 2 = Tuesday, etc.
	private int openSpots;
	private int waitList;

	private String courseName;

	public Section(String id, TimeBlock[] times) {
		this.id = id;
		if(times != null && times.length != 7)
			throw new IllegalArgumentException("TimeBlock[] times must have length 7.");
		else 
			this.times = times;
	}
	
	public Section(String id, TimeBlock[] times, String courseName, int openSpots) {
		this.id = id;
		if(times.length != 7)
			throw new IllegalArgumentException("TimeBlock[] times must have length 7.");
		else 
			this.times = times;
		this.courseName = courseName;
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
		return this.courseName;	
	}
	
	@Override
	public boolean equals(Object other) {
		boolean equal = true;
		
		if (other == null ||
				other.getClass() != this.getClass() ||
				!this.equalID((Section) other) ||
				this.waitList != ((Section)other).waitList ||
				this.openSpots != ((Section)other).openSpots ||
				!this.equalCourseNames((Section) other) ||
				!this.equalTimes((Section)other))
			equal = false;
		
		return equal;
	}
	
	private boolean equalID(Section other) {
		boolean equal = true;
		
		if(this.id == null) {
			if(other.id != null)
				equal = false;
		}
		else if(!this.id.equals(other.id))
			equal = false;
		
		return equal;
	}
	
	private boolean equalCourseNames(Section other) {
		boolean equal = true;
		
		if(this.courseName == null) {
			if(other.courseName != null)
				equal = false;
		}
		else if(!this.courseName.equals(other.courseName))
			equal = false;
		
		return equal;
	}
	
	private boolean equalTimes(Section other) {
		boolean equal = true;
		
		if(this.times == null) {
			if(other.times != null)
				equal = false;
		}
		else if(other.times == null){
			equal = false;
		}
		else {	
			for(int i = 0; i < this.times.length; i++) {
				TimeBlock thisTime = this.times[i];
				TimeBlock otherTime = other.times[i];
				
				if(thisTime == null) {
					if(otherTime != null)
						equal = false;
				}
				else if(!thisTime.equals(otherTime))
					equal = false;
				}
			}		
		return equal;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, times, openSpots, waitList, courseName);
	}
}
