package logic.entity;
import java.util.Comparator;

public class CategoryComparator  implements Comparator<Category>{
	@Override
	//@return negative number to prioritize cat1, zero of cat1 and cat2 are equivalent, positive number to prioritize cat2
	public int compare(Category cat1, Category cat2) {
		// Prioritizes availability, then name alphabetically
		if(cat1 == cat2) //same object
			return 0;
		
		if(cat1.equals(cat2)) //equivalent objects
			return 0;
		
		if(cat1.isAvailable() && cat2.isAvailable()) 
			return cat1.getName().compareTo(cat2.getName()); //both are available, compare by names
		else if(cat1.isAvailable())
			return -1; //cat1 is available, cat2 is not
		else
			return 1; //cat2 is available, cat1 is not
	}
}
