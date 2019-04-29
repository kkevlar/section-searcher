package logic;
public class Category{
	public boolean isAvailable;
	public String name;
	
	public Category(String name, boolean isAvailable) {
		this.name = name.trim();
		this.isAvailable = isAvailable;
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	

	public boolean equal(Object other) {
		if (other.getClass() != this.getClass())
			return false;
		
		if(this.isAvailable != ((Category)other).isAvailable)
			return false;
		
		if(!this.name.equals(((Category)other).name))
			return false;
		
		return true;
	}
}
