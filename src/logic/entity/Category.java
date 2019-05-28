package logic.entity;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category{
	@XmlElement(name = "available")
	private boolean isAvailable;
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElementWrapper(name = "courses") 
	@XmlElement(name = "course")
	private List<Course> courses;
	
	public Category() {
		this.name = "";
		this.isAvailable = false;
		courses = new ArrayList<>();
	}
	
	public Category(String name, List<Course> courses, boolean isAvailable) {
		this.name = name.trim();
		this.courses = courses;
		this.isAvailable = isAvailable;
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Category(name=");
		builder.append(this.name);
		builder.append(", isAvailable=");
		builder.append(this.isAvailable);
		builder.append("):\n");
		for(Course course : courses) {
			builder.append("   ");
			builder.append(course.toString());
		}
		return builder.toString();
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	public boolean isAvailable() {return this.isAvailable;}
	
	public void setAvailable(Boolean available) {this.isAvailable = available;}
	
	public List<Course> getCourses() {return this.courses;}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	
	@Override
	public boolean equals(Object other) {
		boolean equal = true;
		
		if (other == null)
			equal = false;
		
		else if (other.getClass() != this.getClass())
			equal = false;
		
		else if(this.isAvailable != ((Category)other).isAvailable)
			equal = false;
		
		else if(!this.name.equals(((Category)other).name))
			equal = false;
		
		return equal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, isAvailable);
	}
}
