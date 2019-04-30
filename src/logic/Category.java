package logic;

import java.util.Objects;

public class Category{
	private boolean isAvailable;
	private String name;
	
	public Category(String name, boolean isAvailable) {
		this.name = name.trim();
		this.isAvailable = isAvailable;
	}

	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name.trim();}
	
	public boolean isAvailable() {return this.isAvailable;}
	
	public void setAvailable(Boolean available) {this.isAvailable = available;}
	
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
