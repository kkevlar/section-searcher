package logic.scraper;

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
		list.add("AEPS");
		list.add("AG");
		list.add("AGB");
		list.add("AGC");
		list.add("AGED");
		list.add("ASCI");
		list.add("BRAE");
		list.add("DSCI");
		list.add("ERSC");
		list.add("FSN");
		list.add("MSL");
		list.add("NR");
		list.add("RPTA");
		list.add("SS");
		list.add("WVIT");
		
		list.add("ARCE");
		list.add("ARCH");
		list.add("CM");
		list.add("CRP");
		list.add("EDES");
		list.add("LA");
		
		list.add("BUS");
		list.add("ECON");
		list.add("GSB");
		list.add("ITP");
		
		list.add("ANT");
		list.add("ART");
		list.add("CD");
		list.add("CHIN");
		list.add("COMS");
		list.add("DANC");
		list.add("ENGL");
		list.add("ES");
		list.add("FR");
		list.add("GEOG");
		list.add("GER");
		list.add("GRC");
		list.add("HIST");
		list.add("ISLA");
		list.add("ITAL");
		list.add("JOUR");
		list.add("JPNS");
		list.add("MU");
		list.add("PHIL");
		list.add("POLS");
		list.add("PSY");
		list.add("RELS");
		list.add("SOC");
		list.add("SPAN");
		list.add("TH");
		list.add("WGS");
		list.add("WLC");
		
		list.add("AREO");
		list.add("BMED");
		list.add("CE");
		list.add("CPE");
		list.add("CSC");
		list.add("EE");
		list.add("ENGR");
		list.add("ENVE");
		list.add("IME");
		list.add("MATE");
		list.add("ME");
		
		list.add("ASTR");
		list.add("BIO");
		list.add("BOT");
		list.add("CHEM");
		list.add("DATA");
		list.add("EDUC");
		list.add("GEOL");
		list.add("HLTH");
		list.add("KINE");
		list.add("LS");
		list.add("MATH");
		list.add("MCRO");
		list.add("MSCI");
		list.add("PHYS");
		list.add("PSC");
		list.add("SCM");
		list.add("STAT");
		
		list.sort((a,b)->a.compareTo(b));
		
		return list;
	}
}
