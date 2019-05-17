package logic;

import java.util.Objects;

public class Section {
	private String id;
	private TimeBlock[] times; //each index corresponds to a day of the week. 0 = Sunday, 1 = Monday, 2 = Tuesday, etc.
	private int openSpots;
	private int waitList;

	private String courseName;

	public Section(String id, TimeBlock[] times) {
		this.id = id;
		if(times != null && times.length != 7) //TODO: handle null times
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
		if (other == null)
			return false;
		
		if (other.getClass() != this.getClass())
			return false;
		
		if(this.id == null && ((Section)other).id != null)
			return false;
		if(this.id != null && ((Section)other).id == null)
			return false;
		if(this.id != null && ((Section)other).id != null)
			if(!this.id.contentEquals(((Section)other).id))
				return false;
		
		if(this.waitList != ((Section)other).waitList)
			return false;
		
		if(this.openSpots != ((Section)other).openSpots)
			return false;
		
		if(this.courseName == null && ((Section)other).courseName != null)
			return false;
		
		if(this.courseName != null && ((Section)other).courseName != null) {
			if(!this.courseName.contentEquals(((Section)other).courseName))
				return false;
		}
		
		if(this.times != null && ((Section)other).times == null)
			return false;
		
		if(this.times != null && ((Section)other).times != null) {		
			for(int i = 0; i < this.times.length; i++) {
				if(this.times[i] != null || ((Section)other).times[i] != null) {
					if(!this.times[i].equals(((Section)other).times[i]))
					return false;
				}
			}
		}
		
		return true;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, times, openSpots, waitList, courseName);
	}
}
