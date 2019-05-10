package logic;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Course")
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {
	@XmlTransient
	private List<Section> sections;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "department")
	private String department;
	
	public Course() {
		name = "";
		department = "";
		sections = new ArrayList<Section>();
	}
	
	public Course(String name) {
		this.sections = new ArrayList<Section>();
		this.name = name.trim(); //.trim() removes whitespace from ends of string
		this.department = "";
	}
	
	public Course(String name, String department, List<Section> sections) {
		setSections(sections);
		this.name = name.trim(); //.trim() removes whitespace from ends of string
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "   Course(name=" + this.name + ", department=" + this.department + ")\n";
	}
	
	public String getName() {return this.name;}
	
	public String getDepartment() {return this.department;}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setName(String name) {this.name = name.trim();} 
	
	//TODO: implement isAvailable
	public boolean isAvailable() {
		return true;
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
