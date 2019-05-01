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
	
	//TODO: availability
	public ArrayList<Section> filterAvailability(){
		return null;
	}
	
	
	//TODO: degree
	
	public static List<Course> filterDepartment(List<Course> courses, String department){
		List<Course> filtered = courses.stream()
				.filter(course -> course.getDepartment().equals(department))
				.collect(Collectors.toList());
		return filtered;
	}
	
	//TODO: wait list	
	public static List<Course> filterWaitList(List<Course> courses, int maxWait) {
		return null;
	}

	//TODO: GE
	
	//TODO: open
}
