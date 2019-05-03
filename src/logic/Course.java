package logic;
import java.util.ArrayList;
import java.util.List;


public class Course {
	private List<Section> sections;
	private String name;
	private long updateTime;
	private String department;
	
	public Course(String name, long updateTime) {
		setSections(new ArrayList<Section>());
		this.name = name.trim(); //.trim() removes whitespace from ends of string
		this.updateTime = updateTime;
	}
	
	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();} 
	
	//TODO: implement isAvailable
	public boolean isAvailable() {
		return true;
	}
	
	//TODO: implement update
	//what does this even do???
	public void update() {
		return;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}
