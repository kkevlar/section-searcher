package logic;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.StringWriter;


public class PlanFactory {
	
	private static String path = "./saved_plans/";

	public static Plan makePlan(String name, int id, ArrayList<Category> categories) throws JAXBException {
		return new Plan(name, id, categories); //need a way to determine id's
	}
	
	public static void SavePlan(Plan plan) {
		String filename = path + plan.getName() + ".xml";
		jaxbObjectToXML(plan, filename);
	}
	
	public static Optional<Plan> GetPlan(String name) {
		String filename = path + name + ".xml";
		return XMLToObject(filename);
	}
	
	public static boolean DeletePlan(String name) {
		String filename = path + name + ".xml";
		
		File file = new File(filename);
		
		return file.delete();
	}

	public static ArrayList<String> GetPlanList() {
		File folder = new File(path);
		File[] files = folder.listFiles();
		
		ArrayList<String> filenames = new ArrayList<String>();
		
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
            FileOutputStream outFile = new FileOutputStream(filename, false); 
            outFile.write(xmlContent.getBytes());
            
            outFile.close();
        } 
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static Optional<Plan> XMLToObject(String filename){
		
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
