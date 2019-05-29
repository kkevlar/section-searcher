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
		this.department = "";
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
	
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		
		if (other.getClass() != this.getClass())
			return false;
		
		if(this.name != null && ((Course)other).name == null)
			return false;
		
		if(this.name != null && ((Course)other).name != null && !this.name.contentEquals(((Course)other).name))
			return false;
		
		if(this.department != null && ((Course)other).department == null)
			return false;
		
		if(this.department != null && ((Course)other).department != null && !this.department.contentEquals(((Course)other).department))
			return false;
		
		if(this.sections != null && ((Course)other).sections == null)
			return false;
		
		if(this.sections != null && ((Course)other).sections != null) {		
			for(int i = 0; i < this.sections.size(); i++) {
				if(!this.sections.get(i).equals(((Course)other).sections.get(i)))
					return false;
			}
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, department, sections);
	}
}
