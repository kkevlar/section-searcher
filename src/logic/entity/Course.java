package logic.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	//Use to construct an empty Course
	public Course() {
		name = "";
		department = "";
		sections = new ArrayList<>();
	}
	
	//use when only the course name is known
	//methods in ClassDB can be used to populate the other fields
	public Course(String name) {
		this.sections = new ArrayList<>();
		this.name = name.trim(); //.trim() removes whitespace from ends of string
		this.department = name.split(" ")[0];
	}
	
	//use when all information is known
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
	
	public void setName(String name) {
		this.name = name.trim();
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
	
	@Override
	public boolean equals(Object other) {
		boolean equal = true;
		
		if (other == null ||
				other.getClass() != this.getClass() ||
				!this.equalName((Course)other) ||
				!this.equalDepartment((Course)other) ||
				!this.equalSections((Course) other))
			equal = false;
		
		return equal;
	}
	
	private boolean equalName(Course other) {
		boolean equal = true;
		if(this.name != null) {
			if(other.name == null)
				equal = false;
			else
				equal = this.name.equals(other.name);
		}
		else if(other.name != null)
				equal = false;
		
		return equal;
	}
	
	private boolean equalDepartment(Course other) {
		boolean equal = true;
		if(this.department != null) {
			if(other.department == null)
				equal = false;
			else
				equal = this.department.equals(other.department);
		}
		else if(other.department != null)
				equal = false;
		
		return equal;
	}
	
	private boolean equalSections(Course other) {
		boolean equal = true;
		
		if(this.sections != null) {
			if(other.sections == null)
				equal = false;
			else {
				for(int i = 0; i < this.sections.size(); i++) {
					if(!this.sections.get(i).equals(((Course)other).sections.get(i)))
						equal = false;
				}
			}
		}
		else if(other.sections != null)
			equal = false;
		
		return equal;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, department, sections);
	}
}
