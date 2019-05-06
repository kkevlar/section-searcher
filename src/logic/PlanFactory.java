package logic;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;


public class PlanFactory {

	public static Plan makePlan(String name, int id, ArrayList<Category> categories, String filename) throws JAXBException {
		
		Plan plan = new Plan(name, id, categories); //need a way to determine id's
		jaxbObjectToXML(plan,filename);
		return plan;
	}
	//from https://howtodoinjava.com/jaxb/write-object-to-xml/
	//takes Plan object and writes a 
	private static void jaxbObjectToXML(Plan plan, String filename){
        try{
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Plan.class);
             
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            //Print XML String to Console
            StringWriter sw = new StringWriter();
             
            //Store XML to File
            // todo: add functionality to change file name
            File file = new File(filename+".xml");
            
            //Write XML to StringWriter
            jaxbMarshaller.marshal(plan, file);
             
            //Verifies XML Content
            //String xmlContent = sw.toString();
            //System.out.println( xmlContent );
        } 
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
