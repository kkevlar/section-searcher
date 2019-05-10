package logic;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category{
	private boolean isAvailable;
	private String name;
	private List<Course> courses;
	
	public Category() {
		this.name = "";
		this.isAvailable = false;
	}
	
	public Category(String name, List<Course> courses, boolean isAvailable) {
		this.name = name.trim();
		this.isAvailable = isAvailable;
		this.courses = courses;
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	public boolean isAvailable() {return this.isAvailable;}
	
	public void setAvailable(Boolean available) {this.isAvailable = available;}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		
		if (other.getClass() != this.getClass())
			return false;
		
		if(this.isAvailable != ((Category)other).isAvailable)
			return false;
		
		if(!this.name.equals(((Category)other).name))
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, isAvailable);
	}
}
