package logic;

import java.util.ArrayList;

public class DepartmentLister 
{
	public static ArrayList<String> getDepartmentList()
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
