package logic;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

//contains all courses found by the scraper
public class ClassDB {
	private List<Course> courses;
	
	public ClassDB() {
		courses = new ArrayList<Course>();
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public List<Course> getCourses(){
		return this.courses;
	}
	
	//gets all the sections in a List<Course> object and returns them in one List<Section> object
	public static List<Section> getSections(List<Course> courses){
		List<Section> sections= new ArrayList<Section>();
		
		for(Course course : courses) {
			sections.addAll(course.getSections());
		}
		
		return sections;
	}
	
	//TODO: availability
	public List<Section> filterAvailability(){
		return null;
	}
	
	
	//TODO: degree
	
	//department
	public static List<Course> filterDepartment(List<Course> courses, String department){
		List<Course> filtered = courses.stream()
				.filter(course -> course.getDepartment().equals(department))
				.collect(Collectors.toList());
		return filtered;
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

	public static List<Section> sortWaitListCourses(List<Course> courses){
		List<Section> sorted = getSections(courses);
		
		sorted = sorted.stream()
				.sorted((s1, s2) -> s1.getWaitList() - s2.getWaitList())
				.collect(Collectors.toList());
		return sorted;
	}
	
	public static List<Section> sortWaitListSections(List<Section> sections){
		List<Section> sorted;
		
		sorted = sections.stream()
				.sorted((s1, s2) -> s1.getWaitList() - s2.getWaitList())
				.collect(Collectors.toList());
		return sorted;
	}
		
	//TODO: GE
	
	//TODO: open
}
