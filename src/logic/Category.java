package logic;
public class Category{
	private boolean isAvailable;
	private String name;
	
	public Category(String name, boolean isAvailable) {
		this.name = name.trim();
		this.isAvailable = isAvailable;
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	public boolean getIsAvailable() {return this.isAvailable;}
	
	public void setIsAvailable(boolean isAvailable) {this.isAvailable = isAvailable;}
	
	
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
