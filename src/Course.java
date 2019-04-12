import java.util.ArrayList;
import java.util.List;


public class Course {
	private List<Section> sections;
	private String name;
	private long updateTime;
	
	public Course(String name, long updateTime) {
		sections = new ArrayList<Section>();
		this.name = name;
		this.updateTime = updateTime;
	}
	
	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name;}
	
	//TODO: implement isAvailable
	public boolean isAvailable() {
		return true;
	}
	
	//TODO: implement update
	//what does this even do???
	public void update() {
		return;
	}
}
