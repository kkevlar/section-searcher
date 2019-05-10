package logic;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;


public class PlanFactory {


	public static Plan makePlan(String name, int id, ArrayList<Category> categories) throws JAXBException {
		return new Plan(name, id, categories); //need a way to determine id's
	}
	//from https://howtodoinjava.com/jaxb/write-object-to-xml/
	//takes Plan object and writes a 
	public static void jaxbObjectToXML(Plan plan, String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Plan.class);
             
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            
            //Write XML to StringWriter
            jaxbMarshaller.marshal(plan, sw);
             
            //Verifies XML Content
            String xmlContent = sw.toString();
            System.out.println( xmlContent );
            
            
            writer.write(xmlContent);
            
            writer.close();
        } 
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
