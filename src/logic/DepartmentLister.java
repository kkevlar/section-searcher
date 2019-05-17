package logic;

import java.util.ArrayList;
import java.util.List;

public class DepartmentLister 
{	
	public DepartmentLister()
	{
		//No internal initialization needed
	}
	
	public List<String> getDepartmentList()
	{
		ArrayList<String> list = new ArrayList<>();
		list.add("CSC");
		list.add("MATH");
		list.add("CPE");
		list.add("MU");
		list.add("CHIN");
		list.sort((a,b)->a.compareTo(b));
		return list;
	}
}
