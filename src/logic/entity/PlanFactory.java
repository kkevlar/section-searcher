package logic.entity;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.StringWriter;

import logic.scraper.ClassDB;


public class PlanFactory {
	
	private PlanFactory() {}
	
	private static String path = "./saved_plans/";
	
	public static Plan makePlan(String name, int id, List<Category> categories) {
		return new Plan(name, id, categories);
	}
	
	public static void savePlan(Plan plan) {
		String filename = path + plan.getName() + ".xml";
		jaxbObjectToXML(plan, filename);
	}
	
	public static Optional<Plan> getPlan(String name) {
		String filename = path + name + ".xml";
		Optional<Plan> optionalPlan = xmlToObject(filename);
		
		if(!optionalPlan.isPresent()) {
			return optionalPlan;
		}
		
		ClassDB db = ClassDB.getInstance();
		db.scrapeAll();
		Plan plan = optionalPlan.get();
		
		List<Category> categories = plan.getCategories();
		
		for(int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			
			List<Course> emptyCourses = category.getCourses();
			List<Course> filledCourses = new ArrayList<>();
			
			for(Course course : emptyCourses) {
				String courseName = course.getName();
				Course filledCourse = db.getCourse(courseName);
				
				if(filledCourse != null) {
					filledCourses.add(filledCourse);
				}	
			}
			
			category.setCourses(filledCourses);
		}
		
		return Optional.of(plan);
			
	}
	
	public static boolean deletePlan(String name) {
		String filename = path + name + ".xml";
		
		try {
			Path path = Paths.get(filename);
			return Files.deleteIfExists(path);
		}catch(IOException e) {
			return false;
		}
		
		
	}

	public static List<String> getPlanList() {
		File folder = new File(path);
		File[] files = folder.listFiles();
		
		ArrayList<String> filenames = new ArrayList<>();
		
		for (File file : files) {
		    if (file.isFile()) {
		    	String temp = file.getName();
		    	
		        filenames.add( temp.substring(0, temp.lastIndexOf('.')) );
		    }
		}
		
		return filenames;
	}
	
	
	//from https://howtodoinjava.com/jaxb/write-object-to-xml/
	//takes Plan object and writes a 
	private static void jaxbObjectToXML(Plan plan, String filename){
        try{
            //Setup jaxb stuff
            JAXBContext jaxbContext = JAXBContext.newInstance(Plan.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            //Create a StringWriter for JAXB to write to
            StringWriter sw = new StringWriter();
            
            //Write XML to StringWriter
            jaxbMarshaller.marshal(plan, sw);
             
            //get the XML string
            String xmlContent = sw.toString();
            
            //Create and write to the file given by filename
            try(FileOutputStream outFile = new FileOutputStream(filename, false)) {
                outFile.write(xmlContent.getBytes());
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static Optional<Plan> xmlToObject(String filename){
		
		JAXBContext jaxbContext;
		Optional<Plan> optional = Optional.empty();
		
		try
		{
		    jaxbContext = JAXBContext.newInstance(Plan.class);             
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    Plan plan = (Plan) jaxbUnmarshaller.unmarshal(new File(filename));
		    
		    optional = Optional.ofNullable(plan);
		}
		catch (JAXBException e)
		{
		    e.printStackTrace();
		}
		
		
		return optional;
	}
}
