package test.parsescrapedata;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import logic.entity.Course;
import logic.scraper.ParseHtml;
import logic.scraper.WebScraper;

public class TestParseHtml {
	@Test
	public void testParsing1()
	{
		WebScraper scraper = new WebScraper();
		String sample = 
		"Course,Sect,Class,Type,GE,Req,Days,Start,End,Instructor,Location,LCap,ECap,Enrl,Wait,Drop,ICS,\r\n" + 
		"CPE 100 /0,01,4138,Lec, , ,T,11:10 AM,12:00 PM, ,006-0124,180,120,0,0,0,ICS,\r\n" + 
		"CPE 101,01,8056,Lec, ,002868,MWF,08:10 AM,09:00 AM,Einakian, S,186-C201,36,35,2,6,2,ICS,\r\n" + 
		"CPE 101 (1),02,8057,Lab, ,002868,MWF,09:10 AM,10:00 AM,Einakian, S,014-0302,24,35,2,6,2,ICS,\r\n" + 
		"CPE 101 (1),03,5483,Lec, ,002868,MWF,09:10 AM,10:00 AM,Kauffman, D,011-0104,46,35,6,12,0,ICS,\r\n" + 
		"CPE 101 (1),04,5484,Lab, ,002868,MWF,10:10 AM,11:00 AM,Kauffman, D,014-0301,35,35,6,12,0,ICS,";
		List<Course> course = scraper.getCourseList(ParseHtml.parselines(sample));
		assertTrue(course.contains(new Course("CPE 101")));
	}	
	@Test
	public void testParsing2()
	{
		WebScraper scraper = new WebScraper();
		String sample = "CPE 133,11,7350,Lec, ,002538,TR,03:10 PM,04:30 PM,Perks, G,020-0132,24,28,26,0,4,ICS,\r\n" + 
				"CPE 133,12,7351,Lab, ,002538,TR,04:40 PM,06:00 PM,Perks, G,020-0132,24,28,26,0,4,ICS,\r\n" + 
				"CPE 200,01,1383,Ind, , , , , ,Oliver, J, , ,30,0,0,0,,\r\n" + 
				"CPE 200 /0,02,****,Ind, , , , , , , , , , , , ,,\r\n" + 
				"CPE 200 /0,03,****,Ind, , , , , , , , , , , , ,,\r\n" + 
				"CPE 200 /0,04,****,Ind, , , , , , , , , , , , ,,";
		List<Course> course = scraper.getCourseList(ParseHtml.parselines(sample));
		assertTrue(!course.contains(new Course("CPE 200")));
	}
}
