public class Category{
	public boolean isAvailable;
	public String name;
	
	public Category(String name, boolean isAvailable) {
		this.name = name.trim();
		this.isAvailable = isAvailable;
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	public boolean equals(Category other) {
		if(this.isAvailable != other.isAvailable)
			return false;
		
		if(!this.name.equals(other.name))
			return false;
		
		return true;
	}
}
