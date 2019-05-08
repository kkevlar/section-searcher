package logic;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "plan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Plan {
	private String name;
	private int id;
	private boolean valid;
	
	@XmlElement(name = "category")
	private ArrayList<Category> categories;
	
	public Plan() {
		this.name="";
		this.id = 0;
		this.categories = null;
	}
	public Plan(String name, int id, ArrayList<Category> categories) {
		this.name = name.trim(); //.trim() removes whitespace from ends of name
		this.id = id;
		this.categories = categories;
	}
	
	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	
	public int getID() {return this.id;}
	
	public void setID(int id) {this.id = id;}
	
	
	public boolean isValid() {return this.valid;}
	
	public void setValid(boolean valid) {this.valid = valid;}
	
	
	public ArrayList<Category> getCategories() {return this.categories;}
	
	
	public void addCategory(Category cat) {
		this.categories.add(cat);
		Comparator c = new CategoryComparator();
		this.categories.sort(c);
	}
}
