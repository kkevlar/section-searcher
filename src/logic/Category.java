package logic;

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
	private ArrayList<Course> courses;
	
	public Category() {
		this.name = "";
		this.isAvailable = false;
		courses = new ArrayList<Course>();
	}
	
	public Category(String name, ArrayList<Course> courses, boolean isAvailable) {
		this.name = name.trim();
		this.isAvailable = isAvailable;
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		String output = "Category(name=" + this.name + ", isAvailable=" + this.isAvailable + "):\n";
		for(Course course : courses) {
			output += "   " + course.toString();
		}
		
		return output;
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	public boolean isAvailable() {return this.isAvailable;}
	
	public void setAvailable(Boolean available) {this.isAvailable = available;}
	
	public ArrayList<Course> getCourses() {return this.courses;}
	
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
