package logic;
import java.util.ArrayList;
import java.util.List;


public class Course {
	private List<Section> sections;
	private String name;
	private String department;
	
	public Course(String name) {
		setSections(new ArrayList<Section>());
		this.name = name.trim(); //.trim() removes whitespace from ends of string
	}
	
	public Course(String name, List<Section> sections) {
		setSections(sections);
		this.name = name.trim(); //.trim() removes whitespace from ends of string
	}
	
	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();} 
	
	//TODO: implement isAvailable
	public boolean isAvailable() {
		return true;
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
	
	public void addSection(Section section) {
		this.sections.add(section);
	}
	
	public void removeSection(Section section) {
		this.sections.remove(section);
	}
}