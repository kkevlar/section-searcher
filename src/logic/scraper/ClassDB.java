//author: Brandon Lyday

package logic.scraper;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import logic.entity.Course;
import logic.entity.Section;

//contains all courses found by the scraper
public class ClassDB {
	private List<Course> courses;
	private static ClassDB instance;
	
	private ClassDB() {
		courses = new ArrayList<>();
	}
	
	public static synchronized ClassDB getInstance() {
		if(instance == null)
			instance = new ClassDB();
		return instance;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	
	public void scrapeAndAddDepartment(String department)
	{
		WebScraper scraper = new WebScraper();
		List<Course> scrapedCourses = scraper.scrapeCoursesByDept(department);
		for(Course course : scrapedCourses)
		{
			addCourse(course);
		}
	}
	
	public void scrapeAll()
	{
		DepartmentLister lister = new DepartmentLister();
		List<String> departments = lister.getDepartmentList();
		for(String department : departments)
		{
			scrapeAndAddDepartment(department);
		}
	}

	//gets all courses from this ClassDB object
	public List<Course> getCourses(){
		return this.courses;
	}
	
	//gets all the sections in a List<Course> object and returns them in one List<Section> object
	public static List<Section> getSections(List<Course> courses){
		List<Section> sections= new ArrayList<>();
		
		for(Course course : courses) {
			sections.addAll(course.getSections());
		}
		
		return sections;
	}
	
	//gets all sections from this ClassDB object
	public List<Section> getAllSections(){
		List<Section> sections= new ArrayList<>();
		
		for(Course course : this.courses) {
			sections.addAll(course.getSections());
		}
		
		return sections;
	}
	
	//searches for a course with a matching course name
	//if found, returns that course
	//if not found, returns null
	public Course getCourse(String courseName) {
		for(Course course : this.courses) {
			if(course.getName().contentEquals(courseName))
				return course;
		}
		return null;
	}
	
	//gets all sections of a department from a List<Course> object
	public static List<Section> getSectionsByDepartment(List<Course> courses, String department){
		return getSections(filterDepartment(courses, department));
	}
	
	//gets all sections of a department from this ClassDB object
	public List<Section> getSectionsByDepartment(String department){
		return getSections(filterDepartment(this.courses, department));
	}
	
	//returns a List<Course> filtered by department from the given List<Course> courses
	public static List<Course> filterDepartment(List<Course> courses, String department){
		return courses.stream()
				.filter(course -> course.getDepartment().equals(department))
				.collect(Collectors.toList());
	}
		
	//filters the sections of a List<Course> object by a maximum wait list and returns a List<Section> object
	public static List<Section> filterWaitListCourses(List<Course> courses, int maxWait) {
		List<Section> filtered = getSections(courses);

		filtered = filtered.stream()
				.filter(section -> section.getWaitList() <= maxWait)
				.collect(Collectors.toList());
		return filtered;
	}
	
	//filters the sections of a List<Section> object by a maximum wait list and returns a List<Section> object
	public static List<Section> filterWaitListSections(List<Section> sections, int maxWait) {
		List<Section> filtered;

		filtered = sections.stream()
				.filter(section -> section.getWaitList() <= maxWait)
				.collect(Collectors.toList());
		return filtered;
	}

	public static List<Section> filterCourseName(List<Section> sections, String courseName){
		List<Section> filtered;

		filtered = sections.stream()
				.filter(section -> section.getCourseName().equals(courseName)) //filters out sections that do not have a matching course name
				.collect(Collectors.toList());
		return filtered;
	}
	
	//returns a list of all sections sorted by wait list
	public List<Section> sortWaitList(){
		return sortWaitListSections(this.getAllSections());
	}
	
	//returns a List<Section> sorted by lowest wait list to highest wait list
	public static List<Section> sortWaitListCourses(List<Course> courses){
		List<Section> sorted = getSections(courses);
		
		sorted = sorted.stream()
				.sorted((s1, s2) -> s2.getWaitList() - s1.getWaitList())
				.collect(Collectors.toList());
		return sorted;
	}
	
	//returns a List<Section> sorted by lowest wait list to highest wait list
	public static List<Section> sortWaitListSections(List<Section> sections){
		List<Section> sorted;
		
		sorted = sections.stream()
				.sorted((s1, s2) -> s2.getWaitList() - s1.getWaitList())
				.collect(Collectors.toList());
		return sorted;
	}
}
