package main.java.com.corecomponents;


import java.io.File;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;  
import org.w3c.dom.Element;

public class XMLReader extends GenericClass {
	
	public static String xmlpath;
	public static String tagname;
	GenericClass coreMethods = new GenericClass();
	
	/**
	 * Method to read the single tag value from the xml file. 
	 * sample file -main.resource.config.xml
	 * 
	 * @param xmlfile path
	 * @param tagname
	 * @return string
	 * @author Ravi VG
	 */
		public String getSingleXMLTagValue(String xmlpath, String tagname){
		
			String val=null;
			
			try {
				// Getting single xml tag value from xml file
					
				File f = new File(xmlpath);
				coreMethods.LOGGER.info("file path Value is : " +f);
							
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(f);
			
				//get the root folder details
				doc.getDocumentElement().normalize();  								
				coreMethods.LOGGER.info("Root element is : "+ doc.getDocumentElement().getNodeName());
			
				//get Tag Value
				val = doc.getElementsByTagName(tagname).item(0).getTextContent();
				coreMethods.LOGGER.info("Tag value is : " + val);
				
				coreMethods.LOGGER.info("Reterive single tag values succesful");
			}
			catch (Exception e) 
			{
			
				coreMethods.LOGGER.info("Reterive single tag value from xml  failed");
				coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
				coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());		
			}
		
			return val;
		
			}
	
	
	/**
	 *  Method to read multiple tag values from the xml file. 
	 * sample file - main.resource.config.xml
	 * 
	 * @param xmlfile path
	 * @param tagname
	 * @return string
	 * @author Ravi VG
	 */
		public String[] getmultipleXMLTagValue(String xmlpath, String tagname)
	
		{
			// Getting multiple xml tag value from the the parent tag in an xml file
			String[] values =null;
			
			try {
			
				File f = new File(xmlpath);
				coreMethods.LOGGER.info("file path Value is : " +f);
				
				DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = docBuilder.newDocumentBuilder();
				Document doc = builder.parse(f);
			
				//get the root folder details
				doc.getDocumentElement().normalize();  
				coreMethods.LOGGER.info("Root element is : "+ doc.getDocumentElement().getNodeName());
		
				NodeList node = doc.getElementsByTagName(tagname);
				coreMethods.LOGGER.info("node is" + node.getLength());
				values=new String[node.getLength()];
				for(int i=0;i<values.length;i++){
					values[i] = node.item(i).getTextContent();
				
					coreMethods.LOGGER.info("loop value is" + values [i]);
					
				}
				
				coreMethods.LOGGER.info("Reterive multiple tag values succesful");
			}
			catch (Exception e) {
				
				coreMethods.LOGGER.info("Reterive multiple tag values from xml  failed");
				coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getCause());
				coreMethods.LOGGER.log(Level.SEVERE,"exception :{0}", e.getMessage());
				}
	
			return values;
	
		}
	
}
